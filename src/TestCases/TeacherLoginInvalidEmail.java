package TestCases;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TeacherLoginInvalidEmail {

	public static void main(String[] args) throws InterruptedException {

		// SETUP
		System.out.println("Opening Chrome Browser");
		System.setProperty("webdriver.chrome.driver","/Users/alekins/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
        // BUG API URL
        //String appURL = "https://bug-api.dvcphyfdliprj.amplifyapp.com/";
        
        // STAGING URL
        String appURL = "https://staging.dvcphyfdliprj.amplifyapp.com/";
       
        // driver.get() does the same as driver.navigate().to()
        // Thread.sleep(1000);
        
        //Navigate to appURL and maximize window
        driver.navigate().to(appURL);
        driver.manage().window().maximize();
        
        System.out.println("Home Page has opened!");
        
        // Click on Login button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/header/div/div/div[2]/a")).click();
        System.out.println("Login Page has opened!");

        // Find the element of first shadow host
        WebElement root = driver.findElement(By.tagName("amplify-sign-in"));
        
        // Execute Script on shadow host
        WebElement shadowRoot1 = getShadowDOM(root,driver);
        
        // Find Email Element and Input Email Address
        WebElement emailInput = shadowRoot1.findElement(By.id("email"));
        emailInput.sendKeys("doodleboop.dale.dan.dawson");
        System.out.println("Invalid email address has been entered");
        
        // Find Password Element and Input Password
        WebElement passInput = shadowRoot1.findElement(By.id("password"));
        passInput.sendKeys("test1234");
        System.out.println("Password has been entered");

        // Find Sign-in Button and Click button 
        WebElement shadowRoot2 = getShadowDOM(root,driver);
        WebElement submitForm = shadowRoot2.findElement(By.cssSelector("button.button"));
        submitForm.submit();
        System.out.println("Sign-in button has been clicked");
        System.out.println("Teacher is blocked");
       
        Thread.sleep(50000);
        
        //Close the browser
        driver.quit();

	}
	public static WebElement getShadowDOM(WebElement element, WebDriver driver) {
		
		// Find ShadowRoot DOM Elements using JavaScriptExecutor -___-
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
		//WebElement shadowDom = (WebElement) js.executeScript("return document.querySelector().shadowRoot", element);
		return shadowDom;
	}

}
