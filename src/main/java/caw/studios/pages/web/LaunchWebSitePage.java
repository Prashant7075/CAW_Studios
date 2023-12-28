package caw.studios.pages.web;

import org.openqa.selenium.WebDriver;

public class LaunchWebSitePage {
	WebDriver driver;

	public LaunchWebSitePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * A METHOD TO LAUNCH WEBSITE
	 * 
	 * @param url
	 */
	public void launchWebSite(String url) {
		driver.get(url);
		String title = driver.getTitle();
		System.out.println("The title of the application is : " + title);
	}
}
