package com.glide.qa.Reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.glide.qa.BaseClass.TestBase;
import com.glide.qa.Utilities.TestUtility;

public class ExtentReportSetup extends TestBase
{	
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static ExtentSparkReporter sparkReport;

	public static ExtentReports extentReportSetup()
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
		String strDate= formatter.format(date);
		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + "/GlideExtentResults/"+strDate+"/GlideExtentReport" + TestUtility.getSystemDate() + ".html");

		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		sparkReport.loadXMLConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
		sparkReport.getAnalysisStrategy();
		extent.setSystemInfo("Application Name", "Glide Automation ExtentReport");
		extent.setSystemInfo("Platform", System.getProperty("os.name"));
		extent.setSystemInfo("Browser",property.getProperty("Browser"));
		extent.setSystemInfo("Environment",property.getProperty("environment"));




		return extent;
	}
}
