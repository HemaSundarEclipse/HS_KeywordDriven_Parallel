package com.HS.eventListeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.HS.utils.Log;

public class WebDriverEventHandler implements WebDriverEventListener {
    public Log logger;

    /**
     * 
     */
    public WebDriverEventHandler() {
	logger = new Log(getClass().getSimpleName());
    }

    @Override
    public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {

	// System.out.println("About to change value of : " +arg0.toString());
	logger.info("About to change value of : " + arg0.toString());
    }

    @Override
    public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {

	// System.out.println("Changed the value for : " + arg0.toString());
	logger.info("Changed the value for : " + arg0.toString());
    }

    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {

	// System.out.println("About to click on the " + arg0.toString());
	logger.info("About to click on the " + arg0.toString());
    }

    @Override
    public void afterClickOn(WebElement arg0, WebDriver arg1) {

	// System.out.println("inside method afterClickOn on " +
	// arg0.toString());
	logger.info("inside method afterClickOn on " + arg0.toString());
    }

    @Override
    public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {

	if (arg1 == null) {
	    // System.out.println("Just before finding element: " +
	    // arg0.toString() + " on browser: " + arg2.toString() );
	    // System.out.println("Just before finding element: " +
	    // arg0.toString());
	    logger.info("Just before finding element: " + arg0.toString());

	} else {
	    // System.out.println("Just before finding element: " +
	    // arg0.toString() + " inside " + arg1.toString() + " Web element on
	    // browser: " + arg2.toString() );
	    // System.out.println("Just before finding element: " +
	    // arg0.toString() + " inside " + arg1.toString() + " Web element");
	    logger.info(
		    "Just before finding element: " + arg0.toString() + " inside " + arg1.toString() + " Web element");
	}

    }

    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {

	if (arg1 == null) {
	    // System.out.println("Found happened on element: " +
	    // arg0.toString() + " on browser: " + arg2.toString() );
	    // System.out.println("Found happened on element: " +
	    // arg0.toString());
	    logger.info("Found happened on element: " + arg0.toString());

	} else {
	    // System.out.println("Found happened on element: " +
	    // arg0.toString() + " inside " + arg1.toString() + " Web element on
	    // browser: " + arg2.toString() );
	    // System.out.println("Found happened on element: " +
	    // arg0.toString() + " inside " + arg1.toString() + " Web element");
	    logger.info(
		    "Found happened on element: " + arg0.toString() + " inside " + arg1.toString() + " Web element");
	}

    }

    @Override
    public void beforeNavigateBack(WebDriver arg0) {

	// System.out.println("Just before beforeNavigateBack " +
	// arg0.getCurrentUrl());
	logger.info("Just before beforeNavigateBack " + arg0.getCurrentUrl());

    }

    @Override
    public void afterNavigateBack(WebDriver arg0) {

	// System.out.println("Inside the after navigateback to " +
	// arg0.getCurrentUrl());
	logger.info("Inside the after navigateback to " + arg0.getCurrentUrl());
    }

    @Override
    public void beforeNavigateForward(WebDriver arg0) {

	// System.out.println("Just before beforeNavigateForward " +
	// arg0.getCurrentUrl());
	logger.info("Just before beforeNavigateForward " + arg0.getCurrentUrl());

    }

    @Override
    public void afterNavigateForward(WebDriver arg0) {

	// System.out.println("Inside the afterNavigateForward to " +
	// arg0.getCurrentUrl());
	logger.info("Inside the afterNavigateForward to " + arg0.getCurrentUrl());
    }

    @Override
    public void beforeNavigateTo(String arg0, WebDriver arg1) {

	// System.out.println("Just before beforeNavigateTo " + arg0);
	logger.info("Just before beforeNavigateTo " + arg0);
    }

    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {

	// System.out.println("Inside the afterNavigateTo to " + arg0);
	logger.info("Inside the afterNavigateTo to " + arg0);
    }

    @Override
    public void beforeScript(String arg0, WebDriver arg1) {

	// System.out.println("Just before beforeScript " + arg0);
	logger.info("Just before beforeScript " + arg0);
    }

    @Override
    public void afterScript(String arg0, WebDriver arg1) {

	// System.out.println("Inside the afterScript to, Script is " + arg0);
	logger.info("Inside the afterScript to, Script is " + arg0);
    }

    @Override
    public void onException(Throwable arg0, WebDriver arg1) {

	// System.out.println("Exception occured at " + arg0.getMessage());
	logger.info("Exception occured at " + arg0.getMessage());

    }
}