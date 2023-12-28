package caw.studios.testcases.web;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import caw.studios.base.BaseTests;
import caw.studios.pageobjects.web.DynamicHTMLTablePageObject;
import caw.studios.pageobjects.web.LaunchWebSitePageObject;
import caw.studios.pages.web.DynamicHTMLTablePage;
import caw.studios.pages.web.LaunchWebSitePage;
import caw.studios.resources.InputDataResources;

public class DynamicHTMLTablePageTest extends BaseTests {
	LaunchWebSitePage launchWebSitePage;
	DynamicHTMLTablePage dynamicHTMLTablePage;
	SoftAssert softAssert;

	@BeforeClass
	public void launchDynamicHTMLTablePage() {
		launchWebSitePage = new LaunchWebSitePageObject(driver.get()).getLaunchWebSitePage();
		dynamicHTMLTablePage = new DynamicHTMLTablePageObject(driver.get()).getDynamicHTMLTablePage();
	}

	@Test(dataProvider = "inputData", dataProviderClass = InputDataResources.class)
	public void inputDataFunctionalityCheck(Object data) throws InterruptedException, IOException, ParseException {
		softAssert = new SoftAssert();
		System.out.println("The set of data to be entered is : " + data.toString());
		dynamicHTMLTablePage.insertData(data);
		Object[][] tableData = dynamicHTMLTablePage.getTableDataFromApplication();
		JSONArray jsonInput = (JSONArray) InputDataResources.readInputJSONData()[0];
		softAssert.assertEquals(tableData.length, jsonInput.size(),
				"Number of rows in the table data and JSON should match");
		for (int i = 0; i < tableData.length; i++) {
			JSONObject expectedRow = (JSONObject) jsonInput.get(i);
			Object[] actualRow = tableData[i];
			int expectedAge = ((Long) expectedRow.get("age")).intValue();
			softAssert.assertEquals(actualRow[0], expectedRow.get("name"), "Name mismatch in row " + i);
			softAssert.assertEquals(actualRow[1], expectedAge, "Age mismatch in row " + i);
			softAssert.assertEquals(actualRow[2], expectedRow.get("gender"), "Gender mismatch in row " + i);
		}
		softAssert.assertAll();
	}
}
