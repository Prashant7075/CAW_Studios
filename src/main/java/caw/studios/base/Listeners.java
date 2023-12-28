package caw.studios.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import caw.studios.reports.ExtentReporterNG;

public class Listeners implements ITestListener {
	ExtentTest test;
	WebDriver driver;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String filePath = null;
		extentTest.get().pass(result.getThrowable());
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), BaseTests.driver.get());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		extentTest.get().log(Status.PASS, "TEST PASSED");
		extentTest.get().pass(filePath);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String filePath = null;
		extentTest.get().fail(result.getThrowable());
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), BaseTests.driver.get());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		extentTest.get().log(Status.FAIL, "TEST FAILED");
		extentTest.get().fail(filePath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();

	}

	/**
	 * CAPTURES THE SCREENSHOT AND RETURNS IT AS AN FILE
	 *
	 * @param testCaseName
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	private String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

}
