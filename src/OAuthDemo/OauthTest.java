package OAuthDemo;

import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;

public class OauthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\this pc\\Downloads\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Ramtmp27@gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);*/
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qjqjNMh-0itGb1Je5M-O2dSZfe2SSsMtUpBHCdG-oETm7WQVhI4F5D9GciUEw0FnA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none";
		String partialcod=url.split("code=")[1];
		String code=partialcod.split("&scope")[0];
		System.out.println(code);
		
	String accessTokenResponse=	given().urlEncodingEnabled(false).queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js=new JsonPath(accessTokenResponse);
		String accessToken= js.getString("access_token");
		
		
		String response= given().queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	}
}
