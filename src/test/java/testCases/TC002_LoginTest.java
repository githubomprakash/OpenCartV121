package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;   //Importing BaseClass from testBase

public class TC002_LoginTest extends BaseClass { //Extending Baseclass to access ReUsable method Setup, tear down, randomData method rtc...
	
	
	//Creating Test Method
	@Test(groups = {"Sanity", "Master"}) //Adding grouping - Master will execute all TCs, If Single group no need to put in curly braces
	public void verify_login()
	{
		logger.info("*********Starting TC002_LoginTest**********");
		
		try { //Putting everyting in try/catch block if any exception come to handle
		
		//Creating Object to acces HomePage element and passing driver instance
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Creating Object to acces LoginPage element and passing driver instance
		LoginPage lp = new LoginPage(driver);
		//lp.setUser("omprakashsah36@gmail.com");  //Hard Coded
		lp.setUser(p.getProperty("email"));   //Taking Testdata from Properties file
		lp.setPassword(p.getProperty("password")); //Taking Testdata from Properties file
		lp.ClickLogin();
		
		Thread.sleep(5000);
		
		//Creating Object to acces MyAccountPage element and passing driver instance
		MyAccountPage macc =new MyAccountPage(driver);
		boolean targetPage = macc.isMYAccountPageExists();
		 
		 Assert.assertEquals(targetPage, true, "Login Failed");  //Passing 3 parameter, if failed Login Failed print on console
		//Assert.assertTrue(targetPage);  //If both are equal return true
		 
		 
		}
		catch (Exception e)
		{
		 Assert.fail();
		}
		 logger.info("*********Ending TC002_LoginTest**********");
		
	
	}
	

}
