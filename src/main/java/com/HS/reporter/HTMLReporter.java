/**
 * 
 */
package com.HS.reporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.HS.common.ExecutionEnvironment;
import com.HS.common.UtilityMethods;
import com.HS.pojos.StepDetails;
import com.HS.pojos.TestCaseLocation;
import com.HS.utils.Log;

/**
 * @author hemasundar
 *
 */
public class HTMLReporter implements Reporter {
    public Log logger;
    ExecutionEnvironment env;

    /**
    * 
    */
    public HTMLReporter(ExecutionEnvironment env) {
	logger = new Log(getClass().getSimpleName());
	this.env = env;
    }

    /**
     * 
     */
    public void setFilePaths() {
    }

    /**
     * @throws IOException
     * 
     */
    public void writeSuiteTemplate() throws IOException {
	FileUtils.copyFile(new File(env.suiteReportTemplatePath), new File(env.suiteReportPath));
    }

    /**
     * @throws IOException
     * 
     */
    public synchronized void updateSuiteTemplate(ISuite suite) throws IOException {
	String currentSuiteReport = new UtilityMethods().readFile(new File(env.suiteReportPath));
	List<XmlTest> tests = suite.getXmlSuite().getTests();
	for (XmlTest xmlTest : tests) {
	    String updateString = "<tr><th>" + xmlTest.getName() + "</th><th><a href=" + xmlTest.getName()
		    + ".html>Report link</a></th></tr><!--Thread report link-->";
	    currentSuiteReport = currentSuiteReport.replaceFirst("<!--Thread report link-->", updateString);
	}

	new UtilityMethods().writeFile(new File(env.suiteReportPath), currentSuiteReport);
    }

    /**
     * @throws IOException
     * 
     */
    public void writeThreadTemplate(ArrayList<TestCaseLocation> testCaseIDsForExecution, ITestContext context)
	    throws IOException {
	String currentTest = context.getCurrentXmlTest().getName();
	FileUtils.copyFile(new File(env.threadReportTemplatePath),
		new File(env.threadReportPath.replace("testTag", currentTest)));
	/**
	 * Writing the test case summary row for the Thread initially only with
	 * Total number of test case count.
	 */
	String currentThreadReport = new UtilityMethods()
		.readFile(new File(env.threadReportPath.replace("testTag", currentTest)));
	String testCaseSummaryRow4Thread = "<td>S. No.</td><td>" + testCaseIDsForExecution.size()
		+ "</td><td>Test group</td><td>Browser</td><td>Break point</td><td>Status</td><td>Failed TCs</td><td>Result</td>";
	currentThreadReport = currentThreadReport.replace("<!--test cases summary row undefined-->",
		testCaseSummaryRow4Thread);

	new UtilityMethods().writeFile(new File(env.threadReportPath.replace("testTag", currentTest)),
		currentThreadReport);

	// writeTestCaseTemplate(testCaseIDsForExecution, currentTest);
    }

    /**
     * @param testCaseIDsForExecution
     * @param iTestContext
     * @param oldtext
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeTestCaseTemplate(TestCaseLocation currentTestCase, ITestContext context)
	    throws FileNotFoundException, IOException {
	String currentTest = context.getCurrentXmlTest().getName();
	String currentThreadReport = new UtilityMethods()
		.readFile(new File(env.threadReportPath.replace("testTag", currentTest)));
	/**
	 * Reading the single test case plan/template file
	 */
	String testCaseTemplate = new UtilityMethods().readFile(new File(env.testCaseReportTemplate));
	/**
	 * Writing the Above read test case template/html snippet for all the
	 * test cases.
	 */
	// for (TestCaseLocation testCase : testCaseIDsForExecution) {
	/**
	 * Replacing the the id of the test case status row. It will be used for
	 * hiding/showing the test case details with steps.
	 */
	String updateString1 = testCaseTemplate.replaceAll("unDefinedIdTestCase",
		currentTest + "_" + currentTestCase.getTCName());
	String updateString2 = updateString1.replaceAll("<!--test step detail-->",
		"<!--test step detail for " + currentTest + "_" + currentTestCase.getTCName() + "-->");

	currentThreadReport = currentThreadReport.replace("<!--test cases rows-->", updateString2);
	// }
	new UtilityMethods().writeFile(new File(env.threadReportPath.replace("testTag", currentTest)),
		currentThreadReport);
    }

    /**
     * 
     */
    public void updateThreadReport() {
	// TODO Auto-generated method stub
    }

    /**
     * @throws IOException
     * @throws FileNotFoundException
     * 
     */
    public void updateTestStep(StepDetails testCase, String currentTestTagName)
	    throws FileNotFoundException, IOException {
	String threadReportString = new UtilityMethods()
		.readFile(new File(env.threadReportPath.replace("testTag", currentTestTagName)));

	int index = threadReportString
		.indexOf("id=\"" + currentTestTagName + "_" + testCase.getTestCaseNameBelongTo() + "\"");
	String updatedPartString = threadReportString.substring(index).replace(
		"<!--test step detail for " + currentTestTagName + "_" + testCase.getTestCaseNameBelongTo() + "-->",
		"<tr><td>" + testCase.getTestCaseNameBelongTo() + "</td><td>" + testCase.getStepAction() + "</td><td>"
			+ testCase.getStep() + "</td><td>" + env.testStepStatus
			+ "</td><td>Execution Date & Time</td><td>Remarks</td></tr><!--test step detail for "
			+ currentTestTagName + "_" + testCase.getTestCaseNameBelongTo() + "-->");
	String resultantThreadReport = threadReportString.substring(0, index).concat(updatedPartString);

	new UtilityMethods().writeFile(new File(env.threadReportPath.replace("testTag", currentTestTagName)),
		resultantThreadReport);

    }

    /**
     * @throws IOException
     * @throws FileNotFoundException
     * 
     */
    public void readingTemplateFiles4Threads(ITestContext context) throws FileNotFoundException, IOException {
	String currentTest = context.getCurrentXmlTest().getName();
	String oldtext = new UtilityMethods().readFile(new File(env.threadReportPath.replace("testTag", currentTest)));
	String threadReportString = new UtilityMethods()
		.readFile(new File(env.threadReportPath.replace("testTag", currentTest)));
	String oldtext1 = new UtilityMethods().readFile(new File(env.testCaseReportTemplate));
    }
}
