package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	// THIS ONE IS FOR API DEMO TEST

// 1. Is to call the driver object from testcase to Pageobject file

	// Concatenate driver
	public HomePage(AndroidDriver<AndroidElement> dr) {
		PageFactory.initElements(new AppiumFieldDecorator(dr), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
	private WebElement Preferences;

	public WebElement getPreferences() {
		return Preferences;
	}

}
