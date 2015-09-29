package com.HS.practise;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Appium {
    static WebDriver driver;

    public static void main(String[] args) throws MalformedURLException {
	DesiredCapabilities caps = new DesiredCapabilities();
	caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "19");
	caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
	caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");
	caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10 * 60);

	driver = new AndroidDriver(new URL("http://10.51.132.205:4723/wd/hub"), caps);
	/*
	 * capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
	 * "com.android.calculator2"); // This is package name of your app //
	 * (you can get it from apk info app
	 * capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
	 * "com.android.calculator2.Calculator"); // This is Launcher //
	 * activity of your app // (you can get it from // apk info app)
	 */// Create AndroidDriver instance and connect to the Appium server.
	  // It will launch the Calculator App in Android Device using the
	  // configurations specified in Desired Capabilities

	driver.navigate().to("http://google.com");
	driver.close();
	driver.quit();

    }

}
