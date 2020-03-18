package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Preferences {
	// THIS ONE IS FOR API DEMO TEST
	public Preferences(AppiumDriver dr) {
		PageFactory.initElements(new AppiumFieldDecorator(dr), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
	private WebElement dependencies;

	@AndroidFindBy(id = "android:id/checkbox")
	private WebElement checkBox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='WiFi settings']")
	private WebElement wifiSetting;

	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement editText;

	@AndroidFindBy(className = "android.widget.Button")
	private List<WebElement> buttons;

	public WebElement getDependencies() {
		return dependencies;
	}

	public WebElement getCheckBox() {
		return checkBox;
	}

	public WebElement getWifiSetting() {
		return wifiSetting;

	}

	public WebElement getEditText() {
		return editText;
	}

	public List<WebElement> getButtons() {
		return buttons;
	}

}
