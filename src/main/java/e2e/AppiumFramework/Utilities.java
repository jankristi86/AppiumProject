package e2e.AppiumFramework;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	AndroidDriver<AndroidElement> dr;

	public Utilities(AndroidDriver<AndroidElement> dr) {
		this.dr = dr;
	}

	public WebElement scrollToText(String text) {
		return dr.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
	}
}
