/**
 * 
 */
package com.HS.pojos;

import com.HS.enums.TestStepStatus;

/**
 * @author Hema
 *
 */
public class StepDetails {
    private final String step, parent, testObject, StepAction, testCaseNameBelongTo;
    private TestStepStatus stepStatus;
    String[] stepData;

    /**
     * @param testCaseID
     * @param step
     * @param parent
     * @param testObject
     * @param stepData
     * @param StepAction
     * 
     */
    public StepDetails(String tcName, String step, String parent, String testObject, String[] stepData,
	    String StepAction, TestStepStatus stepStatus) {
	this.testCaseNameBelongTo = tcName;
	this.step = step;
	this.parent = parent;
	this.testObject = testObject;
	this.stepData = stepData;
	this.StepAction = StepAction;
	/*
	 * Currently we are using env.testStepStatus variable instead of this.
	 * Down the line we should implement code to use this variable
	 * 
	 * currentIssue: to validate testCase result
	 */
	this.stepStatus = stepStatus;
    }

    /**
     * @return the step
     */
    public String getStep() {
	return step;
    }

    /**
     * @return the parent
     */
    public String getParent() {
	return parent;
    }

    public void setStepStatus(TestStepStatus stepStatus) {
	this.stepStatus = stepStatus;
    }

    public TestStepStatus getStepStatus() {
	return stepStatus;
    }

    /**
     * @return the testObject
     */
    public String getTestObject() {
	return testObject;
    }

    /**
     * @return the dataContent
     */
    public String[] getDataContent() {
	return stepData;
    }

    /**
     * @return the stepAction
     */
    public String getStepAction() {
	return StepAction;
    }

    /**
     * @return the testCaseNameBelongTo
     */
    public String getTestCaseNameBelongTo() {
	return testCaseNameBelongTo;
    }

}
