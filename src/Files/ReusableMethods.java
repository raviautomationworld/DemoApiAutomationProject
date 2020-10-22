package Files;

import java.nio.file.Paths;
import java.nio.file.Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	
// This Method is String into Json Response
	
	public static JsonPath rawToJson(String response) {
		
		JsonPath js = new JsonPath(response);
		
		System.out.println(js);
		
		return js;
	}
	
// This method will convert Json file into String	
	public static String GenerateStringFromJson(String file) throws Exception
	{
		String json = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/Files/"+file)));
		
		System.out.println(json);
		
		return json;
	}
	
}
