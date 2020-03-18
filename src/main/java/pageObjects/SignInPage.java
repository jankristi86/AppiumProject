package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInPage {

	public SignInPage(AndroidDriver<AndroidElement> dr) {
		PageFactory.initElements(new AppiumFieldDecorator(dr), this);
	}

	@AndroidFindBy(id = "android:id/text1")
	private WebElement countrySelection;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(xpath = "//*[@text='Female']")
	private WebElement femaleOption;

	@AndroidFindBy(uiAutomator = "text(\"Let's  Shop\")")
	private WebElement letsShopBtn;

	public WebElement getCountrySelection() {
		System.out.println("Selecting the option from dropdown");
		return countrySelection;
	}

	public WebElement getNameField() {
		System.out.println("trying to find the Name field");
		return nameField;
	}

	public WebElement getFemaleOption() {
		return femaleOption;
	}

	public WebElement getLetsShopBtn() {
		return letsShopBtn;

	}

}
