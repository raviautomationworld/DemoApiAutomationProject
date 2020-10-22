package Tests;
import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) throws Exception {
		
		
	/*	System.setProperty("webdriver.chrome.driver", "E:\\soft\\RestAssuredJars\\chromedriver_win32\\chromedriver.exe");
		
		
		ChromeOptions options = new ChromeOptions();
		
		options.setAcceptInsecureCerts(false);
		
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		
		driver.findElement(By.xpath("//*[@name='identifier']")).sendKeys("ravi.aluvala4a3@gmail.com");
		driver.findElement(By.xpath("//*[@name='identifier']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@name ='password']")).sendKeys("Sweety@143");
		driver.findElement(By.xpath("//*[@name ='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String redirectUrl = driver.getCurrentUrl();
		
		System.out.println(redirectUrl);
		*/
		
		String redirectUrl = "https://rahulshettyacademy.com/getCourse.php?code=4%2F4wFTWVcnyxsSlWoQz08i_xQHGHx4snichDo7V-GHwIrcA6sQtEuOxA3wEIm1G2gBjuNH4xO31Poyz-4OlNI4qBc&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		
		String partialCode = redirectUrl.split("code=")[1];
		
		String code = partialCode.split("&scope")[0];
		
		
		String accessTokenResponse= given().urlEncodingEnabled(false)
				.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		
		String accessToken= js.getString("access_token");
		
		
		String finalResponse = given().queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		
		System.out.println(finalResponse);

	}

}
