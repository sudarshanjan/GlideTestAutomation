package com.glide.qa.TestListeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.glide.qa.Reports.ExtentReportSetup;
import com.glide.qa.Utilities.TestUtility;

public class ExtentReportListener extends ExtentReportSetup implements ITestListener


{

	private static String TestcaseName;
	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}
	public void onTestStart(ITestResult result) 
	{
		TestcaseName =result.getMethod().getDescription();
		setTestcaseName(TestcaseName);
		extentTest = extent.createTest(result.getMethod().getMethodName());
		extentTest.log(Status.INFO,  MarkupHelper.createLabel("Test is Started ::: " +result.getMethod().getMethodName(),ExtentColor.PURPLE));

	}

	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.PASS,MarkupHelper.createLabel("Test Case Passed ::: " +result.getMethod().getMethodName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult result) 
	{
		extentTest.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed ::: " +result.getMethod().getMethodName(), ExtentColor.RED));
		extentTest.log(Status.FAIL, result.getThrowable());

		try 
		{
			extentTest.addScreenCaptureFromPath(TestUtility.getScreenshot(driver, result.getMethod().getMethodName()));
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		extentTest.log(Status.SKIP, MarkupHelper.createLabel("Test Case Skipped ::: " +result.getMethod().getMethodName(), ExtentColor.ORANGE));

		try 
		{
			extentTest.addScreenCaptureFromPath(TestUtility.getScreenshot(driver, result.getMethod().getMethodName()));
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{

	}

	public void onStart(ITestContext context) 
	{
		extent = ExtentReportSetup.extentReportSetup();
	}

	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
}

