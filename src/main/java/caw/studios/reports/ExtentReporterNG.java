package caw.studios.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("CAW-STUDIOS-DEMO");
		report.config().setDocumentTitle("CAW-STUDIOS-DEMO TEST RESULTS");
		report.config().setEncoding("utf-8");
		report.config().setTheme(Theme.DARK);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Approved By", "PRASHANT");
		return extent;
	}
}
