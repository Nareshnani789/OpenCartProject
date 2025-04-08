package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {

	public AccountRegisterPage(WebDriver driver) 
	{
		super(driver);
	}
	
	
@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstName;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastName;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txttelephone;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtpassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtconfirmPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement chkdPolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmtion;

public void setFirstName(String fname)
{
	txtFirstName.sendKeys(fname);
	
}
public void setLastName(String lname)
{
	txtLastName.sendKeys(lname);
	
}
public void setEmail(String email)
{
	txtEmail.sendKeys(email);
}

public void setTelephone(String tel)
{
	txttelephone.sendKeys(tel);
}
public void setPassword(String pwd) 
{
	txtpassword.sendKeys(pwd);
}
public void setConfirmationPassword(String pwd)
{
	txtconfirmPassword.sendKeys(pwd);
}
public void setPrivatePolicy()
{
	chkdPolicy.click();
}
public void clickContinue()
{   //sol1
	btnContinue.click();
	
	//sol2
	//Actions act=new Actions(driver);
	//act.moveToElement(btnContinue).click().perform();
	
	//sol3
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();", btnContinue);
	
	//sol4
	//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();;

}

public String getConfirmationMsg()
{
	try {
		return(msgConfirmtion.getText());
	}catch(Exception e) {
		return(e.getMessage());
	}
}



}
