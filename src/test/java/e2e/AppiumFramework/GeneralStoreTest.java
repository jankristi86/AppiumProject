package e2e.AppiumFramework;

import java.io.IOException;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.CheckoutPage;

import pageObjects.ProductPage;
import pageObjects.SignInPage;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;

public class GeneralStoreTest extends Base {

	@Test
	public void totalValidation() throws IOException, InterruptedException {
		service = startServer();

		AndroidDriver<AndroidElement> dr = Capabilities("GeneralStoreApp");

		SignInPage sp = new SignInPage(dr);

		sp.getCountrySelection().click();
		Utilities u = new Utilities(dr);
		u.scrollToText("Belgium").click();

		sp.getNameField().sendKeys("hello");
		dr.hideKeyboard(); // nice :)
		sp.getFemaleOption().click();
		sp.getLetsShopBtn().click();

		ProductPage pp = new ProductPage(dr);
		pp.getItemPick().click();
		pp.getItemPick().click();
		pp.getCartBtn().click();

		Thread.sleep(4000);

		int count = pp.getProductPrice().size();
		double sum = 0;
		CheckoutPage cp = new CheckoutPage(dr);

		for (int i = 0; i < count; i++) {
			String amount = cp.getProductList().get(i).getText();
			double amount1 = getAmount(amount);
			sum += amount1;
		}
		System.out.println("Sum of products is: " + sum);

		String total = cp.getTotalAmount().getText();
		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		System.out.println("total value is: " + totalValue);
		Assert.assertEquals(sum, totalValue);

		TouchAction t = new TouchAction(dr);
		t.tap(tapOptions().withElement(element(pp.getCheckbox()))).perform();
		t.longPress(longPressOptions().withElement(element(pp.getTermsCond())).withDuration(Duration.ofSeconds(5)))
				.perform().release();

		pp.getCloseTerms().click();
		pp.getBtnProceed().click();

		// 19.03 - need to fix nullPointerException - with kill all nodes I guess it's
		// done . need to test it.

		// Verify if user can do operatios on web view and can navigate back to Native
		// app if needed
		Thread.sleep(6000);
		Set<String> contexts = dr.getContextHandles();
		for (String contextName : contexts) {
			System.out.println(contextName);

			dr.context("WEBVIEW_com.androidsample.generalstore");
			dr.findElement(By.name("q")).sendKeys("hello");
			dr.findElement(By.name("q")).sendKeys(Keys.ENTER);
			dr.pressKey(new KeyEvent(AndroidKey.BACK));
			dr.context("NATIVE_APP");

		}

		service.stop();

	}

	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\killAllNodes.bat");
		Thread.sleep(3000);
	}

	public static double getAmount(String value) {
		// removing $ with substring $120.00 and parsing into double

		value = value.substring(1);
		double amount2value = Double.parseDouble(value);
		return amount2value;
	}

}
