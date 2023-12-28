package caw.studios.resources;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {

	/**
	 * IMLICIT WAIT
	 *
	 * @param driver
	 * @param timeInSeconds
	 */
	public static void implicitWait(WebDriver driver, int timeInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
	}

	/**
	 * IMLICIT WAIT THREADLOCAL
	 *
	 * @param driver
	 * @param timeInSeconds
	 */
	public static void implicitWaitThreadLocal(ThreadLocal<WebDriver> driver, int timeInSeconds) {
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
	}

	/**
	 * WAIT UNTIL THE ELEMENT GETS CLICKABLE
	 *
	 * @param driver
	 * @param element
	 * @param timeInSeconds
	 */
	public static void waitUntilElementToBeClickable(WebDriver driver, WebElement element, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * WAIT UNTIL ELEMENT GETS VISIBLE
	 *
	 * @param driver
	 * @param element
	 * @param timeInSeconds
	 */
	public static void waitUntilVisibilityOfElement(WebDriver driver, WebElement element, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * CLICK A WEB ELEMENT USING JAVASCRIPTEXECUTOR
	 *
	 * @param driver
	 * @param element
	 */
	public static void clickElementUsingJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
}
