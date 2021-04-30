package com.glide.qa.Pages;


import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.glide.qa.Reports.ExtentReportSetup;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.glide.qa.BaseClass.TestBase;
import com.glide.qa.Utilities.TestUtility;
import com.glide.qa.Utilities.Xls_Reader;
import com.glide.qa.Constants.Constants;

public class LoginPage extends TestBase {
	
	
	@FindBy(xpath = "//a[text()='I already have an account']")
	WebElement AlreadyHaveAccountLink;
	
	@FindBy(xpath = "//input[@type='email']")
	WebElement EmailTxtFld;
	
	@FindBy(xpath = "//span[text()='Sign in with Email']")
	WebElement SignWithEmailBtn;
	
	
	String LoginToGlideSheet="LoginToGlide";
	public static String loginEmail;
	public static String host;
	public static String mailStoreType;
	public static String username;
	public static String password;
	
	public static final String Datasheetpath = Constants.TEST_DATA_SHEET_PATH;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
		if (host==null || mailStoreType ==null||username==null ||password==null || loginEmail ==null ) 
		{

			try {
				getlogindata();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void getlogindata() throws IOException
	{
		Xls_Reader ReadcolumnNC= new Xls_Reader(Datasheetpath);  
		
		loginEmail = ReadcolumnNC.getCellData(LoginToGlideSheet,"EmailLogin",2);
		host = ReadcolumnNC.getCellData(LoginToGlideSheet,"host",2);
		mailStoreType = ReadcolumnNC.getCellData(LoginToGlideSheet,"mailStoreType",2);
		username = ReadcolumnNC.getCellData(LoginToGlideSheet,"username",2);
		password= ReadcolumnNC.getCellData(LoginToGlideSheet,"password",2);
	
	}

	public void ClickOnAlreadyHaveAccountLink() {
		AlreadyHaveAccountLink.click();
	}
	public void ClickOnSignWithEmailBtn() {
		SignWithEmailBtn.click();
	}

	public void loginwithExistingGmail() throws InterruptedException {
		//Click on Already have account
		ClickOnAlreadyHaveAccountLink();
		Thread.sleep(5000);
		//Enter existing email
		EmailTxtFld.click();
		EmailTxtFld.sendKeys(loginEmail);
		ExtentReportSetup.extentTest.log(Status.INFO,MarkupHelper.createLabel("Login Email is:: " +loginEmail,ExtentColor.GREEN));
		Thread.sleep(5000);
		//Click on sign in with email
		ClickOnSignWithEmailBtn();
		Thread.sleep(30000);
	}

	

}



