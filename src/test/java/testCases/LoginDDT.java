package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class LoginDDT extends BaseTest {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void testLogin(String email,String password) {
        logger.info("****** Starting verifyAccountRegistrationTest  *******");

		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		homePage.clickLogin();
		logger.info("Clicked on Login Link");
		// Login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(email);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		// My Account Page
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean targetPage =myAccountPage.isMyAccountPageExists();
		
			
	} 

}
