package caw.studios.resources;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class LaunchWebsitePageTestResources {
	static Scanner scanner;

	public static void main() {

	}

	@DataProvider(name = "URL")
	public static Object[] readURLJSON() throws IOException, ParseException {
		JSONParser json = new JSONParser();
		FileReader reader = new FileReader(
				System.getProperty("user.dir") + "//src//test//java//caw//studios//resources//URL.json");
		Object obj = json.parse(reader);
		JSONObject urlJSONObject = (JSONObject) obj;
		JSONObject url = (JSONObject) urlJSONObject.get("URL");

		if (System.getProperty("testEnvURLInput") == null || System.getProperty("testEnvURLInput") == "") {
			return new Object[] { url.get("url") };
		} else if (System.getProperty("testEnvURLInput") != null || System.getProperty("testEnvURLInput") != "") {
			return new Object[] { System.getProperty("testEnvURLInput") };
		} else {
			scanner = new Scanner(System.in);
			System.out.print("Enter data: ");
			String data = scanner.nextLine();
			System.out.println("Received data: " + data);
			return new Object[] { data };
		}
	}

	/**
	 * GET REMOTE WEB DRIVER URL CHROME
	 *
	 * @return URL as String
	 * @throws Exception
	 */
	public static String getRemoteWebDriverUrlChrome() throws Exception {
		JSONParser json = new JSONParser();
		FileReader file = new FileReader(
				System.getProperty("user.dir") + "//src//test//java//caw//studios//resources//URL.json");
		Object urlObject = json.parse(file);
		JSONObject urlJSONObject = (JSONObject) urlObject;
		JSONObject remoteUrlJSONObject = (JSONObject) urlJSONObject.get("remoteWebDriverURL");
		String chromeUrl = (String) remoteUrlJSONObject.get("urlChrome");
		System.out.println("The remote WebDriver URL being used for chrome is : " + chromeUrl);
		return chromeUrl;
	}

	/**
	 * GET REMOTE WEB DRIVER URL EDGE
	 *
	 * @return URL as String
	 * @throws Exception
	 */
	public static String getRemoteWebDriverUrlEdge() throws Exception {
		JSONParser json = new JSONParser();
		FileReader file = new FileReader(
				System.getProperty("user.dir") + "//src//test//java//caw//studios//resources//URL.json");
		Object urlObject = json.parse(file);
		JSONObject urlJSONObject = (JSONObject) urlObject;
		JSONObject remoteUrlJSONObject = (JSONObject) urlJSONObject.get("remoteWebDriverURL");
		String edgeUrl = (String) remoteUrlJSONObject.get("urlEdge");
		System.out.println("The remote WebDriver URL being used for edge is : " + edgeUrl);
		return edgeUrl;
	}

	/**
	 * GET REMOTE WEB DRIVER URL FIREFOX
	 *
	 * @return URL as String
	 * @throws Exception
	 */
	public static String getRemoteWebDriverUrlFirefox() throws Exception {
		JSONParser json = new JSONParser();
		FileReader file = new FileReader(
				System.getProperty("user.dir") + "//src//test//java//caw//studios//resources//URL.json");
		Object urlObject = json.parse(file);
		JSONObject urlJSONObject = (JSONObject) urlObject;
		JSONObject remoteUrlJSONObject = (JSONObject) urlJSONObject.get("remoteWebDriverURL");
		String foxUrl = (String) remoteUrlJSONObject.get("urlFirefox");
		System.out.println("The remote WebDriver URL being used for firefox is : " + foxUrl);
		return foxUrl;
	}

	/**
	 * GET GRID URL
	 *
	 * @return URL as String
	 * @throws Exception
	 */
	public static String getGridUrl() throws Exception {
		JSONParser json = new JSONParser();
		FileReader file = new FileReader(
				System.getProperty("user.dir") + "//src//test//java//caw//studios//resources//URL.json");
		Object urlObject = json.parse(file);
		JSONObject urlJSONObject = (JSONObject) urlObject;
		JSONObject remoteUrlJSONObject = (JSONObject) urlJSONObject.get("remoteWebDriverURL");
		String gridUrl = (String) remoteUrlJSONObject.get("gridURL");
		System.out.println("The remote GRID URL being used is : " + gridUrl);
		return gridUrl;
	}
}