package com.glide.qa.BaseClass;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.glide.qa.Constants.Constants;
import com.glide.qa.Utilities.TestUtility;
import com.glide.qa.Utilities.WebEventListener;

public class TestBase
{
	public static WebDriver driver; 
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String url;
	public static Logger Log;
	Robot rb;
	public TestBase()
	{
		Log = Logger.getLogger(this.getClass());
		try 
		{
			property = new Properties();
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/glide/qa/Configuration/Configuration.properties");
			property.load(inputStream);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void setLog4j() throws AWTException
	{

		TestUtility.setDateForLog4j();
		System.out.println("Before test");

	}

	public void initialization()
	{
		String browserName = property.getProperty("Browser");
		if(System.getProperty("os.name").contains("Window")){

			System.out.println("OS Version is "+System.getProperty("os.name"));
			if(browserName.equals("Chrome"))
			{
				chromeOptions = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				prefs.put("download.prompt_for_download", false);
				prefs.put("privacy.popups.disable_from_plugins", 3);
				chromeOptions.setExperimentalOption("prefs", prefs);
				chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);


				driver = new ChromeDriver(chromeOptions);
				Log.info("The browser value is : " +browserName);

			}
			else if(browserName.equals("edge"))
			{
				System.setProperty("webdriver.edge.driver", Constants.Edge_DRIVER_PATH);
				driver = new EdgeDriver();
				Log.info("The browser value is : " +browserName);
			}

		}
		else if(System.getProperty("os.name").contains("Mac")){
			System.out.println("OS Version is "+System.getProperty("os.name"));
			if(browserName.equals("Chrome"))
			{
				chromeOptions = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				prefs.put("download.prompt_for_download", false);
				prefs.put("privacy.popups.disable_from_plugins", 3);
				chromeOptions.setExperimentalOption("prefs", prefs);
				chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));					
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH_MAC);

				driver = new ChromeDriver(chromeOptions);
				Log.info("The browser value is : " +browserName);

			}
		}
		else
		{
			System.out.println("Path of Driver Executable is not Set for any Browser");
			Log.info("Incorrect browser value passed. " +browserName);
		}
		String  environment= property.getProperty("environment");
		if(environment.equals("stage")){       
			url="https://staging.heyglide.com/";     
		}else if(environment.equals("qa")){
			url="https://staging.heyglide.com/";        
		}else if(environment.equals("uat")){
			url="https://staging.heyglide.com/";

		}

		e_driver = new EventFiringWebDriver(driver);

		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(url);
		System.out.println(url);

	}

	@AfterTest
	public void endReport() throws AWTException 
	{


	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() throws IOException, InterruptedException, AWTException
	{


		driver.quit();
		Log.info("Browser Terminated");
		Log.info("-----------------------------------------------");

		Log.info("Closing"); 

		try {
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
