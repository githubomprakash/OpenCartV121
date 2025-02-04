package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


//Flow of Working - TCs taking data from DataProviders Class and DataProviders stored data from ExcelUtility class(which read from Excel) file in 2 diamentianl Array
//and now dataprovider passing data to testcases at runtime

public class TC003_LoginDDT extends BaseClass{
	
	
	//Specified location from where we are getting Data, If DataProviders created in same HomePage class so no need to mentioned -dataProviderClass = DataProviders.class As we are accessing from the different package
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = {"DataDriven", "Master"})  
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("*********Starting TC003_LoginDDT************");
		
		try
		{
		//Creating Object to acces HomePage element and passing driver instance
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
			
		//Creating Object to acces LoginPage element and passing driver instance
		LoginPage lp = new LoginPage(driver);
		//lp.setUser("omprakashsah36@gmail.com");  //Hard Coded
		//lp.setUser(p.getProperty("email"));   //Taking Testdata from Properties file
		lp.setUser(email);  //Testdata from excel Utility by DataProviders
		lp.setPassword(pwd);
		lp.ClickLogin();
				
				
		//Creating Object to acces MyAccountPage element and passing driver instance
		MyAccountPage macc =new MyAccountPage(driver);
		boolean targetPage = macc.isMYAccountPageExists();
		
		/* Data Valid login Success -Test Pass - Logout
		 * Data Valid login Failed -Test Fail 
		 * 
		 * Data InValid login Success -Test Fail - Logout
		 * Data InValid login Failed -Test Pass - 
		 */
		
		if(exp.equalsIgnoreCase("Valid")) //If data is valid
		{
			if(targetPage==true)  //If MyAccountPage visibble
			{
				macc.clickLogout();    //click on logout
				Assert.assertTrue(true);  //test pass
				
			}
			else
			{
				Assert.assertTrue(false);  //test fail
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))  //If Data is invalid
		{
			if(targetPage==true)   //MyAccount page not visible
			{
				macc.clickLogout();  //clcik on logout if with invalid data test pass
				Assert.assertTrue(false);  //Make Test failed
				
			}
			else 
			{
				Assert.assertTrue(false);  
				
			}
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
				 
		logger.info("*********Ending TC003_LoginDDT************");		
	}
	
	

}
