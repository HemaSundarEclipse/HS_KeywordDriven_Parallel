/**
 * 
 */
package com.HS.common;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.HS.reporter.HTMLReporter;
import com.HS.utils.Log;

/**
 * @author Hema
 *
 */
public class TestStepExecution {
    private Log logger;
    public StepDetails tcStepData4TestStep;
    public TestObject currentStepTestObject;
    public ExecutionEnvironment env;
    public CommonSeleniumActions perform;
    HTMLReporter objReporter;
    private WebDriver driver;

    /**
     * @param tcName
     * @param currentStepTestObject
     * @param tcStepData4TestStep
     * 
     */
    public TestStepExecution(StepDetails tcStepData4TestStep, TestObject currentStepTestObject,
	    ExecutionEnvironment env) {
	this.env = env;
	this.tcStepData4TestStep = tcStepData4TestStep;
	this.currentStepTestObject = currentStepTestObject;
	logger = new Log(getClass().getSimpleName());
	perform = new CommonSeleniumActions(currentStepTestObject, env);
	objReporter = new HTMLReporter(env);
    }

    /**
     * 
     */
    public void executeTestStep() {

	StepActions currentStepAction = StepActions.valueOf(tcStepData4TestStep.getStepAction().toUpperCase());
	switch (currentStepAction) {

	case OPENBROWSER:

	    try {
		perform.openBrowser(tcStepData4TestStep.getDataContent()[0]);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    try {
		objReporter.updateTestStep(tcStepData4TestStep, env.currentTestTagName);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    break;
	case SENDKEYS:
	    try {
		perform.sendKeys(tcStepData4TestStep.getDataContent()[0]);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    try {
		objReporter.updateTestStep(tcStepData4TestStep, env.currentTestTagName);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    break;

	case VERIFYTEXT:

	    try {
		perform.verifyText(tcStepData4TestStep.getDataContent()[0]);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    try {
		objReporter.updateTestStep(tcStepData4TestStep, env.currentTestTagName);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    break;

	default:
	    break;
	}

    }
}
