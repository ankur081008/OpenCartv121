package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement  txtfirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement  txtlastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement  txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement  txtTelephone;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement  txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPasword ;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement BtnContinue ;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkBoxPolicy ;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation ;

	public void setFirstName(String firstName) {
		txtfirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		txtlastName.sendKeys(lastName);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void setConfirmPasword(String confirmPasword) {
		txtConfirmPasword.sendKeys(confirmPasword);
	}

	public void clickBtnContinue() {
		BtnContinue.click();
	}

	public void setPrivacyPolicy() {
		chkBoxPolicy.click();
	}
	
	public String getConfirmationMsg() {
		return (msgConfirmation.getText());
	}

	
}
