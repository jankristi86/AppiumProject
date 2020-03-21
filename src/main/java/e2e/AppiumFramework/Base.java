package e2e.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> dr;

	public AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;
	}

	public static AndroidDriver<AndroidElement> Capabilities(String apk) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties");

		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(apk));
		DesiredCapabilities cap = new DesiredCapabilities();
		String device = (String) prop.get("device");
		// String device = System.getProperty("deviceName");
		if (device.contains("Emulator")) {
			startEmulator();
		}
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		dr = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return dr;
	}

	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(4000);
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void getScreenshot(String s) throws IOException {
		File srcFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,
				new File(System.getProperty("user.dir") + "\\src\\main\\java\\e2e\\AppiumFramework\\" + s + ".png)"));
	}

}
