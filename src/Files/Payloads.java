package Files;

public class Payloads {
	
	public static String AddPlace()
	{
		String body = "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 897 8431 2020\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 10\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
				
		return body;
	}
	
	
	public static String UpdatePlace(String placeid, String newAddress)
	{
		
		String body ="{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}";
		
		return body;
	}
	
	
	public static String MokeApiForCoursePrice()
	{
		
		String body = "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 910,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
		
		return body;
	}

	
	public static String AddBookToLibrary(String isbn, String aisle)
	{
		
		String body = "{\r\n" + 
				"\r\n" + 
				"\"name\":\"API Automation Learning\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"Ravi Aluvala\"\r\n" + 
				"}\r\n" + 
				"";
		
		return body;
	}
	
	public static String DeleteBookFromLibrary(String isbn, String aisle)
	{
		
		String body = "{\r\n" + 
				"\r\n" + 
				"\"ID\" : \""+isbn+aisle+"\"\r\n" + 
				"\r\n" + 
				"} \r\n" + 
				"";
		
		return body;
	}
	
}
