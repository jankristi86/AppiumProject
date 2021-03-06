package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage {

	public ProductPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
	private WebElement itemPick;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkbox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsCond;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement closeTerms;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement btnProceed;

	public WebElement getItemPick() {
		return itemPick;
	}

	public WebElement getCartBtn() {
		return cartBtn;
	}

	public List<WebElement> getProductPrice() {
		return productPrice;
	}

	public WebElement getCheckbox() {
		return checkbox;
	}

	public WebElement getTermsCond() {
		return termsCond;
	}

	public WebElement getCloseTerms() {
		return closeTerms;
	}

	public WebElement getBtnProceed() {
		return btnProceed;
	}

}