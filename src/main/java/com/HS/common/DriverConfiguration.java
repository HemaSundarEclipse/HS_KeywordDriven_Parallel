/**
 * 
 */
package com.HS.common;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.HS.utils.Log;

import io.appium.java_client.remote.MobileCapabilityType;

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

		capabilities = creatingCapabilityForRemoteBrowser(capabilities);
		// creatingProfileForRemoteBrowser(capabilities, uri);

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
		    if (System.getenv("os.name").contains("Mac")) {
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

    private DesiredCapabilities creatingCapabilityForRemoteBrowser(DesiredCapabilities capabilities) {
	if (this.env.browserName.equalsIgnoreCase("IE")) {
	    // objLog.writeInfo("Remote browser is internet explorer");
	    capabilities = DesiredCapabilities.internetExplorer();
	    capabilities.setPlatform(Platform.ANY);
	    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    capabilities.setCapability("ensureCleanSession", true);
	    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

	} else if (this.env.browserName.equalsIgnoreCase("safari")) {
	    // objLog.writeInfo("Remote browser is safari");
	    System.setProperty("webdriver.safari.noinstall", "true");
	    capabilities = DesiredCapabilities.safari();
	    capabilities.setCapability("webdriver.safari.noinstall", "true");
	    capabilities.setPlatform(Platform.ANY);
	} else if (this.env.browserName.equalsIgnoreCase("firefox")) {
	    // objLog.writeInfo("Remote browser is firefox");
	    capabilities = DesiredCapabilities.firefox();
	    capabilities.setPlatform(Platform.ANY);

	} else if (this.env.browserName.equalsIgnoreCase("chrome")) {
	    // objLog.writeInfo("Remote browser is Chrome");
	    capabilities = DesiredCapabilities.chrome();
	    capabilities.setPlatform(Platform.ANY);
	} else if (this.env.browserName.equalsIgnoreCase("iphonePortrait")) {

	    /*********************************************************************************************************************
	     * Capabilities for TestDroid *
	     *********************************************************************************************************************/
	    // capabilities.setCapability("testdroid_username",
	    // "hpenugonda1988@gmail.com");
	    // capabilities.setCapability("testdroid_password", "tribune1");
	    // capabilities.setCapability("testdroid_target", "Safari");
	    // capabilities.setCapability("testdroid_project", "TribuneIOS");
	    // capabilities.setCapability("testdroid_testrun",
	    // "TribuneIOS_testRun_" + Calendar.getInstance().getTime()
	    // + "_" + Thread.currentThread().getId());
	    // capabilities.setCapability("testdroid_device",
	    // "iPhone 5 A1429 6.1.4");
	    // capabilities.setCapability("testdroid_junitWaitTime", "290");
	    /*********************************************************************************************************************/
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Safari");
	    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10 * 60);
	    capabilities.setCapability("safariIgnoreFraudWarning", "true");
	} else if (this.env.browserName.equalsIgnoreCase("iphoneLandscape")) {

	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Safari");
	    capabilities.setCapability("safariIgnoreFraudWarning", "true");
	    capabilities.setCapability("orientation", "LANDSCAPE");
	} else if (this.env.browserName.equalsIgnoreCase("ipadPortrait")) {

	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPad 2");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Safari");
	    capabilities.setCapability("safariIgnoreFraudWarning", "true");
	} else if (this.env.browserName.equalsIgnoreCase("ipadLandscape")) {

	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPad 2");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Safari");
	    capabilities.setCapability("safariIgnoreFraudWarning", "true");
	    capabilities.setCapability("orientation", "LANDSCAPE");
	} else if (this.env.browserName.equalsIgnoreCase("androidPhonePortrait")
		|| this.env.browserName.equalsIgnoreCase("androidPhoneLandscape")) {

	    /*********************************************************************************************************************
	     * Capabilities for TestDroid *
	     *********************************************************************************************************************/
	    capabilities.setCapability("testdroid_username", "hpenugonda1988@gmail.com");
	    capabilities.setCapability("testdroid_password", "tribune1");
	    capabilities.setCapability("testdroid_target", "Chrome");
	    capabilities.setCapability("testdroid_project", "DemoAndroid");
	    capabilities.setCapability("testdroid_testrun",
		    "TribuneDemo_testRun_" + Calendar.getInstance().getTime() + "_" + Thread.currentThread().getId());
	    capabilities.setCapability("testdroid_device", "Samsung Galaxy Nexus SPH-L700 4.3");
	    /*********************************************************************************************************************/
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.3");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy Nexus SPH-L700 4.3");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	    // capabilities.setCapability("avd", "AndroidPhone");
	    // capabilities.setCapability(
	    // MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10 * 60);

	} else if (env.browserName.equalsIgnoreCase("androidTabletPortrait")
		|| env.browserName.equalsIgnoreCase("androidTabletLandscape")) {

	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Nexus 7 HD Emulator");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	    // capabilities.setCapability("avd", "AndroidTablet");
	    // capabilities.setCapability(
	    // MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10 * 60);
	} else if (env.browserName.equalsIgnoreCase("androidDeviceLandscape")) {

	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy Grand");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	    // capabilities.setCapability(
	    // MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10 * 60);

	}
	return capabilities;
    }

}
