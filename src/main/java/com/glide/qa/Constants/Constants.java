package com.glide.qa.Constants;


public class Constants 
{
	public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
	public static final String Edge_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/msedgedriver.exe";
	public static final String FIREFOX_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/geckodriver.exe";
	public static final String CHROME_DRIVER_PATH_MAC = System.getProperty("user.dir") + "/DriversMac/chromedriver.exe";

	public static final long PAGE_LOAD_TIMEOUT = 30;
	public static final long IMPLICIT_WAIT = 20;
	public static final long EXPLICIT_WAIT = 20;

	public static final int SHORT_WAIT = 10000;
	public static final int MEDIUM_WAIT = 20000;
	public static final int LONG_WAIT = 30000;
	public static final int LONG_WAIT_InMinutes = 10;
	public static final String TESTDATASHEETNAME= "TestData";
	public static final String LOGIN_PAGE_TITLE = "Glide";
	public static final String HOME_PAGE_TITLE = "Glide";

	public static final String Path_TestData = System.getProperty("user.dir") + "/src/main/java/com/glide/qa/TestData/GlideTestCases.xlsx";
	public static final String Master_Sheet = "Master";
	public static final String TEST_DATA_SHEET_PATH =System.getProperty("user.dir") + "/src/main/java/com/glide/qa/TestData/GlideTestData.xlsx";
}



