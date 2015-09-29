/**
 * 
 */
package com.HS.common;

/**
 * @author hemasundar
 *
 */
public class TestCaseLocation {
    private int TCRowNumber;
    private String TCName;

    /**
     * 
     */
    public TestCaseLocation(String TCName, int TCRowNumber) {
	this.TCName = TCName;
	this.TCRowNumber = TCRowNumber;
    }

    public int getTCRowNumber() {
	return TCRowNumber;
    }

    public String getTCName() {
	return TCName;
    }

}
