package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTest;

public class AccountRegistrationTest extends BaseTest {
	
	
	@Test(groups= {"Regression"})
	public void verifyAccountRegistration() {
		logger.info("****** Starting verifyAccountRegistrationTest  *******");
		
		try {
			
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		homePage.clickRegister();
		logger.info("Clicked on Register Link");
		AccountRegistrationPage accntRegPage = new AccountRegistrationPage(driver);
		logger.info("providing customer details");
		accntRegPage.setFirstName(randomString());
		accntRegPage.setLastName(randomString());
		accntRegPage.setEmail(randomAlphaNumeric()+"@gmail.com");
		accntRegPage.setTelephone(randomNumber());
		String password = randomAlphaNumeric();
		accntRegPage.setPassword(password);
		accntRegPage.setConfirmPasword(password);
		accntRegPage.setPrivacyPolicy();
		accntRegPage.clickBtnContinue();
		logger.info("Validating expected Message");
		String confmsg = accntRegPage.getConfirmationMsg();
		if(confmsg.equalsIgnoreCase("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		} else {
			logger.error("Test Failed");
			logger.debug("Debug Logs");	
			Assert.assertTrue(false);
		}
	//	Assert.assertEquals(accntRegPage.getConfirmationMsg(), "Your Account Has Been Created!");
		} catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("****** Finished verifyAccountRegistrationTest  *******");
	}
	
	
	
	
	
	

}
