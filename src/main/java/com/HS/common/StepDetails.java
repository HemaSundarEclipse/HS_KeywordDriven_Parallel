/**
 * 
 */
package com.HS.common;

/**
 * @author Hema
 *
 */
public class StepDetails {
    private final String step, parent, testObject, StepAction, testCaseNameBelongTo;
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
	    String StepAction) {
	this.testCaseNameBelongTo = tcName;
	this.step = step;
	this.parent = parent;
	this.testObject = testObject;
	this.stepData = stepData;
	this.StepAction = StepAction;
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
