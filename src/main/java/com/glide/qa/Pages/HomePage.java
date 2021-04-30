package com.glide.qa.Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.glide.qa.BaseClass.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath="//button[@class='edit-button']/following::h4[text()='Apps']")
	WebElement AppsHeaderTitle;
	

	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}


	public boolean VerifyHomePageAfterLoginInGlide() {
		return AppsHeaderTitle.isDisplayed();
	}
	

	


	
}
