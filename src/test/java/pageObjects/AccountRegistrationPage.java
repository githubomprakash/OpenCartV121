package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {  //Extending base page to access Created Driver constructor
	
	public AccountRegistrationPage(WebDriver driver)  //Creating Constructor to invike BasePage parant class constructor
	{
		super(driver);   //invoking Constructor of BasePage by Super keyword
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	WebElement newsLetterYes;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fName)  //Gettig this parameter from testcase
	{
		txtFirstName.sendKeys(fName);  //Getting field and passing value
	}
	
	public void setLastName(String lName)
	{
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String cnfrmPassword)
	{
		txtConfirmPassword.sendKeys(cnfrmPassword);
	}
	
	public void clickNewsLetter()
	{
		newsLetterYes.click();
	}
	
	public void checkpolicy()
	{
		chkPolicy.click();
	}
	public void clickContinue()
	{
		//Sol1
		btnContinue.click();
		
		//Sol2
		//btnContinue.submit();
		
		//Sol3 by Actions Classs
		//Actions act = new Actions(driver);
		//act.moveToElement(btnContinue).click().build().perform();
		
		//Sol4 by JavascriptExecutor method
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click()", btnContinue);
		
		//Sol5 by Send Key
		//btnContinue.sendKeys(Keys.RETURN);
		
		//Sol6 by Wait Method
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMsg()
	{
	try
	{
		return (msgConfirmation.getText());  //it will capture txt msg and it will retrn in string, Validation will do in testcase
	}
	catch(Exception e)
	{
		return (e.getMessage());	
	}
	
	}
	

}
