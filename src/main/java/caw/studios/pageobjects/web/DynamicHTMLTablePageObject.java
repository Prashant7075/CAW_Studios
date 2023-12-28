package caw.studios.pageobjects.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import caw.studios.pages.web.DynamicHTMLTablePage;

public class DynamicHTMLTablePageObject {
	private DynamicHTMLTablePage dynamicHTMLTablePage = null;

	/**
	 * DECORATION OF DYNAMICHTMLTABLEPAGE WITH DRIVER USING PAGEFACTORY
	 *
	 * @param driver
	 */
	public DynamicHTMLTablePageObject(WebDriver driver) {
		dynamicHTMLTablePage = PageFactory.initElements(driver, DynamicHTMLTablePage.class);
	}

	/**
	 * RETURNS THE DECORATED DYNAMICHTMLTABLEPAGE
	 *
	 * @return dynamicHTMLTablePage
	 */
	public DynamicHTMLTablePage getDynamicHTMLTablePage() {
		return this.dynamicHTMLTablePage;
	}
}
