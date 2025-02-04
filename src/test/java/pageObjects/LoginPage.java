package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage{   //Extending Base Class
	
	public LoginPage( WebDriver driver)  //Creating Constructor same name as class name to invoke parant BaseClass 
	{
		super(driver);  //Accessing immediate constructor from Base Page by super 
	}
	
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	public void setUser(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void ClickLogin()
	{
		btnLogin.click();
	}

}
