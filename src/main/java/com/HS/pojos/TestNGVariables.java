/**
 * 
 */
package com.HS.pojos;

import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * @author hemasundar
 *
 */
public class TestNGVariables {
    private static TestNGVariables INSTANCE;
    ISuite suite;
    ITestContext context;

    /**
     * 
     */
    private TestNGVariables() {
	// TODO Auto-generated constructor stub
    }

    public synchronized ISuite getSuite() {
	return suite;
    }

    public synchronized void setSuite(ISuite suite) {
	this.suite = suite;
    }

    public synchronized ITestContext getContext() {
	return context;
    }

    public synchronized void setContext(ITestContext context) {
	this.context = context;
    }

    /**
     * 
     */
    public static TestNGVariables getInstance() {
	if (INSTANCE == null) {

	    INSTANCE = new TestNGVariables();
	}
	return INSTANCE;

    }
}
