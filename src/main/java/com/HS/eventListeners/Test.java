package com.HS.eventListeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Test {
    @org.testng.annotations.Test
    public void test1() throws ArrayIndexOutOfBoundsException {
	WebDriver driver = new FirefoxDriver();
	EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
	WebDriverEventHandler handler = new WebDriverEventHandler();
	eDriver.register(handler);

	eDriver.get("http://google.com");

	try {
	    // test

	} catch (Exception e) {
	    // TODO: handle exception
	}
    }
}