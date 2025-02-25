package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//Creating Base classes to Re-Usable method in every testcases  to avoid duplicate, Basically its parant class of all Testcase class

public class BaseClassWithoutGridConcept {
	
	     //Creating Variable/Method as public to acess throughout the page
		 public static WebDriver driver;   //Creating Variable for driver, making this driver as Static so same driver will be refered to Object also eg- BaseClass Object created in ExtendtReportManager class to take the Screenshot from Base Class
		 public Logger logger;      //Creating Variable for Logger Class log4j
		 public Properties p;   //Creating Variable for Properties file
		 
		 @BeforeClass(groups = {"Sanity", "Regression", "Master", "DataDriven"})   //It will execute only once, in Group on every setup method
		@Parameters({"os", "browser"})   //Passing this {"os", "browser"} parameter to read value from testNG xml file broswer name = chrome or edge rtc
		public void setUp(String os, String br) throws IOException  //Creating variable for OS and browser br to Passing this {"os", "browser"} parameter from xml file
		{
			
			//Loading config.properties file
			 FileReader file = new FileReader(".//src//test//resources//config.properties");  //Reading value from File Location
			 p = new Properties();
			 p.load(file);
			 
			 
			 //Loading Logger to print log and reading configuration from log4j.xml file
			 logger= (Logger) LogManager.getLogger(this.getClass());  
			
			
			//driver = new ChromeDriver(); 
			//Launching browser based on input provided in TestNG.xml file for cross browser tesing 
			switch(br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid Browser Name..."); return;
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			//driver.get("https://tutorialsninja.com/demo/"); //Hard Coded Url
			driver.get(p.getProperty("appurl"));   //Reading appurl from properties file to avoid hard coded
			driver.manage().window().maximize();
		}
		
		@AfterClass(groups = {"Sanity", "Regression", "Master", "DataDriven"})   //It will execute only once, in Group on every setup method
		 public void tearDown()
		{
			driver.close();
		}
		
		
		//Creating User define method to generate Random String alphabate
		public String randomeString()
		{
		  String generatedString = RandomStringUtils.randomAlphabetic(5);  //5 randome String will generate
		  return generatedString;
		}
		//Creating User define method to generate Random Number digit
		public String randomeNumber()
		{
		  String generateNumber = RandomStringUtils.randomNumeric(10);  //10 randome number will generate
		  return generateNumber;
		}
		
		//Creating User define method to generate Random Alpha Number digit
		public String randomeAlphaNumber()
		{
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			String generateNumber = RandomStringUtils.randomNumeric(3);  //10 randome number will generate
			//return(generatedString+generateNumber); //Alpha and number
			return(generatedString+"@"+generateNumber);  //Adding special char
			//return(generatedString+generateNumber+"@gmail.com");  //in form of email id
		}
		
		//Creating Common method for Capturing Screenshot while any of the TCs got Failed
		public String captureScreen(String tname) throws IOException  //captureScreen method will take name as parameter to name the screenshot
		{
			String timestamp = new SimpleDateFormat("�yyyMMddhhmmss").format(new Date()); //will take screenshot name in timestamp
			
			TakesScreenshot takeScreenShot = (TakesScreenshot)driver; //TakesScreenshot is interface to take TakesScreenshot
			File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + tname + "_" + timestamp + ".png";
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);  //Coping sourcefile into Target file
			return targetFilePath;  //Returning path where Screenshot located to attached in report, if will not return it will be in folder only
		}

}
