package Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payloads;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJsonPayloads {

	
	@Test(dataProvider = "BookData")	
	public void AddBookTest(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBookResponse = given().log().all().header("Content-Type","application/json")
		.body(Payloads.AddBookToLibrary(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().extract().body().asString();
		
		JsonPath addBookResponseJson = ReusableMethods.rawToJson(addBookResponse);
		String id = addBookResponseJson.get("ID");
		System.out.println("Added Book to Library with :::::::: "+id);
		
	}
	
	
	@Test(dataProvider = "BookData")	
	public void DeleteBookTest(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String deleteBookResponse = given().log().all().header("Content-Type","application/json")
		.body(Payloads.DeleteBookFromLibrary(isbn,aisle))
		.when().post("/Library/DeleteBook.php")
		.then().log().all().extract().body().asString();
		
		JsonPath deleteBookResponseJson = ReusableMethods.rawToJson(deleteBookResponse);
		String msg = deleteBookResponseJson.get("msg");
		System.out.println("Delete Book from Library message :::::::: "+msg);
		
	}
	
	
	
	@DataProvider(name = "BookData")
	public Object[][] getBookData()
	{
		
		Object[][] data = new Object[][] {{"abcde","99999"},{"abcde","88888"},{"abcde","77777"}};
		
		return data;
	}
	
}
