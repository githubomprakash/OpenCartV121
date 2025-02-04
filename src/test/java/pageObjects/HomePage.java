package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{   //Extending BasePage as parant class for reuasable acheivement
	
	public HomePage(WebDriver driver)  //Creating Constructor same name as class name to invoke parant BaseClass 
	{
		super(driver);   //Accessing immediate constructor from Base Page by super 
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAcount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")  //Login Link, Creating Diff TestCases
	WebElement lnklogin;
	
	public void clickMyAccount()
	{
		lnkMyAcount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnklogin.click();
	}

}
