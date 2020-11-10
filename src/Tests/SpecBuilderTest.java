package Tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Files.Payloads;
import Pojo.CreatePlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {
	

	
	public static void main(String args[])
	{
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		CreatePlace reqBody = new CreatePlace();
		Location loc = new Location();
		loc.setLat(-38.383888);
		loc.setLng(33.427333);
		
		List<String> Mytypes = new ArrayList<String>();
		
		Mytypes.add("shoe park");
		Mytypes.add("shop");
		
		reqBody.setLocation(loc);
		reqBody.setAccuracy(60);
		reqBody.setName("Frontline house new");
		reqBody.setPhone_number("(+91) 897 797 2330");
		reqBody.setAddress("29, side layout, cohen 11 for Test");
		reqBody.setTypes(Mytypes);
		reqBody.setWebsite("http://google.com");
		reqBody.setLanguage("French-IN");
		
		System.out.println("===========================");
		
		System.out.println(reqBody);
		
		System.out.println("===========================");
		
		// RequestSpecBuilder for generic method
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com") .addQueryParam("key", "qaclick123")
				.addHeader("Content-Type", "application/json").build();
		
		//RequestSpecification
		
		RequestSpecification reqSpec = given().spec(req).body(reqBody);
		
		// ResponseSpecBuilder and ResponseSpecificaiton
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		
		// Add Place
		
		Response postResponse  = reqSpec.when().post("/maps/api/place/add/json")
		.then().spec(resSpec).extract().response();
		
		String responseInString = postResponse.asString();
		
		System.out.println(responseInString);
		
		
	}

}
