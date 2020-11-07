package Tests;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import Pojo.Api;
import Pojo.CreateToken;
import Pojo.GetCourseResponse;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class DeserilizationWithPojo {

	public static void main(String[] args) throws Exception {
		
	/*
		System.setProperty("webdriver.chrome.driver", "E:\\SeleniumTeaching\\Softwares\\chromedriver_win32\\chromedriver.exe");
		
		
		ChromeOptions options = new ChromeOptions();
		
		options.setAcceptInsecureCerts(true);
		
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		
		driver.findElement(By.xpath("//*[@name='identifier']")).sendKeys("XXXXX@gmail.com");
		driver.findElement(By.xpath("//*[@name='identifier']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@name ='password']")).sendKeys("XXXX");
		driver.findElement(By.xpath("//*[@name ='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String redirectUrl = driver.getCurrentUrl();
		
		System.out.println(redirectUrl);
		*/
	
		String redirectUrl = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g6lft1wWXGKFt_bLekN07KXbuBInJUAzEr4JB3LyvEey1xAO7_1l6svDhPmOtjhRQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		String partialCode = redirectUrl.split("code=")[1];
		
		String code = partialCode.split("&scope")[0];
		
		
		CreateToken accessTokenResponse= given().urlEncodingEnabled(false)
				.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").as(CreateToken.class);
		
		System.out.println("==========================================");
		System.out.println("===========Print Response as String==============================");
		System.out.println(accessTokenResponse);
		System.out.println("==========================================");
		
		System.out.println("===========Priont Response as Json==============================");
		//JsonPath js = new JsonPath(accessTokenResponse);
		
		System.out.println(accessTokenResponse);
		
		System.out.println("==========================================");
		
		String accessToken= accessTokenResponse.getAccess_token();
		
		System.out.println("Printing the Acces Token for which is generated :: "+accessToken);
		
		
		GetCourseResponse finalResponse = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourseResponse.class);
		
		System.out.println("==========================================");
		System.out.println("===========Print Final Response as String==============================");
		
		System.out.println(finalResponse);
		System.out.println("=======================================================================");
		System.out.println("=================Validations starts from response=============================");
		System.out.println("=======================================================================");
		System.out.println("Instructor Name::::::: "+finalResponse.getInstructor());
		
		System.out.println("Linked In Link::::::: "+finalResponse.getLinkedIn());
		
		System.out.println("Instructor URL::::::: "+finalResponse.getUrl());
		
		System.out.println("Instructor Expertise Name::::::: "+finalResponse.getExpertise());
		System.out.println("Instructor Services::::::: "+finalResponse.getServices());
		
		
		System.out.println("Instructor WbeAutomation Couses Count::::::: "+finalResponse.getCourses().getWebAutomation().size());
		
		System.out.println("Instructor WebAutomation 1st Course Name::::::: "+finalResponse.getCourses().getWebAutomation().get(0).getCourseTitle());
		
		System.out.println("Instructor WebAutomation 1st Course Price:::::::"+finalResponse.getCourses().getWebAutomation().get(0).getPrice());
		
		/*
		 * List out all courses are expected are NOT in API
		 */
		
		String[] expectedApiCourses = {"Rest Assured Automation using Java","SoapUI Webservices testing"};
		
		 List<Api> apiCourses = finalResponse.getCourses().getApi();
		 
		 ArrayList<String> actualApiCoursesList= new ArrayList<String>();
		 
		 int n = apiCourses.size();
		 
		 for(int i = 0;i<n;i++)
		 {
			 actualApiCoursesList.add(apiCourses.get(i).getCourseTitle());
		 }
		 
		 List<String> expectedApiCoursesList= Arrays.asList(expectedApiCourses);
		 
		 System.out.println(actualApiCoursesList.equals(expectedApiCoursesList));
		 
		 Assert.assertTrue(actualApiCoursesList.equals(expectedApiCoursesList));
		 

		System.out.println("=======================================================================");
		System.out.println("=================Validations ended=============================");
		System.out.println("=======================================================================");
	}

}
