/**
 * 
 */
package com.HS.practise;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author hemasundar
 *
 */
public class GridRestRequest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	capabilities.setPlatform(Platform.ANY);
	WebDriver drive = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
	drive.get("http://facebook.com/");
	System.out.println("url redirected successfully");
	System.out.println(((RemoteWebDriver) drive).getCapabilities());
	System.out.println(((RemoteWebDriver) drive).getSessionId());

	HttpURLConnectionExample req = new HttpURLConnectionExample();
	req.sendGet("/session/" + ((RemoteWebDriver) drive).getSessionId().toString());

	for (int i = 0; i <= 180; i++) {
	    Thread.sleep(5000);
	    drive.getTitle();
	}

	System.out.println("Waited for 30 min");
	drive.close();
    }

}
