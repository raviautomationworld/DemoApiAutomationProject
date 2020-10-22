package Tests;

import Files.Payloads;
import Files.ReusableMethods;
import io.restassured.path.json.JsonPath;

public class MokeTestWithComplexJsonParse {
	
	
	public static void main(String args[])
	{
		JsonPath coursePriceResponse = ReusableMethods.rawToJson(Payloads.MokeApiForCoursePrice()); 
			
		//1. Print No of courses returned by API
		
		int count = coursePriceResponse.getInt("courses.size()");
		
		System.out.println("No of Courese returneb by API are :::: "+count);

		//2. Print Purchase Amount
		
		int purchaseAmount = coursePriceResponse.getInt("dashboard.purchaseAmount");
		
		System.out.println("Purchase Amount of Courses is ::::: "+purchaseAmount);
		
		//3. Print Title of the first course
		
		String titleFirstCourse = coursePriceResponse.getString("courses[0].title");
		
		System.out.println("Title of the first course is ::::: "+titleFirstCourse);
		
		//4. Print all course titles and their respective Prices
		
		System.out.println("All course titles and their respective Prices ::::::::::::");
		
		for(int i = 0; i<count;i++)
		{
			String courseTitle =coursePriceResponse.get("courses["+i+"].title");
			
			int coursePrice = coursePriceResponse.getInt("courses["+i+"].price");
			
			System.out.println(courseTitle+" - "+coursePrice);
		}
		
		
		//5. Print no of copies sold by RPA Course
		
		for(int i = 0; i<count; i++)
		{
			String courseTitle =coursePriceResponse.get("courses["+i+"].title");
			
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				int courseCopies = coursePriceResponse.get("courses["+i+"].copies");
				
				System.out.println("No of copies sold by RPA Course ::::: "+courseCopies);
				
				break;
			}
			
			
		}
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int sum = 0;
		
		for(int i = 0;i<count;i++)
		{
			
			int courseCopies = coursePriceResponse.get("courses["+i+"].copies");
			int coursePrice = coursePriceResponse.get("courses["+i+"].price");
			
			sum = sum + (courseCopies*coursePrice);
			
		}
		
		System.out.println("Price of all courses after sum of individual coures price :::: "+sum);
		
		if(purchaseAmount == sum)
		{
			System.out.println("Purchase Amount matches with Price of all courses");
		}
		
		
	}

}
