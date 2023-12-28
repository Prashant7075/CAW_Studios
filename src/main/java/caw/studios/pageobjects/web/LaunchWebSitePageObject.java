package caw.studios.pageobjects.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import caw.studios.pages.web.LaunchWebSitePage;

public class LaunchWebSitePageObject {
	private LaunchWebSitePage launchWebSitePage = null;

	public LaunchWebSitePageObject(WebDriver driver) {
		launchWebSitePage = PageFactory.initElements(driver, LaunchWebSitePage.class);
	}

	public LaunchWebSitePage getLaunchWebSitePage() {
		return this.launchWebSitePage;
	}
}
