/**
 * 
 */
package com.HS.common;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.HS.utils.Log;

/**
 * @author hemasundar
 *
 */
public class DriverConfiguration {
    public Log logger;
    ParseInputData testData;
    public ExecutionEnvironment env;
    WebDriver driver;

    /**
     * 
     */
    public DriverConfiguration(ExecutionEnvironment env) {
	this.env = env;
	logger = new Log(getClass().getSimpleName());
	testData = new ParseInputData();
    }

    public WebDriver initDriver() {
	try {
	    if (env.isRemoteExecution.equalsIgnoreCase("true")) {
		logger.info("Remote execution is true");
		String remoteURL = env.remoteURL + "/wd/hub";
		URL uri = new URL(remoteURL);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		/*
		 * capabilities =
		 * creatingCapabilityForRemoteBrowser(capabilities);
		 * creatingProfileForRemoteBrowser(capabilities, uri);
		 */
		driver = new RemoteWebDriver(uri, capabilities);
		/*
		 * driver.manage().timeouts() .implicitlyWait(1500,
		 * TimeUnit.MILLISECONDS);
		 */
		driver.manage().timeouts().pageLoadTimeout(1500, TimeUnit.SECONDS);
	    } else {
		logger.info("Remote execution is false");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (env.browserName.equalsIgnoreCase("firefox")) {
		    FirefoxProfile ffprofile;
		    ffprofile = new FirefoxProfile();
		    ffprofile.setPreference("webdriver_assume_untrusted_issuer", "false");
		    driver = new FirefoxDriver(ffprofile);

		} else if (env.browserName.equalsIgnoreCase("internet explorer")) {
		    final File file = new File("src/main/resources/drivers/IEDriverServer.exe");
		    System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		    final DesiredCapabilities iecapabilities = DesiredCapabilities.internetExplorer();
		    iecapabilities.setCapability(
			    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		    driver = new InternetExplorerDriver(iecapabilities);

		} else if (env.browserName.equalsIgnoreCase("chrome")) {
		    DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		    chromeCapabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
		    chromeCapabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
		    ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.addArguments(Arrays.asList("--test-type"));
		    chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		    // Check if the OS is Mac and use OS X Chromedriver
		    if (System.getProperty("os.name").contains("Mac")) {
			ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort()
				.usingDriverExecutable(new File("src/main/resources/drivers/chromedriver")).build();
			service.start();
			driver = new ChromeDriver(service, chromeCapabilities);

		    } else {
			ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort()
				.usingDriverExecutable(new File("src/main/resources/drivers/chromedriver.exe")).build();
			service.start();
			driver = new ChromeDriver(service, chromeCapabilities);
		    }
		} else if (env.browserName.equalsIgnoreCase("safari")) {
		    System.setProperty("webdriver.safari.noinstall", "true");
		    driver = new SafariDriver();
		}
	    }
	} catch (NullPointerException e) {
	    // env.setStepDescription("Exception occurred at driver
	    // initialization: " + e.getMessage());
	    // env.setRemarks("Driver initialization failed. ");
	    logger.error("Exception occurred at driver initialization: " + e.getMessage());
	} catch (Exception e) {
	    // env.setStepDescription("Exception occurred at driver
	    // initialization: " + e.getMessage());
	    // env.setRemarks("Driver initialization failed. ");
	    logger.error("Exception occurred at driver initialization: " + e.getMessage());
	}

	driver.manage().timeouts().implicitlyWait(env.maxTimeOut, TimeUnit.SECONDS);
	return driver;
    }

}
