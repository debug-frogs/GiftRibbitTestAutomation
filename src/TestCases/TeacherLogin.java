package TestCases;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TeacherLogin {

	public static void main(String[] args) throws InterruptedException {

		// SETUP
		System.out.println("Opening Chrome Browser");
		System.setProperty("webdriver.chrome.driver","/Users/alekins/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		// Wait for page contents to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// BUG API URL
        //String appURL = "https://bug-api.dvcphyfdliprj.amplifyapp.com/";
        
        // STAGING URL
        String appURL = "https://staging.dvcphyfdliprj.amplifyapp.com/";
        
        System.out.println("\n--------Testing the Teacher Login Test Cases--------\n");
        
        // Navigate to GiftRibbit Home Page and maximize window
        driver.navigate().to(appURL);
        driver.manage().window().maximize();
        System.out.println("Home Page has opened!");
        
        // Click on Login button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/header/div/div/div[2]/a")).click();
        System.out.println("Login Page has opened!");
		
        // Run the Invalid Email Teacher Login Test
        loginInvalidEmailTeacherTest(driver);
        
        // Run the Invalid Email Teacher Login Test
        loginInvalidPassTeacherTest(driver);
        
		// Run the Valid Teacher Login Test
		loginTeacherTest(driver);
        
        // Close the browser
        driver.quit();

	}
	
	public static void loginTeacherTest(WebDriver driver) throws InterruptedException{
		
		System.out.println("\n--------Testing the Valid Teacher Login Test Case--------\n");
		
        // Find the element of first shadow host
        WebElement root = driver.findElement(By.tagName("amplify-sign-in"));
        
        // Execute Script on shadow host
        WebElement shadowRoot1 = getShadowDOM(root,driver);
        
        // Find Email Element and Input Email Address
        WebElement emailInput = shadowRoot1.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys("doodleboop.dale.dan.dawson@gmail.com");
        System.out.println("Email address has been entered");
        
        // Find Password Element and Input Password
        WebElement passInput = shadowRoot1.findElement(By.id("password"));
        passInput.clear();
        passInput.sendKeys("test1234");
        System.out.println("Password has been entered");

        // Find Sign-in Button and Click button 
        WebElement shadowRoot2 = getShadowDOM(root,driver);
        WebElement submitForm = shadowRoot2.findElement(By.cssSelector("button.button"));
        submitForm.submit();
        System.out.println("Sign-in button has been clicked");
        
        Thread.sleep(5000); 
        
        // Check if system has logged teacher in
        String profileViewURL = "https://staging.dvcphyfdliprj.amplifyapp.com/profile";
        String currentURL = driver.getCurrentUrl();
        Boolean matchURL =  currentURL.equalsIgnoreCase(profileViewURL);
        Assert.assertTrue(matchURL);
        System.out.println("System has logged Teacher into their account.");
        System.out.println("--------------------------------------------------\n");

        
	}
	
	public static void loginInvalidEmailTeacherTest(WebDriver driver) throws InterruptedException{
		
		System.out.println("\n--------Testing the Invalid Email Teacher Login Test Case--------\n");
		
		// Find the element of first shadow host
        WebElement root = driver.findElement(By.tagName("amplify-sign-in"));
        
        // Execute Script on shadow host
        WebElement shadowRoot1 = getShadowDOM(root,driver);
        
        // Find Email Element and Input Email Address
        WebElement emailInput = shadowRoot1.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys("doodleboop.dale.dan.dawson");
        System.out.println("Invalid email address has been entered");
        
        // Find Password Element and Input Password
        WebElement passInput = shadowRoot1.findElement(By.id("password"));
        passInput.clear();
        passInput.sendKeys("test1234");
        System.out.println("Password has been entered");

        // Find Sign-in Button and Click button 
        WebElement shadowRoot2 = getShadowDOM(root,driver);
        WebElement submitForm = shadowRoot2.findElement(By.cssSelector("button.button"));
        submitForm.submit();
        System.out.println("Sign-in button has been clicked");
        
        Thread.sleep(5000); 
        
        // Check if system has logged teacher in or blocked and notify result
        String profileViewURL = "https://staging.dvcphyfdliprj.amplifyapp.com/profile";
        String currentURL = driver.getCurrentUrl();
        Boolean matchURL =  currentURL.equalsIgnoreCase(profileViewURL);
        Assert.assertFalse(matchURL);
        System.out.println("System has blocked Teacher from logging in.");
        System.out.println("--------------------------------------------------\n");

	}
	
	public static void loginInvalidPassTeacherTest(WebDriver driver) throws InterruptedException{
		
		System.out.println("\n--------Testing the Invalid Password Teacher Login Test Case--------\n");
		
		// Find the element of first shadow host
        WebElement root = driver.findElement(By.tagName("amplify-sign-in"));
        
        // Execute Script on shadow host
        WebElement shadowRoot1 = getShadowDOM(root,driver);
        
        // Find Email Element and Input Email Address
        WebElement emailInput = shadowRoot1.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys("doodleboop.dale.dan.dawson@gmail.com");
        System.out.println("Email address has been entered");
        
        // Find Password Element and Input Password
        WebElement passInput = shadowRoot1.findElement(By.id("password"));
        passInput.clear();
        passInput.sendKeys("wrongPassword");
        System.out.println("Invalid password has been entered");

        // Find Sign-in Button and Click button 
        WebElement shadowRoot2 = getShadowDOM(root,driver);
        WebElement submitForm = shadowRoot2.findElement(By.cssSelector("button.button"));
        submitForm.submit();
        System.out.println("Sign-in button has been clicked");
        
        Thread.sleep(5000); 
        
        // Check if system has logged teacher in or blocked and notify result
        String profileViewURL = "https://staging.dvcphyfdliprj.amplifyapp.com/profile";
        String currentURL = driver.getCurrentUrl();
        Boolean matchURL =  currentURL.equalsIgnoreCase(profileViewURL);
        Assert.assertFalse(matchURL);
        System.out.println("System has blocked Teacher from logging in.");
        System.out.println("--------------------------------------------------\n");
	}
	
	public static WebElement getShadowDOM(WebElement element, WebDriver driver) {
		
		// Find ShadowRoot DOM Elements using JavaScriptExecutor -___-
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
		return shadowDom;
	}
}
