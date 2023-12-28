package caw.studios.testcases.web;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import caw.studios.base.BaseTests;
import caw.studios.pageobjects.web.LaunchWebSitePageObject;
import caw.studios.pages.web.LaunchWebSitePage;
import caw.studios.resources.LaunchWebsitePageTestResources;

public class LaunchWebSitePageTest extends BaseTests {
	LaunchWebSitePage launchWebSitePage;

	@BeforeClass
	public void launchWebsite() {
		launchWebSitePage = new LaunchWebSitePageObject(driver.get()).getLaunchWebSitePage();
	}

	@Test(dataProvider = "URL", dataProviderClass = LaunchWebsitePageTestResources.class)
	public void launchWebSiteFunctionalityCheck(String url) {
		launchWebSitePage.launchWebSite(url);
	}
}
