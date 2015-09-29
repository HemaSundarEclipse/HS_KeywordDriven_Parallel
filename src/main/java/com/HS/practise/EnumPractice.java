/**
 * 
 */
package com.HS.practise;

/**
 * @author hemasundar
 *
 */
public class EnumPractice {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println(TestStepStatus.valueOf("FAIL"));
	TestStepStatus test = TestStepStatus.IGNORE;
	System.out.println("individual value for IGNORE is " + test.value);
	TestStepStatus[] values = TestStepStatus.values();
	for (TestStepStatus testStepStatus : values) {
	    System.out.println(testStepStatus.value);
	    // System.out.println(testStepStatus.valueOf("FAIL"));
	}
    }

}

/**
 * @author hemasundar
 *
 */
enum TestStepStatus {
    PASS(1), FAIL(2), WARNING(3), IGNORE(4), SKIP(5);
    public int value;

    private TestStepStatus(int value) {
	this.value = value;
    }
}
