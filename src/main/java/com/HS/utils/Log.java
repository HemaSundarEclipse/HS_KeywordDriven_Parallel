package com.HS.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
    private Logger logger;

    /**
     * 
     */
    public Log(String className) {
	logger = Logger.getLogger(className);
    }

    // Initialize Log4j logs

    static {
	DOMConfigurator.configure("log4j.xml");
    }

    // This is to print log for the beginning of the test case, as we usually
    // run so many test cases as a test suite
    public void startTestCase(String sTestCaseName) {

	logger.info("****************************************************************************************");

	logger.info("****************************************************************************************");

	logger.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");

	logger.info("****************************************************************************************");

	logger.info("****************************************************************************************");

    }

    // This is to print log for the ending of the test case

    public void endTestCase(String sTestCaseName) {

	logger.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");

	logger.info("X");

	logger.info("X");

	logger.info("X");

	logger.info("X");

    }

    // Need to create these methods, so that they can be called

    public void info(String message) {

	logger.info(message);

    }

    public void warn(String message) {
	logger.warn(message);
    }

    public void error(String message) {

	logger.error(message);

    }

    public void fatal(String message) {

	logger.fatal(message);

    }

    public void debug(String message) {

	logger.debug(message);

    }

}