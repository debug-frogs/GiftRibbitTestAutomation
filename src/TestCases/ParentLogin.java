package TestCases;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ParentLogin {

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
       
        System.out.println("\n--------Testing the Parent Login Test Cases--------\n");
        
        // Navigate to GiftRibbit Home Page and maximize window
        driver.navigate().to(appURL);
        driver.manage().window().maximize();
        
        System.out.println("Home Page has opened!");
        
        // Click on Login button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/header/div/div/div[2]/a")).click();
        System.out.println("Login Page has opened!");

        // Run the Invalid Email Teacher Login Test
        loginInvalidEmailParentTest(driver);
        
        // Run the Invalid Email Teacher Login Test
        loginInvalidPassParentTest(driver);
        
		// Run the Valid Parent Login Test
        loginParentTest(driver);
        
        Thread.sleep(5000);
        
        //Close the browser
        driver.quit();

	}
	public static void loginParentTest(WebDriver driver) throws InterruptedException{
		
		System.out.println("\n--------Testing the Valid Parent Login Test Case--------\n");
        
		// Find the element of first shadow host
        WebElement root = driver.findElement(By.tagName("amplify-sign-in"));
        
        // Execute Script on shadow host
        WebElement shadowRoot1 = getShadowDOM(root,driver);
        
        // Find Email Element and Input Email Address
        WebElement emailInput = shadowRoot1.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys("alejandra.soto.50@my.csun.edu");
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
        
        // Check if system has logged parent in
        String profileViewURL = "https://staging.dvcphyfdliprj.amplifyapp.com/profile";
        String currentURL = driver.getCurrentUrl();
        Boolean matchURL =  currentURL.equalsIgnoreCase(profileViewURL);
        Assert.assertTrue(matchURL);
        System.out.println("System has logged Parent into their account.");
		
		System.out.println("--------------------------------------------------\n");
	}
	
	public static void loginInvalidEmailParentTest(WebDriver driver) throws InterruptedException{
		
		System.out.println("\n--------Testing the Invalid Email Parent Login Test Case--------\n");
		
		// Find the element of first shadow host
        WebElement root = driver.findElement(By.tagName("amplify-sign-in"));
        
        // Execute Script on shadow host
        WebElement shadowRoot1 = getShadowDOM(root,driver);
        
        // Find Email Element and Input Email Address
        WebElement emailInput = shadowRoot1.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys("alejandra.soto.50");
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
        
        // Check if system has logged teacher in or blocked and notify result
        String profileViewURL = "https://staging.dvcphyfdliprj.amplifyapp.com/profile";
        String currentURL = driver.getCurrentUrl();
        Boolean matchURL =  currentURL.equalsIgnoreCase(profileViewURL); 
        Assert.assertFalse(matchURL);
        System.out.println("System has blocked Parent from logging in.");
		
		System.out.println("--------------------------------------------------\n");
	}
	
	public static void loginInvalidPassParentTest(WebDriver driver) throws InterruptedException{
		
		System.out.println("\n--------Testing the Invalid Parent Password Login Test Case--------\n");
		
		// Find the element of first shadow host
        WebElement root = driver.findElement(By.tagName("amplify-sign-in"));
        
        // Execute Script on shadow host
        WebElement shadowRoot1 = getShadowDOM(root,driver);
        
        // Find Email Element and Input Email Address
        WebElement emailInput = shadowRoot1.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys("alejandra.soto.50@my.csun.edu");
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
        
        // Check if system has logged parent in or blocked and notify result
        String profileViewURL = "https://staging.dvcphyfdliprj.amplifyapp.com/profile";
        String currentURL = driver.getCurrentUrl();
        Boolean matchURL =  currentURL.equalsIgnoreCase(profileViewURL);
        Assert.assertFalse(matchURL);
        System.out.println("System has blocked Parent from logging in.");
		
		System.out.println("--------------------------------------------------\n");
	}
	
	
	public static WebElement getShadowDOM(WebElement element, WebDriver driver) {
		
		// Find ShadowRoot DOM Elements using JavaScriptExecutor -___-
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
		//WebElement shadowDom = (WebElement) js.executeScript("return document.querySelector().shadowRoot", element);
		return shadowDom;
	}

}
