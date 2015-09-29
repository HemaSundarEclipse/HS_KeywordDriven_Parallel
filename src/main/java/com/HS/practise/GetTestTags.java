/**
 * 
 */
package com.HS.practise;

import java.util.List;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

/**
 * @author hemasundar
 *
 */
public class GetTestTags implements ISuiteListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.testng.ISuiteListener#onStart(org.testng.ISuite)
     */
    @Override
    public void onStart(ISuite suite) {
	List<XmlTest> tests = suite.getXmlSuite().getTests();
	for (XmlTest xmlTest : tests) {
	    System.out.println(xmlTest.getName());
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.testng.ISuiteListener#onFinish(org.testng.ISuite)
     */
    @Override
    public void onFinish(ISuite suite) {
	// TODO Auto-generated method stub

    }

    @BeforeSuite
    public void BeforeSuite() throws Exception {

	// List<IInvokedMethod> allInvokedMethods =
	// context.getSuite().getAllInvokedMethods();
	// for (IInvokedMethod iInvokedMethod : allInvokedMethods) {
	// System.out.println(iInvokedMethod.getTestMethod().getXmlTest().getName());
	// }
    }

    @Test
    /**
     * 
     */
    private void testName() {
	System.out.println("in test method");

    }

}
