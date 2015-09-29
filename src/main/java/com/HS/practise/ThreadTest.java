package com.HS.practise;

import org.testng.annotations.Test;

import com.HS.scripts.TestEngine;

public class ThreadTest {
    private TestEngine objTE_private_Field = new TestEngine();
    public TestEngine objTE_public_Field = new TestEngine();
    TestEngine objTE_default_Field = new TestEngine();
    private static TestEngine objTE_private_Field_Static = new TestEngine();
    public static TestEngine objTE_public_Field_Static = new TestEngine();
    static TestEngine objTE_default_Field_Static = new TestEngine();
    Integer i = null;

    @Test
    public void mainTest() {
	// objTE_private_Field = new TestEngine();
	// objTE_public_Field = new TestEngine();
	// objTE_default_Field = new TestEngine();
	// // objTE_private_Field_Static = new TestEngine();
	// objTE_public_Field_Static = new TestEngine();
	// objTE_default_Field_Static = new TestEngine();
	// objTE_ThreadLocal = new ThreadLocal<TestEngine>();
	// objTE_ThreadLocal_static = new ThreadLocal<TestEngine>();
	System.out.println("Object value for objTE_private_Field for thread " + Thread.currentThread().getId() + " is "
		+ objTE_private_Field);
	System.out.println("Object value for objTE_public_Field for thread " + Thread.currentThread().getId() + " is "
		+ objTE_public_Field);
	System.out.println("Object value for objTE_default_Field for thread " + Thread.currentThread().getId() + " is "
		+ objTE_default_Field);
	System.out.println("Object value for objTE_private_Field_Static for thread " + Thread.currentThread().getId()
		+ " is " + objTE_private_Field_Static);
	System.out.println("Object value for objTE_public_Field_Static for thread " + Thread.currentThread().getId()
		+ " is " + objTE_public_Field_Static);
	System.out.println("Object value for objTE_default_Field_Static for thread " + Thread.currentThread().getId()
		+ " is " + objTE_default_Field_Static);

    }
}
