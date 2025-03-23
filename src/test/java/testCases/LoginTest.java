package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class LoginTest extends BaseTest {
	
	
	@Test(groups="Sanity")
	public void testLogin() {
        logger.info("****** Starting verifyAccountRegistrationTest  *******");

		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		homePage.clickLogin();
		logger.info("Clicked on Login Link");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(prop.getProperty("email"));
		loginPage.setPassword(prop.getProperty("password"));
		loginPage.clickLogin();
		// My Account Page
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean targetPage =myAccountPage.isMyAccountPageExists();
		Assert.assertTrue(targetPage);
			
	} 

}
