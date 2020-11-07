package Tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payloads;
import Files.ReusableMethods;

public class BasicsTest {
	
	/*
	 * case 1) validate if APP Place API is working as expected
	 * case 2) Add Place -> Update Place with New Address -> Get Place to validates if new Address is present in response
	 */

	
	/*
	 * Rest Assured work on principle of 
	 * 
	 * given -> all input details
	 * when -> submit the API - resource and http method will be in when method
	 * Then -> Validate the response
	 */
	
	public static void main(String args[])
	{
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// Add Place
		
		String postResponse  = given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body(Payloads.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.body("status", equalTo("OK"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().body().asString() ;
		
		System.out.println(postResponse);
		
		JsonPath postResponseJson = ReusableMethods.rawToJson(postResponse);
		
		System.out.println(postResponseJson);
		
		String placeid = postResponseJson.getString("place_id");
		
		System.out.println(placeid);
		
		
		// Update Place by using place id and address
		
		String newAddress = "80 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payloads.UpdatePlace(placeid, newAddress)).when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		// Get Palce by place id  and validate address
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParams("place_id",placeid)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath getPlaceResponseJson = ReusableMethods.rawToJson(getPlaceResponse);
		
		String actualAddress = getPlaceResponseJson.getString("address");
		
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
		
	}
}
