package e2e.AppiumFramework;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import pageObjects.HomePage;
import pageObjects.Preferences;

public class ApiDemoTest extends Base {

	@Test
	public void apiDemoTest() throws IOException, InterruptedException {

		service = startServer();

		AndroidDriver<AndroidElement> dr = Capabilities("apiDemo");
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		HomePage h = new HomePage(dr);
		h.getPreferences().click();

		Preferences p = new Preferences(dr);
		p.getDependencies().click();

		p.getCheckBox().click();
		Thread.sleep(3000);
		p.getWifiSetting().click();

		p.getEditText().sendKeys("input");

		p.getButtons().get(1).click();
		service.stop();

	}

}
