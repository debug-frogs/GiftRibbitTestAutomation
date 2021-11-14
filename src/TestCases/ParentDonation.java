package TestCases;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import TestCases.ParentLogin;


public class ParentDonation {

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
        
        System.out.println("\n--------Testing the Teacher Wishlist Test Cases--------\n");
        
        // Navigate to GiftRibbit Home Page and maximize window
        driver.navigate().to(appURL);
        driver.manage().window().maximize();
        System.out.println("Home Page has opened!");
        
        // Click on Login button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/header/div/div/div[2]/a")).click();
        System.out.println("Login Page has opened!");

        // Run Parent Login Test Case
        ParentLogin.loginParentTest(driver);
        
        // Find Classroom button and click button
        String classroom = "Dale";
        WebElement classButton = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div[3]/div/child::*//a[contains(text(),'"+ classroom + "')]"));
        clickUninteractableElement(classButton, driver);
        		
        Thread.sleep(10000);
        
        // Checks if Classroom Page has opened
        String currentURL = driver.getCurrentUrl();
        boolean matchURL = currentURL.indexOf("classroom")>=0;
        Assert.assertTrue(matchURL);
        System.out.println("Classroom Page has opened!");
        
        // Test donating item from wish list
        String itemTitle = "Frog Backpack";
        donateItemWishlist(driver, itemTitle);
        
        Thread.sleep(10000);
        
        //Close the browser
        driver.quit();

	}
	
	
	public static void donateItemWishlist(WebDriver driver, String itemTitle) throws InterruptedException{
		
		System.out.println("\n--------Testing the Donate Item from Wishlist Test Case--------\n");

		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Find Contribute a donation button and click button
		WebElement donateButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[1]/div[1]/div/div[1]/div/div/div/div[3]/div/div/div[4]/div/div/button"));
		clickUninteractableElement(donateButton, driver);
        System.out.println("Clicked 'Contribute a donation' Button");
        
        // Select checkbox of desired item from list
        String hyphenTitle = itemTitle.replace(' ', '-');
        WebElement checkbox = driver.findElement(By.id("add-item-checkbox-" + hyphenTitle));
        checkbox.click();
        System.out.println("Selected first item from Wishlist");

        // Donate Items Button
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div[3]/button"));
        submitButton.click();
        System.out.println("Clicked Donate Items button");
        
        
        // Check if Test Item was donated
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div/div/div/div[1]/div[1]/div/div[1]/div/div/div/div[5]/div/div/following::*[contains(text(),'" + itemTitle + "')]"));
        Assert.assertTrue(list.size() > 0); 
        System.out.println("Item was donated successfully!");
        
        System.out.println("--------------------------------------------------\n");

	}
	
	public static void clickUninteractableElement (WebElement element, WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}

}
