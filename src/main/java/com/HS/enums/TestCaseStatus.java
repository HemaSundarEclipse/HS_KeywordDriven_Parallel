/**
 * 
 */
package com.HS.enums;

/**
 * @author hemasundar
 *
 */
public enum TestCaseStatus {

    PASS(2), FAIL(4), WARNING(3), IGNORE(0), SKIP(1);
    public int value;

    private TestCaseStatus(int value) {
	this.value = value;
    }
}
