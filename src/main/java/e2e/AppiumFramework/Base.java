package e2e.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {

	public static AppiumDriverLocalService service;

	public static AndroidDriver<AndroidElement> Capabilities(String apk) throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\e2e\\AppiumFramework\\global.properties");

		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(apk));
		DesiredCapabilities cap = new DesiredCapabilities();
		String device = (String) prop.get("device");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		AndroidDriver<AndroidElement> dr = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return dr;
	}

	public AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;
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

}
