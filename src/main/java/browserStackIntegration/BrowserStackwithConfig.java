package browserStackIntegration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class BrowserStackwithConfig {

//  public static final String USERNAME = "jigarsoni1";
//  public static final String AUTOMATE_KEY = "MjZXMLuzXiRwW7wMXgm3";
//  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static Properties prop;
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		readprop();
		String browserName = prop.getProperty("browser");
		if (browserName.equals("browserstack")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "74.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("resolution", "1024x768");
			//readprop();
			String URL = "https://" + prop.getProperty("username") + ":" + prop.getProperty("aceesskey")
					+ "@hub-cloud.browserstack.com/wd/hub";
			driver = new RemoteWebDriver(new URL(URL), caps);
		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(prop.getProperty("baseurl"));
		WebElement element = driver.findElement(By.name("q"));

		element.sendKeys("BrowserStack");
		element.submit();

		System.out.println(driver.getTitle());
		System.out.println("Done");
		driver.quit();

	}

	public static void readprop() {
		try {
			prop = new Properties();
			// String projectpath = System.getProperty("user.dir");
			FileInputStream ip = new FileInputStream(
					"/Users/jigarsony/Automation/WorkSpace/SeleniumDemoIntegration/src/main/java/config/config.properties");
			// F:\Automation\Selenium\newtourDemo\src\main\java\config\config.properties
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}