package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage  extends BasePage{
	
	public MyAccountPage( WebDriver driver)  //Creating Constructor same name as class name to invoke parant BaseClass 
	{
		super(driver);  //Accessing immediate constructor from Base Page by super 
	}
	
	@FindBy(xpath = "//h2[normalize-space()='My Account']")  //My page account heading
	WebElement msgHeading;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")  //Logout from app, Added in Step 6
	WebElement lnkLogOut;
	
	//Action method for validation MyAccount Heading
	public boolean isMYAccountPageExists()   
	{
		try {  //it will check if page exit will return true if not exit it will retrun false
			
			return (msgHeading.isDisplayed());
		}
		catch (Exception e)
		{
			return false;
		}	
	}
	
	public void clickLogout()
	{
		lnkLogOut.click();
	}

}
