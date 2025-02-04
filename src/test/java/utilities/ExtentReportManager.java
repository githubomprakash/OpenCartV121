package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{  //implementing ITestListener interface to implement all default method
	
	
	//Main 3 classes of the extent report, Creating variable on class level to access in multiple method
	public ExtentSparkReporter sparkReporter;  //UI of the report
	public ExtentReports extent;  //populate common info of the report
	public ExtentTest test;  ///Creating TCs enteries in the report
	
	String repName;
	
	
	public void onStart(ITestContext testContext) {  //Implementing pre-defined method from ITestListener class and will store result in testContext Variable
	    
		
		//Creating object for SimpleDateFormat to get report in current timeStamp
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date(0);  //Creating Data
		String currentDateTimeStamp= df.format(dt);  //Passing date in format and its returning in String format
		*/
		
		//Combined above in single step
		String TimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + TimeStamp + ".html";   //Concatnating timestamp with name.html
		sparkReporter = new ExtentSparkReporter(".\\reports\\" +repName);  //Creating Object for ExtentSparkReporter and Specifying location of the report
		
		
		//Under ExtentSparkReporter for UI presentation
		sparkReporter.config().setDocumentTitle("Open Cart Automation Report");  //Title of Report
		sparkReporter.config().setReportName("OpenCart Functional Testing"); //Name of Report
		sparkReporter.config().setTheme(Theme.DARK);
		
		//Under ExtentReports for common info, Creating Object of ExtentReports Class
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));  //Getting tester Name Dynamically
		extent.setSystemInfo("Environment", "QA");
		
		//Capturing below detail from testNG.xml file to show in report
		String os = testContext.getCurrentXmlTest().getParameter("os");  //Getting Operating System from testNGxml file dynamacally
		extent.setSystemInfo("Operating Syatem", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser"); //Getting Browser name from testNGxml file dynamacally
		extent.setSystemInfo("Browser", browser);
		
		List <String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();  //From Which groups executing Testcase from testNG.xml file from included section
		if(!includedGroups.isEmpty()) //If not running any groups it will not execute
		{
		extent.setSystemInfo("Groups", includedGroups.toString()); //if group available than will add in report
		}
	  }
	
	public void onTestSuccess(ITestResult result) {  //when ITestResult will be trigrred results will be in stored in result variable
		
		test = extent.createTest(result.getTestClass().getName());  //From Result getting class and from class getting name
		test.assignCategory(result.getMethod().getGroups());  //From Which method and which group test executing, To Display group in report
		test.log(Status.PASS, result.getName()+ " Got Successfully Executed ");  //Printing Status 
	  }
	
	public void onTestFailure(ITestResult result) {
	   
		test = extent.createTest(result.getTestClass().getName());  
		test.assignCategory(result.getMethod().getGroups());  //From Which method and which group test executing, To Display group in report
		
		test.log(Status.FAIL, result.getName()+ " Got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());  //If TCs failed printing Error Message on report
		
		//Here If Testcase got failed taking Screeenshot
		try  
		{
			String imgpath = new BaseClass().captureScreen(result.getName()); //Created object of BaseClass() to invoked captureScreen method present 
			test.addScreenCaptureFromPath(imgpath);  //Taken Screenshot storing in imgpath variable and will attached to report
			
		} 
		catch (IOException e1)  //If screenshot not available it will thrown file not found exception
		{
			e1.printStackTrace();  //it will print some warning msg
		}
		
	  }
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " Got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage()); 
	    
	  }
	
	public void onFinish(ITestContext context) {
	    
		extent.flush();  //Generating Report
		
		//Want to open report automatically without going to right click and open with browser as soon as execution finished
		String pathOfExtentReport = System.getProperty("user.dir")+ "\\reports\\" +repName;   //Path fo report
		File extentReport = new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI()); //Desktop is predefined it will open report on browser automatically
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
//		//If want to send report to an email as soon as report generated
//		try {
//			
//			URL url = new URL("file:///"+System.getProperty("user.dir")+ "\\reports\\" +repName); //converting into URL as report will open in browser in url format
//			
//			//Create Email Message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.googlemail.com");  //Its depend upon email server, it will change as per ur email
//			email.setSmtpPort(465);  //Its depend upon email server, it will change as per ur email
//			email.setAuthenticator(new DefaultAuthenticator("omprakashsah36@gmail.com", "password"));  //Sender email and pwd
//			email.setSSLOnConnect(true);
//			email.setFrom("omprakashsah36@gmail.com");  //Sender
//			email.setSubject("Test Report");
//			email.setMsg("Please find Attached Report...!!!");
//			email.addTo("omishah1501@gmail.com"); //Receiver, to send multiple ppl we can send via DL
//			email.attach(url, "Extent Report", "Please Check Report...");
//			email.send(); //Send the email
//		
//		} 
//		catch (Exception e) {
//			
//			e.printStackTrace();
//		}
		
		
	  }
	  
	  
	
	
	
	
	
	
	
	
	

}
