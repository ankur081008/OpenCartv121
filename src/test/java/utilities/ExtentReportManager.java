package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		
		extentSparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		extentSparkReporter.config().setDocumentTitle("Opencart Automation report");
		extentSparkReporter.config().setReportName("opencart functional testing");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Application", "OpenCart");
		extentReports.setSystemInfo("Module", "Admin");
		extentReports.setSystemInfo("Sub Module", "Customer");
		extentReports.setSystemInfo("Environment", "QA");
	}
	
	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, result.getName()+ "got executed successfully");  
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, result.getName()+ "got failed");  
		extentTest.log(Status.INFO, result.getThrowable().getMessage()); 
		
		try {
			String imgpath = new BaseTest().captureScreen(result.getName());
			extentTest.addScreenCaptureFromPath(imgpath);
				 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, result.getName()+ "got skipped");  
		extentTest.log(Status.INFO, result.getThrowable().getMessage()); 
    } 
	
	public void onFinish(ITestContext testContext) {
		extentReports.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"//reports//"+repName;
		 	File extentReport = new File(pathOfExtentReport);
		 	try {
		 		Desktop.getDesktop().browse(extentReport.toURI());
		 		
		 	} catch(IOException e) {
		 		e.printStackTrace();
		 	}
		 	
    } 
}


