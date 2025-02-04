package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;  //Importing AccountRegistrationPage as its present in different package
import pageObjects.HomePage;   //Importing Home Page
import testBase.BaseClass;  //Importing Home Page by extending

public class TC001_AccountRegistrationTest extends BaseClass{  //Extending Baseclass to access Setup, tear down, randomData method rtc...
	
	 
	@Test(groups = {"Regression", "Master"}) //Adding grouping - Master will execute all TCs, If Single group no need to put in curly braces
	 public void verify_Account_Registration() throws InterruptedException
	 {
		logger.info("*******Starting TC001_AccountRegistrationTest*******"); 
		
		try {  //Putting whole thing in try and catch block to handle any exception in future
		
		HomePage hp = new HomePage(driver);  //Creating Object for HomePage Class to Access and passing driver to invoke Base page Constructor
		
		hp.clickMyAccount();
		logger.info("<---------Clicked on MyAccount link---------->");
		hp.clickRegister();
		logger.info("<---------Clicked on Regiter link---------->");
		 
		 
		 AccountRegistrationPage regPage = new AccountRegistrationPage(driver); //Creating Object for AccountRegistrationPage Class to Access and passing driver to invoke Base page Constructor
		 
		 logger.info("<---------Providing Customer details---------->");
		 //regPage.setFirstName("Omprakash");  //Hard coded
		 regPage.setFirstName(randomeString().toUpperCase()); //generate randome string and convert into uppercase
		 
		 //regPage.setLastName("Sah"); //Hard coded
		 regPage.setLastName(randomeString().toUpperCase()); //generate randome string and convert into uppercase
		
		 //regPage.setEmail("ays876@gmail.com"); //Hard coded
		 regPage.setEmail(randomeString()+"@gmail.com");  //Random generated email
		 
		 //regPage.setTelephone("8658765432");  //Hard Coded
		 regPage.setTelephone(randomeNumber());  //Generate 10 digit number
		
		 //regPage.setPassword("Graph@12345"); //Hard Coded
		 //regPage.setConfirmPassword("Graph@12345"); //Hard Coded
		 String password = randomeAlphaNumber();  //Storing in one variable as confirm pwd should be same
		 regPage.setPassword(password); //Random Generated pwd
		 regPage.setConfirmPassword(password); //Random Generated pwd
		 
		 regPage.clickNewsLetter();
		 regPage.checkpolicy();
		 regPage.clickContinue();
		 
		 logger.info("<---------Validating expected message---------->");
		 String confmsg = regPage.getConfirmationMsg();
		 
		 if(confmsg.equals("Your Account Has Been Created!"))
		 {
			Assert.assertTrue(true); 
		 }
		 else
		 {
			 logger.error("Test faild");
			 logger.debug("Debug Logs");
			 Assert.assertTrue(false);
		 }
		 
		// Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		 
		}//Try End
		catch (Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("*******Finished TC001_AccountRegistrationTest******* ");
		 
		 
	 }
	
	

}
