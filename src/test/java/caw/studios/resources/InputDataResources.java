package caw.studios.resources;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class InputDataResources {

	@DataProvider(name = "inputData")
	public static Object[] readInputJSONData() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader file = new FileReader(
				System.getProperty("user.dir") + "//src//test//java//caw//studios//resources//InputData.json");
		Object obj = jsonParser.parse(file);
		return new Object[] { obj };
	}
}
