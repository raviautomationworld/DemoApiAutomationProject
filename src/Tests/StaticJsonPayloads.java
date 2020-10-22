package Tests;

import static io.restassured.RestAssured.given;

import java.nio.file.Paths;

import org.testng.annotations.Test;



import Files.Payloads;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJsonPayloads {

	
	@Test()	
	public void AddBookTest() throws Exception
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBookResponse = given().log().all().header("Content-Type","application/json")
		.body(ReusableMethods.GenerateStringFromJson("AddBookStaticJson.json"))
		.when().post("/Library/Addbook.php")
		.then().log().all().extract().body().asString();
		
		JsonPath addBookResponseJson = ReusableMethods.rawToJson(addBookResponse);
		String id = addBookResponseJson.get("ID");
		System.out.println("Added Book to Library with :::::::: "+id);
		
	}
	
	
}
