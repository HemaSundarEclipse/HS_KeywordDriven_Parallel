/**
 * 
 */
package com.HS.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

/**
 * @author hemasundar
 *
 */
public class ExecutionEnvironment {
    /**
     * 
     */
    public ExecutionEnvironment() {
	assigningFilePaths();
    }

    public String browserName;
    public String testCaseSheet;
    public String isRemoteExecution;
    public String remoteURL;
    public int maxTimeOut;
    public String fileSeperator = System.getProperty("file.separator");
    public String projectRootDir = System.getProperty("user.dir");
    /*
     * HashMap that will have data from uiautomation.properties. It will be
     * populated through Utility.populateGlobalMap() method.
     */
    public HashMap<String, String> execEnvVarMap = new HashMap<String, String>();
    public String testCaseFileFormat;
    // Name and location of Property file location (uiautomation.properties)
    public String propertyFileLocation;

    public String logFile;
    public String buildEnv;
    public String currentTestTagName;
    public String suiteReportPath;
    public String suiteReportTemplatePath;
    public String threadReportTemplatePath;
    public String threadReportPath;
    public String testCaseReportTemplate;

    public TestStepStatus testStepStatus = TestStepStatus.FAIL;
    public WebDriver driver;

    /**
     * it will load all the execution environment properties to ExecEnvVarMap
     */
    public void loadEnv4mPropFile() {
	Properties prop = new Properties();
	try {
	    prop.load(new FileInputStream(propertyFileLocation));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	for (final String name : prop.stringPropertyNames())
	    execEnvVarMap.put(name, prop.getProperty(name));
    }

    /**
     * it will update all the execution environment properties from current
     * System to ExecEnvVarMap
     */
    public void updateEnv4mSystemProp() {
	Properties prop = System.getProperties();
	for (final String name : prop.stringPropertyNames())
	    execEnvVarMap.put(name, prop.getProperty(name));
	/* Printing all the property values stored in ExecEnvVarMap hash map */
	for (Map.Entry<String, String> entry : execEnvVarMap.entrySet()) {
	    String key = entry.getKey().toString();
	    String value = entry.getValue();
	    // System.out.println(key + " = " + value);
	}
    }

    /**
     * @param context
     * 
     */
    public void assigningEnv(ITestContext context) {
	browserName = execEnvVarMap.get("browserName");
	isRemoteExecution = execEnvVarMap.get("IsRemoteExecution");
	remoteURL = execEnvVarMap.get("remoteUrl");
	maxTimeOut = Integer.parseInt(execEnvVarMap.get("maximumTimeout"));
	fileSeperator = execEnvVarMap.get("file.separator");
	testCaseFileFormat = execEnvVarMap.get("testCaseFileFormat");
	buildEnv = execEnvVarMap.get("buildEnv");
	currentTestTagName = context.getCurrentXmlTest().getName();
    }

    /**
     * 
     */
    public void assigningFilePaths() {

	propertyFileLocation = "src" + fileSeperator + "main" + fileSeperator + "java" + fileSeperator + "com"
		+ fileSeperator + "HS" + fileSeperator + "common" + fileSeperator + "ExecEnv.properties";
	logFile = "Logs";
	suiteReportPath = projectRootDir + "\\Reports\\Report plan.html";
	suiteReportTemplatePath = projectRootDir + "\\Report plan_Suite.html";
	threadReportTemplatePath = projectRootDir + "\\Report plan_Thread.html";
	threadReportPath = projectRootDir + "\\Reports\\testTag.html";
	testCaseReportTemplate = projectRootDir + "\\Report plan_TC.html";

    }
}
