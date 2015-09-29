/**
 * 
 */
package com.HS.common;

import java.net.InetAddress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.HS.utils.Log;

/**
 * @author hemasundar
 *
 */
public class CommonSeleniumActions extends DriverConfiguration {
    public Log logger;
    public ExecutionEnvironment env;
    public TestObject currentStepTestObject;

    /**
     * @param currentStepTestObject
     * 
     */
    public CommonSeleniumActions(TestObject currentStepTestObject, ExecutionEnvironment env) {
	super(env);
	this.env = env;
	this.currentStepTestObject = currentStepTestObject;
	logger = new Log(getClass().getSimpleName());
	/**
	 * If the driver (field variable) is null, i.e. need to retain the state
	 * of the driver object from ExecutionEnvironment class variable.
	 * 
	 * If the driver (field variable & ExecutionEnvironment class variable)
	 * both are null, i.e. need to initialize the driver object from
	 * ExecutionEnvironment class variable.
	 */
	if (driver == null) {
	    if (env.driver == null) {
		initDriver();
	    } else {
		driver = env.driver;
	    }
	}
    }

    public void openBrowser(String Url) throws Exception {
	try {
	    // this.deleteAllCookies();
	    logger.info("Current ViewPort is: " + driver.manage().window().getSize().toString());
	    logger.info("Computer Name is : " + InetAddress.getLocalHost().getHostName());

	    logger.info("Opening..." + Url);
	    driver.get(Url);

	    logger.info("New ViewPort is: " + driver.manage().window().getSize().toString());

	    env.testStepStatus = TestStepStatus.PASS;
	    env.driver = driver;
	} catch (Exception e) {
	    env.testStepStatus = TestStepStatus.FAIL;
	    throw e;
	}

    }

    public void sendKeys(String Text) throws Exception {
	try {
	    getElement(currentStepTestObject).clear();
	    getElement(currentStepTestObject).sendKeys(Text);
	} catch (Exception e) {
	    // exceptionMessage(e);
	    throw e;
	}
    }

    public boolean verifyText(String expected, String actual) throws Exception {
	try {
	    if (actual.contains(expected)) {
		return true;
	    } else {
		return false;
	    }
	} catch (Exception e) {
	    // exceptionMessage(e);
	    return false;
	}
    }

    public boolean verifyText(String expected) throws Exception {
	try {

	    verifyText(expected, getElement(currentStepTestObject).getText());
	    return true;
	} catch (Exception e) {
	    // exceptionMessage(e);
	    return false;
	}
    }

    public WebElement getElement(TestObject elementDetails) throws Exception {

	WebElement we = driver.findElement(byLocator(elementDetails));
	return null;

    }

    public By byLocator(TestObject elementDetails) throws Exception {
	String attr = elementDetails.getHow().toLowerCase();
	String Attribute = elementDetails.getWhat();
	// String attr = AttributeType.toLowerCase();
	By by = null;

	try {
	    switch (attr) {
	    default:
		throw new Exception("Incorrect Attribute type mentioned");
	    case "css":
		by = By.cssSelector(Attribute);
		break;
	    case "xpath":
		by = By.xpath(Attribute);
		break;
	    case "class":
		by = By.className(Attribute);
		break;
	    case "id":
		by = By.id(Attribute);
		break;
	    case "name":
		by = By.name(Attribute);
		break;
	    case "text":
		by = By.linkText(Attribute);
		break;
	    }
	} catch (Exception e) {
	    // exceptionMessage(e);
	}
	return by;
    }

}