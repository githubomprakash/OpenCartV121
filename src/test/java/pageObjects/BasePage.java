package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//In Base Class creating Only Constructor for setUP & that can be extedned in every pageObject Class, 
//this is parent of all pageObject class


public class BasePage {
	
	WebDriver driver;  //Web Driver Variable
	
	public BasePage(WebDriver driver)  //Constructor
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  //Creating PageFactory to find locators in POM design
	}

}
