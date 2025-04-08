package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups= {"Regression","Master"})
	public void verify_Account_Registration()
	{
		logger.info("*******	Starting TC001_AccountRegistrationTest  *******");
		logger.debug("This isa debug log message");
		try 
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked on MyAccount Link...");
		hp.clickRegister();
		logger.info("Clicked on MY RegisterAccount...");
		
		AccountRegisterPage regpage=new AccountRegisterPage(driver);
		
		logger.info("Providing Customer Details...");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumberic();
		
		regpage.setPassword(password);
		regpage.setConfirmationPassword(password);
		
		regpage.setPrivatePolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected Message....");
		
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg,"Your Account Has Been Created!","Confirmation message mismatch");
		
		logger.info("Test Passed");
	    }
	    catch(Exception e)
		{
	    	logger.error("Test failed: "+e.getMessage());
	    	Assert.fail("Test failed: "+e.getMessage());
		}
		finally
		{
		
		logger.info("*********	Finished TC001_AccountRegistrationTest  ***********");
		}
	}
	
	
}
