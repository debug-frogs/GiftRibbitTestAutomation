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
import TestCases.TeacherLogin;


public class TeacherWishlist {

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

        // Run Teacher Login Test Case
        TeacherLogin.loginTeacherTest(driver);
        
        // Find Classroom button and click button
        WebElement classButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[1]/div[1]/div/div/div/div[4]/div/div[1]/a"));
        classButton.click();
        		
        Thread.sleep(20000);
        
        // Checks if Classroom Page has opened
        String currentURL = driver.getCurrentUrl();
        boolean matchURL = currentURL.indexOf("classroom")>=0;
        Assert.assertTrue(matchURL);
        System.out.println("Classroom Page has opened!");
        
        String itemTitle = "Test Item";
        String editedTitle = "Test Item Edited";
        
        // Test adding item to wish list
        addItemWishlistTest(driver, itemTitle);
        
        Thread.sleep(2000);
        
        // Test edit item from wish list
        editItemWishlistTest(driver, itemTitle, editedTitle);
        
        Thread.sleep(2000);
        
        // Test remove item from wishlist
        removeItemWishlistTest(driver, editedTitle);
        
        Thread.sleep(5000);
        
        //Close the browser
        driver.quit();

	}
	
	
	public static void addItemWishlistTest(WebDriver driver, String itemTitle) throws InterruptedException{
		
		System.out.println("\n--------Testing the Add Item to Wishlist Test Case--------\n");

		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Find Edit Wishlist button and click button
		WebElement editListButton = driver.findElement(By.id("item-menu-button"));
		clickUninteractableElement(editListButton, driver);
        System.out.println("Clicked Edit Wishlist Button");
		
        // Find Add Item and click button
        WebElement addItemButton = driver.findElement(By.xpath("//*[@id=\"item-positioned-menu\"]/div[3]/ul/li[1]"));
		addItemButton.click();
        System.out.println("Clicked Add Item Button");

		// Find Item Summary Input and Input Test
        WebElement itemInput = driver.findElement(By.id("add-item-name-input"));
        itemInput.sendKeys(itemTitle);
        System.out.println("Test Item Summary has been entered");
        
        // Find Item URL Input and Input URL
        WebElement urlInput = driver.findElement(By.id("add-item-url-input"));
        urlInput.sendKeys("https://www.amazon.com/");
        System.out.println("Test Item URL has been entered");
        
        // Find Item Description Input and Input Description
        WebElement descInput = driver.findElement(By.id("add-item-description-input"));
        descInput.sendKeys("This is the Test Item description");
        System.out.println("Test Item Description has been entered");
        
        // Submit new item
        WebElement submitButton = driver.findElement(By.id("add-item-submit-button"));
        submitButton.submit();
        System.out.println("Add Item Submit Button was clicked");
        
        // Check if Test Item was added
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + itemTitle + "')]"));
        Assert.assertTrue(list.size() > 0); 
        System.out.println("Test Item was added successfully to Wishlist!");
        
        System.out.println("--------------------------------------------------\n");

	}
	
	public static void editItemWishlistTest(WebDriver driver, String itemTitle, String editedTitle) throws InterruptedException{
		
		System.out.println("\n--------Testing the Edit Item from Wishlist Test Case--------\n");

		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Find Edit Wishlist button and click button
		WebElement editListButton = driver.findElement(By.id("item-menu-button"));
		clickUninteractableElement(editListButton, driver);
        System.out.println("Clicked Edit Wishlist Button");
		        
        // Find Edit Item and click button
        WebElement editItemButton = driver.findElement(By.xpath("//*[@id=\"item-positioned-menu\"]/div[3]/ul/li[2]"));
        editItemButton.click();
        System.out.println("Clicked Edit Item Button");

        // Find Edit Specific Item Button Next to Desired Item in List
        String hyphenTitle = itemTitle.replace(' ', '-');
        WebElement editSpecItem = driver.findElement(By.id("wishlist-edit-button-"+hyphenTitle));
        clickUninteractableElement(editSpecItem, driver);
        System.out.println("Clicked Edit Desired Item Button");
        
		// Find Item Summary Input and input Edited text
        WebElement itemInput = driver.findElement(By.id("item-name-input"));
        itemInput.sendKeys(Keys.chord(Keys.COMMAND,"a", Keys.DELETE));
        itemInput.sendKeys(editedTitle);
        System.out.println("Test Item Summary has been edited");
        
        // Find Item URL Input and Edit URL
        WebElement urlInput = driver.findElement(By.id("item-url-input"));
        urlInput.sendKeys(Keys.chord(Keys.COMMAND,"a", Keys.DELETE));
        urlInput.sendKeys("https://www.target.com/");
        System.out.println("Test Item URL has been edited");
        
        // Find Item Description Input and Edit
        WebElement descInput = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div/div[4]/div/div/textarea[1]"));
        descInput.sendKeys(Keys.chord(Keys.COMMAND,"a", Keys.DELETE));
        descInput.sendKeys("This is the new and improved Test Item description");
        System.out.println("Test Item Description has been edited");
        
        // Submit Edited Item
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div/div[5]/button"));
        submitButton.submit();
        System.out.println("Edit Item Submit Button has been clicked");
        
        // Check if Test Item was edited
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + editedTitle + "')]"));
        Assert.assertTrue(list.size() > 0); 
        System.out.println("Test Item was edited successfully from Wishlist!");
        
        System.out.println("--------------------------------------------------\n");

	}

	public static void removeItemWishlistTest(WebDriver driver, String itemTitle) throws InterruptedException{
		
		System.out.println("\n--------Testing the Remove Item from Wishlist Test Case--------\n");

		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Find Edit Wishlist button and click button
		WebElement editListButton = driver.findElement(By.id("item-menu-button"));
		clickUninteractableElement(editListButton, driver);
        System.out.println("Clicked Edit Wishlist Button");
		        
        // Find Remove Item and click button
        WebElement removeItemButton = driver.findElement(By.xpath("//*[@id=\"item-positioned-menu\"]/div[3]/ul/li[3]"));
        removeItemButton.click();
        System.out.println("Clicked Remove Item Button");

        // Find Edit Specific Item Button Next to Desired Item in List
        String hyphenTitle = itemTitle.replace(' ', '-');
        
        WebElement editSpecItem = driver.findElement(By.id("wishlist-remove-button-"+hyphenTitle));
        clickUninteractableElement(editSpecItem, driver);
        System.out.println("Clicked Remove Desired Item Button");
        
        // Submit Remove Item
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div[3]/button"));
        submitButton.click();
        System.out.println("Remove Item Submit Button has been clicked");
        
        Thread.sleep(5000);
        
        // Check if Test Item was removed
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + itemTitle + "')]"));
        Assert.assertFalse(list.size()>0); 
        System.out.println("Test Item was removed successfully from Wishlist!");
        
        System.out.println("--------------------------------------------------\n");

	}
	
	public static WebElement getShadowDOM(WebElement element, WebDriver driver) {
		
		// Find ShadowRoot DOM Elements using JavaScriptExecutor -___-
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
		return shadowDom;
	}
	
	public static void clickUninteractableElement (WebElement element, WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}

}
