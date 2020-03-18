package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage {

	public CheckoutPage(AndroidDriver<AndroidElement> dr) {
		PageFactory.initElements(new AppiumFieldDecorator(dr), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;

	public List<WebElement> getProductList() {
		return productList;
	}

	public WebElement getTotalAmount() {
		return totalAmount;
	}

}
