package com.HS.practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class WhatsAppWeb {
    public static void main(String[] args) {

	ProfilesIni profile = new ProfilesIni();
	FirefoxProfile remoteProfile = profile.getProfile("TribuneAutomation");
	WebDriver localDriver = new FirefoxDriver(remoteProfile);
	localDriver.get("https://web.whatsapp.com/");
	localDriver.findElement(By.xpath("//div[@class='chat-title' and span='tribune']")).click();
	List<WebElement> totalMessageList = localDriver.findElements(By.xpath("//*[@class='message-list']/div"));
	int noMessages = totalMessageList.size();
	System.out.println(localDriver
		.findElement(By.xpath(
			"//*[@class='message-list']/div[" + (noMessages) + "]//div[@class='message-text']/span[2]"))
		.getText());
    }
}
