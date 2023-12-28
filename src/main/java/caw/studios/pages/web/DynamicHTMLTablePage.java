package caw.studios.pages.web;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import caw.studios.resources.CommonUtils;

public class DynamicHTMLTablePage extends CommonUtils {
	WebDriver driver;

	public DynamicHTMLTablePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(css = ".centered details summary")
	WebElement tableDataDetailsArrowButton;

	@FindBy(id = "jsondata")
	WebElement jsonDataTextArea;

	@FindBy(id = "refreshtable")
	WebElement refreshButton;

	@FindBy(xpath = "(//table[@id='dynamictable']/tr)")
	List<WebElement> tableDataRows;

	/**
	 * A METHOD TO INSERT DATA
	 * 
	 * @param data
	 * @throws InterruptedException
	 */
	public void insertData(Object data) throws InterruptedException {
		String retrievedData = data.toString();
		waitUntilElementToBeClickable(driver, tableDataDetailsArrowButton, 5);
		clickElementUsingJS(driver, tableDataDetailsArrowButton);
		clickElementUsingJS(driver, jsonDataTextArea);
		jsonDataTextArea.clear();
		jsonDataTextArea.sendKeys(retrievedData);
		waitUntilElementToBeClickable(driver, refreshButton, 5);
		clickElementUsingJS(driver, refreshButton);
	}

	/**
	 * A METHOD TO EXTRACT THE DATA FROM THE TABLE FOR ASSERTIONS
	 * 
	 * @return tableData
	 */
	public Object[][] getTableDataFromApplication() {
		List<Object[]> tableData = new ArrayList<>();
		for (int i = 1; i < tableDataRows.size(); i++) {
			WebElement row = tableDataRows.get(i);
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String name = cells.get(1).getText();
			int age = Integer.parseInt(cells.get(2).getText());
			String gender = cells.get(0).getText();
			tableData.add(new Object[] { name, age, gender });
		}
		return tableData.toArray(new Object[0][]);
	}
}
