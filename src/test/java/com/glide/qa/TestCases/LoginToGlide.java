package com.glide.qa.TestCases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.glide.qa.Utilities.TestUtility;
import com.glide.qa.BaseClass.TestBase;
import com.glide.qa.Pages.HomePage;
import com.glide.qa.Pages.LoginPage;
import com.glide.qa.Utilities.GmailTextReading;
import com.glide.qa.Reports.ExtentReportSetup;
import com.aventstack.extentreports.Status;


@Listeners(com.glide.qa.TestListeners.ExtentReportListener.class)
public class LoginToGlide extends TestBase {
	
	TestUtility testUtil;
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginToGlide()
	{
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp()
	{
		initialization();
		testUtil = new TestUtility();
		loginpage = new LoginPage();
		homepage = new HomePage();
		
	}
	
	@Test()
	public void VerifyLoginToGlideWithExistingEmail() throws Exception {
		
		loginpage.loginwithExistingGmail();
		GmailTextReading.GetEmailLink();
		String EmaillinkToLogin =	GmailTextReading.SigninUrl;
		System.out.println("Email link to login " + EmaillinkToLogin);
		ExtentReportSetup.extentTest.log(Status.INFO, "Email link to login" + EmaillinkToLogin);
		driver.get(EmaillinkToLogin);
		Thread.sleep(45000);
		Assert.assertTrue(homepage.VerifyHomePageAfterLoginInGlide(), "Glide Home page is displayed");
		ExtentReportSetup.extentTest.log(Status.INFO, "Glide Home page is displayed");
		System.out.println("Glide Home page is displayed");
		Log.info("Glide Home page is displayed");
		
	}
	
	
}


