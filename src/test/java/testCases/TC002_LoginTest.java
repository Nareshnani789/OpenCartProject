package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups= {"sanity","Master"})
	public void verfy_Login()
	{
     	logger.info("******************  Started TC002_LoginTest  ***********");
     	
     	try
     	{
     	//HomePage
     	HomePage hp=new HomePage(driver);
     	hp.clickMyaccount();
     	hp.clickLogin();
     	
     	//Login
     	LoginPage lp=new LoginPage(driver);
     	lp.setEmail(p.getProperty("email"));
     	lp.setPassword(p.getProperty("password"));
     	lp.clickLogin();
     	
     	//MyAccountPage
     	
     	MyAccountPage macc=new MyAccountPage(driver);
     	boolean TargetPage=macc.isMyAccountPageExists();
     	
     	Assert.assertTrue(TargetPage);// Assert.assertEquals(TargetPage, true,"LoginFailed");
     	macc.ClickLogout();
     	}
     	catch(Exception e)
     	{
     		Assert.fail();
     	}
     	
     	logger.info("****************** Finished TC002_LoginTest  ***********");
		
		
		
		
		
		
		
		
		
	
		
		
	}

}
