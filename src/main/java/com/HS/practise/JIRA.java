package com.HS.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JIRA {
    public static WebDriverWait wait = null;
    static WebDriver driverJira;
    static WebElement testObject;

    public static void main(String[] args) throws Exception {
	FirefoxProfile ffprofile;
	ProfilesIni profile = new ProfilesIni();
	ffprofile = profile.getProfile("default");
	driverJira = new FirefoxDriver(ffprofile);
	driverJira.get("https://port-212-202-124-81.static.qsc.de/jira/secure/ViewProfile.jspa?name=r.sanka");
	Thread.sleep(3000);
	driverJira.switchTo().frame(driverJira.findElement(By.xpath("//iframe[@id='gadget-1']")));
	// Thread.sleep(5000);
	wait = new WebDriverWait(driverJira, 20);

	waitAndGetElement();

	/*
	 * final WebElement showMore = driverJira.findElement(By
	 * .id("activity-stream-show-more"));
	 */

	while (testObject.isDisplayed()) {
	    waitAndGetElement();
	    System.out.println("isDisplayed: " + testObject.isDisplayed());
	    testObject.click();
	    Thread.sleep(3000);
	    Boolean myDynamicElement = (new WebDriverWait(driverJira, 20))
		    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".throbber-placeholder")));
	    waitAndGetElement();
	}
    }

    public static WebElement waitAndGetElement() throws Exception {
	try {
	    wait.until(new ExpectedCondition<WebElement>() {
		@Override
		public WebElement apply(WebDriver d) {
		    try {
			testObject = getElement();
			return testObject;
		    } catch (Exception e) {
			return null;
		    }

		}
	    });
	    return testObject;

	} catch (TimeoutException exception) {
	    throw new NoSuchElementException("Unable to locate element");

	}
	// catch (Exception e) {
	// throw e;
	// }
    }

    public static WebElement getElement() throws Exception {
	try {
	    testObject = driverJira.findElement(By.xpath("//a[span='Show more...']"));
	} catch (Exception e) {
	    throw e;
	}
	return testObject;
    }
}
