package caw.studios.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import caw.studios.resources.CommonUtils;
import caw.studios.resources.LaunchWebsitePageTestResources;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests extends CommonUtils {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static AppiumDriverLocalService service;
	public static UiAutomator2Options options;
	public static XCUITestOptions safari;
	public String platform;
	public URL gridUrl;
	public static String setDownloadsPath;

	@BeforeTest(alwaysRun = true)
	@Parameters({ "browserName", "platform" })
	public void initializeDriver(String browserName, String platform) throws Exception {
		this.platform = platform;
		this.gridUrl = new URL(LaunchWebsitePageTestResources.getGridUrl());
		if (platform != null) {
			if (platform.equalsIgnoreCase("mobile")) {
				if (browserName.equalsIgnoreCase("chromeMobile")) {
					options = new UiAutomator2Options().setBuildToolsVersion("28.0.3").setDeviceName("MOTOX4")
							.setUdid("192.168.0.126:5555").setPlatformName("Android").eventTimings();
					options.setPlatformVersion("9");
					options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
					options.setUnlockType("pin").setUnlockKey("725267852");
					options.setCapability("appium:automationName", "UiAutomator2");
					options.setCapability("appium:chromedriverExecutable",
							"C:\\Users\\PrashantK\\OneDrive\\Desktop\\GRID\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
					options.setCapability("appium:newCommandTimeout", 20);
					setDownloadsPath = (System.getProperty("user.dir") + "\\certificates&invoices");
					System.setProperty("webdriver.chrome.download.default_directory", setDownloadsPath);
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.setPlatformName("Android");
					chromeOptions.addArguments("--remote-allow-origins=*");
					chromeOptions.addArguments("--incognito");
					chromeOptions.addArguments("--headless=new");
					chromeOptions.addArguments("--disable-notifications");
					chromeOptions.addArguments("--disable-popup-blocking");

					service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withAppiumJS(new File(
							"C:\\Program Files\\Appium Server GUI\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
							.withIPAddress("127.0.0.1").withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
							.usingPort(4723).withArgument(() -> "--allow-insecure", "chromedriver_autodownload")
							.withArgument(() -> "--allow-insecure", "true").withArgument(() -> "--allow-cors")
							.withArgument(GeneralServerFlag.RELAXED_SECURITY).withArgument(() -> "--no-perms-check")
							.withArgument(() -> "--session-override"));
					service.start();

					driver.set(new AndroidDriver(gridUrl, options.merge(chromeOptions)));
				} else if (browserName.equalsIgnoreCase("safariMobile")) {
					safari = new XCUITestOptions().setUdid("F36232D9-8CE1-4C34-8B8F-B49BD31F361F")
							.setDeviceName("iPhone 8").setPlatformName("iOS").setPlatformVersion("11.4")
							.withBrowserName("safari").setAutomationName("XCUITest")
							.setWdaLaunchTimeout(Duration.ofSeconds(20)).setNewCommandTimeout(Duration.ofSeconds(10));
					safari.getUpdatedWdaBundleId();
					safari.setIncludeSafariInWebviews(true);
					safari.setWebviewConnectTimeout(Duration.ofSeconds(10));
					SafariOptions safariOptions = new SafariOptions();
					safariOptions.setAcceptInsecureCerts(true);
					safariOptions.setAutomaticProfiling(true);
					safariOptions.setCapability("se:bidiEnabled", true);
					safariOptions.setCapability("se:cdpEnabled", true);

					service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
							.withIPAddress("192.168.0.100").withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
							.usingPort(4725).withArgument(() -> "--relaxed-security").withArgument(() -> "--allow-cors")
							.withArgument(() -> "--no-perms-check").withArgument(() -> "--allow-insecure", "true")
							.withArgument(() -> "--session-override").withArgument(() -> "--default-device"));
					service.start();

					driver.set(new IOSDriver(gridUrl, safari.merge(safariOptions)));
				}
			} else if (platform.equalsIgnoreCase("web")) {
				if (browserName.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					setDownloadsPath = (System.getProperty("user.dir") + "\\downloads");
					System.setProperty("webdriver.chrome.download.default_directory", setDownloadsPath);
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content.settings.popups", 0);
					chromePrefs.put("download.default_directory", setDownloadsPath);
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", chromePrefs);
					options.addArguments("--headless");
					options.setPlatformName(System.getProperty("os.name"));
					options.addArguments("--remote-allow-origins=*");
					options.addArguments("--disable-setuid-sandbox");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-gpu");
					options.addArguments("--window-size=1920,1080");
					options.addArguments("--start-maximized");
					options.addArguments("--disable-infobars");
					options.addArguments("--disable-extensions");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--disable-notifications");
					options.addArguments("--disable-popup-blocking");
					driver.set(new RemoteWebDriver(
							new URL(LaunchWebsitePageTestResources.getRemoteWebDriverUrlChrome()), options));
				} else if (browserName.equalsIgnoreCase("edge")) {
					WebDriverManager.edgedriver().setup();
					setDownloadsPath = (System.getProperty("user.dir") + "\\downloads");
					System.setProperty("webdriver.edge.download.default_directory", setDownloadsPath);
					HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
					edgePrefs.put("profile.default_content.settings.popups", 0);
					edgePrefs.put("download.default_directory", setDownloadsPath);
					EdgeOptions options = new EdgeOptions();
					options.setExperimentalOption("prefs", edgePrefs);
					options.addArguments("--headless");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-gpu");
					options.addArguments("--window-size=1920,1080");
					options.addArguments("--start-maximized");
					options.addArguments("--disable-infobars");
					options.addArguments("--disable-extensions");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--disable-popup-blocking");
					driver.set(new RemoteWebDriver(new URL(LaunchWebsitePageTestResources.getRemoteWebDriverUrlEdge()),
							options));
				} else if (browserName.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("--headless");
					options.setPageLoadStrategy(PageLoadStrategy.EAGER);
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-gpu");
					driver.set(new RemoteWebDriver(
							new URL(LaunchWebsitePageTestResources.getRemoteWebDriverUrlFirefox()), options));
				} else if (browserName.equalsIgnoreCase("safari")) {
					WebDriverManager.safaridriver().setup();
					SafariOptions options = new SafariOptions();
					options.setCapability(CapabilityType.BROWSER_NAME, "safari");
					options.getBrowserVersion();
					options.setPageLoadTimeout(Duration.ofSeconds(20));
					options.setAcceptInsecureCerts(true);
					options.getPlatformName();
					options.setAutomaticProfiling(true);
					options.getCapabilityNames();
					driver.set(new RemoteWebDriver(gridUrl, options));
				}
				driver.get().manage().window().maximize();
			}
			driver.get().manage().deleteAllCookies();
			implicitWaitThreadLocal(driver, 10);
		}
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws MalformedURLException {
		driver.get().manage().deleteAllCookies();
		if (platform.equalsIgnoreCase("mobile")) {
			if (driver.get() instanceof AndroidDriver) {
				((AndroidDriver) driver.get()).quit();
			} else if (driver.get() instanceof IOSDriver) {
				((IOSDriver) driver.get()).terminateApp("com.apple.mobilesafari");
				((IOSDriver) driver.get()).quit();
			}
			if (service.isRunning()) {
				service.stop();
			}
		} else {
			driver.get().quit();
		}
	}
}
