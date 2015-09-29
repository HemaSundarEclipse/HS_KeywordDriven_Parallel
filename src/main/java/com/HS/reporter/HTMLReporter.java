/**
 * 
 */
package com.HS.reporter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.HS.common.ExecutionEnvironment;
import com.HS.common.StepDetails;
import com.HS.common.TestCaseLocation;
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
	String oldtext = readFile(new File(env.suiteReportPath));
	List<XmlTest> tests = suite.getXmlSuite().getTests();
	for (XmlTest xmlTest : tests) {
	    String updateString = "<tr><th>" + xmlTest.getName() + "</th><th><a href=" + xmlTest.getName()
		    + ".html>Report link</a></th></tr><!--Thread report link-->";
	    oldtext = oldtext.replaceFirst("<!--Thread report link-->", updateString);
	}

	writeFile(new File(env.suiteReportPath), oldtext);
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

	String oldtext = readFile(new File(env.threadReportPath.replace("testTag", currentTest)));

	String updateString0 = "<td>S. No.</td><td>" + testCaseIDsForExecution.size()
		+ "</td><td>Test group</td><td>Browser</td><td>Break point</td><td>Status</td><td>Failed TCs</td><td>Result</td>";
	oldtext = oldtext.replace("<!--test cases summary row undefined-->", updateString0);

	String oldtext1 = readFile(new File(env.testCaseReportTemplate));

	for (TestCaseLocation testCase : testCaseIDsForExecution) {

	    String updateString1 = oldtext1.replaceAll("unDefinedIdTestCase", currentTest + "_" + testCase.getTCName());
	    String updateString2 = updateString1.replaceAll("<!--test step detail-->",
		    "<!--test step detail for " + currentTest + "_" + testCase.getTCName() + "-->");
	    oldtext = oldtext.replace("<!--test cases rows-->", updateString2);
	}
	writeFile(new File(env.threadReportPath.replace("testTag", currentTest)), oldtext);

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
	String threadReportString = readFile(new File(env.threadReportPath.replace("testTag", currentTestTagName)));

	int index = threadReportString
		.indexOf("id=\"" + currentTestTagName + "_" + testCase.getTestCaseNameBelongTo() + "\"");
	String updatedPartString = threadReportString.substring(index).replace(
		"<!--test step detail for " + currentTestTagName + "_" + testCase.getTestCaseNameBelongTo() + "-->",
		"<tr><td>" + testCase.getTestCaseNameBelongTo() + "</td><td>" + testCase.getStepAction() + "</td><td>"
			+ testCase.getStep()
			+ "</td><td>Status</td><td>Execution Date & Time</td><td>Remarks</td></tr>");
	String resultantThreadReport = threadReportString.substring(0, index).concat(updatedPartString);

	writeFile(new File(env.threadReportPath.replace("testTag", currentTestTagName)), resultantThreadReport);
    }

    /**
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String readFile(File file) throws FileNotFoundException, IOException {
	BufferedReader br = new BufferedReader(new FileReader(file));
	String line = "", oldtext = "";
	while ((line = br.readLine()) != null) {
	    oldtext += line + "\r\n";
	    // System.out.println("Older HTML text is - " + oldtext);
	}
	br.close();
	return oldtext;
    }

    /**
     * @param oldtext
     * @throws IOException
     */
    public void writeFile(File file, String oldtext) throws IOException {
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write(oldtext);
	bw.flush();
	bw.close();
    }
}
