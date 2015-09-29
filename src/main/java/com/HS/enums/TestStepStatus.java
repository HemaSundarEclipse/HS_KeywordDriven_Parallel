/**
 * 
 */
package com.HS.enums;

/**
 * @author hemasundar
 *
 */
public enum TestStepStatus {

    INITIAL(0), PASS(2), FAIL(4), WARNING(3), IGNORE(1), SKIP(1);
    public int value;

    private TestStepStatus(int value) {
	this.value = value;
    }
}