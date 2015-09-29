package com.HS.practise;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Sample extends RemoteWebDriver {

    public static void main(String[] args) {
	FirefoxDriver dri = new FirefoxDriver();
	dri.get("http://nguxbeta:nguxtr!b@ngux.baltimoresun.stage.tribdev.com/qa/automation/outfit11A/250/3/");
	/*
	 * WebElement OutFitImage = dri.findElement(By
	 * .cssSelector("section.trb_outfit")); Actions act = new Actions(dri);
	 * act.moveToElement(OutFitImage).build().perform(); testDriver.Actions
	 * localact = new testDriver.Actions(dri); try {
	 * localact.cutRequiredImage("Sample"); } catch (Exception e) {
	 * e.printStackTrace(); }
	 */
	dri.findElement(By.cssSelector(".trb_outfit_sponsorship_logo_img")).click();
	System.out.println(dri.findElement(By.cssSelector(".trb_outfit_sponsorship_logo_img")).getSize());
	List<WebElement> dropDownValues = dri.findElements(By.xpath("//select[@id='Customer']"));
	ArrayList<String> dropDownValuesasText = null;
	for (WebElement eachValue : dropDownValues) {
	    dropDownValuesasText.add(eachValue.getText());
	}
	boolean result = dropDownValuesasText.contains("Your FirstName & Email value");
	// It will pass if your value is present in drop down
	// assertNotEquals(dropDownValuesasText.contains("Your FirstName & Email
	// value"),
	// true);
    }
}
