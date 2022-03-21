package com.demo.utility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demo.base.BaseClass;


public class ExtentManager extends BaseClass{
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@Test
	public static void setExtent() {
//		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
//		ExtentReports extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
		
		ExtentHtmlReporter report = new ExtentHtmlReporter("extentReport.html");
 		extent = new ExtentReports();
 		extent.attachReporter(report);
		
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"DemoReport.html");
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Demo Test Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "DemoProject");
		extent.setSystemInfo("Tester", "Tareq");
		extent.setSystemInfo("OS", "MacOS");
		extent.setSystemInfo("Browser", "Chrome");
		
		extent.flush();
	}
	
	
//	@AfterMethod(alwaysRun = true)
//	public void endReport() {
//	    extent.flush();
//	}
}

