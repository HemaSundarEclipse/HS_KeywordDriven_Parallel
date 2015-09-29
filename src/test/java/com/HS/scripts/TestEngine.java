/**
 * 
 */
package com.HS.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.HS.common.ExecutionEnvironment;
import com.HS.common.StepDetails;
import com.HS.common.TestCaseLocation;
import com.HS.common.TestCaseStatus;
import com.HS.common.TestNGVariables;
import com.HS.common.TestObject;
import com.HS.common.TestStepExecution;
import com.HS.common.TestStepStatus;
import com.HS.dataReader.CSVDataReader;
import com.HS.reporter.HTMLReporter;
import com.HS.utils.Log;

/**
 * @author hemasundar
 *
 */
public class TestEngine {
    public Log logger;
    public ExecutionEnvironment env;
    public CSVDataReader reader;
    public ArrayList<TestCaseLocation> TestCaseIDsForExecution;
    private List<String[]> tcData, orData;
    public HTMLReporter objReporter;
    public TestNGVariables objTestNGVariables;
    public String testCaseStatus1;
    public TestCaseStatus testCaseStatus;

    /**
    * 
    */
    public TestEngine() {
	logger = new Log(getClass().getSimpleName());
	objTestNGVariables = TestNGVariables.getInstance();
	env = new ExecutionEnvironment();
	reader = new CSVDataReader();
	TestCaseIDsForExecution = new ArrayList<TestCaseLocation>();
	objReporter = new HTMLReporter(env);
    }

    /**
     * 
     */
    @BeforeSuite
    private void beforeSuite() {

	/*
	 * Load HTML report template only for Total execution summary & Set the
	 * total test case count
	 */
	try {
	    objReporter.writeSuiteTemplate();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	/*
	 * Add the thread templates based on the number of test tags in
	 * TestNG.xml Down the line, Need to place this in to beforeSuite method
	 */
	try {
	    objReporter.updateSuiteTemplate(objTestNGVariables.getSuite());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @BeforeTest
    public void beforeTest(ITestContext context) {
	System.out.println("Sample");

	/**
	 * Loading And assigning execution environment into java variables
	 */
	// env.assigningFilePaths();
	reader.setFilePaths();
	env.loadEnv4mPropFile();
	env.updateEnv4mSystemProp();
	env.assigningEnv(context);
	env.loadEnv4mPropFile();
    }

    /**
     * 
     */
    @Test
    @Parameters({ "testGroup", "testCaseID" })
    public void exectingTests(String testGroup, String testCaseID, ITestContext context) {
	logger.info("Sample");
	// objReporter.setFilePaths();
	/* Loading test case data */
	orData = reader.getORData();
	tcData = reader.getTestCaseData();

	gettingTestCases4Execution(testGroup, testCaseID);

	try {
	    /*
	     * load the current test tag's thread environment values to HTML
	     * report
	     */
	    objReporter.writeThreadTemplate(TestCaseIDsForExecution, context);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	for (TestCaseLocation currentTestCaseID : TestCaseIDsForExecution) {

	    /* Write the test case row in HTML report */

	    // Need to write logic to check whether the TC found in TC sheet or
	    // not
	    executeTestCase(currentTestCaseID.getTCName(), currentTestCaseID.getTCRowNumber());

	}
    }

    /**
     * @param tcName
     * @param tcRowNumber
     */
    private void executeTestCase(String tcName, int tcRowNumber) {
	String step, parent, testObject, dataContent, StepAction;
	int tcRowCount = tcData.size();
	String cellValue;
	do {
	    StepDetails tcStepData4TestStep = getTCStepData4TestStep(tcName, tcRowNumber);
	    TestObject currentStepTestObject = getORData4TestStep(tcStepData4TestStep.getParent(),
		    tcStepData4TestStep.getTestObject());

	    /* Update the HTML report with test step details */

	    TestStepExecution stepExecutor = new TestStepExecution(tcStepData4TestStep, currentStepTestObject, env);

	    stepExecutor.executeTestStep();

	    if (env.testStepStatus == TestStepStatus.FAIL) {
		testCaseStatus = TestCaseStatus.FAIL;

		break;
	    } else if (env.testStepStatus == TestStepStatus.PASS) {
		testCaseStatus = TestCaseStatus.PASS;
	    }
	    /* Update the HTML report with test step result */

	    tcRowNumber++;

	    if (tcRowNumber == tcRowCount) {
		break;
	    }
	    cellValue = reader.getCellValue(tcData, tcRowNumber, "testcase_id");
	} while (cellValue == null || cellValue.trim().equalsIgnoreCase(""));

    }

    public TestObject getORData4TestStep(String parent, String testObject) {
	String how = null, what = null;
	try {
	    int rowCount = orData.size();
	    int iterativeRow = 0;
	    while (iterativeRow < rowCount) {
		if (reader.getCellValue(orData, iterativeRow, "parent").equals(parent)
			&& reader.getCellValue(orData, iterativeRow, "testObject").equals(testObject)) {
		    how = reader.getCellValue(orData, iterativeRow, "how");
		    what = reader.getCellValue(orData, iterativeRow, "what");

		    break;
		}
		iterativeRow++;
	    }
	} catch (Exception e) {
	    // throw e;
	}
	return new TestObject(how, what);
    }

    /**
     * @param tcName
     * @param tcRowNumber
     * @return
     * 
     */
    private StepDetails getTCStepData4TestStep(String tcName, int tcRowNumber) {
	String testCaseName = tcName;
	String step = reader.getCellValue(tcData, tcRowNumber, "step");
	String parent = reader.getCellValue(tcData, tcRowNumber, "parent");
	String testObject = reader.getCellValue(tcData, tcRowNumber, "testObject");
	String dataContent = reader.getCellValue(tcData, tcRowNumber, "stepdata");
	/**
	 * Need to read the stepDataRegex from ExecEnv.prop file or from
	 * ExecutionEnvironment.java file
	 */
	String[] stepData = dataContent.split("#");
	String StepAction = reader.getCellValue(tcData, tcRowNumber, "stepaction");
	return new StepDetails(testCaseName, step, parent, testObject, stepData, StepAction, TestStepStatus.INITIAL);
    }

    /**
     * @param testGroup
     * @param testCaseID
     */
    private void gettingTestCases4Execution(String testGroup, String testCaseID) {
	int tcRowCount = tcData.size();

	if (!(testCaseID == null || testCaseID == "")) {
	    String[] TCids = testCaseID.split(",");
	    for (String ID : TCids) {
		ID = ID.trim();
		int tcIndex = 0;
		while (tcIndex < tcRowCount) {
		    if (reader.getCellValue(tcData, tcIndex, "testcase_id").equalsIgnoreCase(ID)) {
			TestCaseIDsForExecution.add(new TestCaseLocation(ID, tcIndex));
			break;
		    }
		    tcIndex++;
		}

	    }
	} else if (!(testGroup == null || testGroup == "")) {
	    String[] TcGrps = testGroup.split(",");
	    for (String Group : TcGrps) {
		Group = Group.trim();
		int tcIndex = 0;
		while (tcIndex < tcRowCount) {
		    try {
			String tGrp = reader.getCellValue(tcData, tcIndex, "TestGroups").trim();

			String[] TCGrpofID = tGrp.split(",");

			List<String> TCGrpofIDList = Arrays.asList(TCGrpofID);
			for (String Grp : TCGrpofIDList) {
			    Grp = Grp.trim();
			    if (Grp.equalsIgnoreCase(Group)) {
				String tid = reader.getCellValue(tcData, tcIndex, "testcase_id");
				TestCaseIDsForExecution.add(new TestCaseLocation(tid, tcIndex));
			    }
			}
		    } catch (NullPointerException e) {
			e.printStackTrace();
		    }
		    tcIndex++;
		}
	    }
	}
    }

    /**
     * 
     */
    @AfterTest
    private void AfterTestMethod() {
	logger.info("In AfterTest method");

	// Reset driver object state
	env.driver = null;
    }
}
