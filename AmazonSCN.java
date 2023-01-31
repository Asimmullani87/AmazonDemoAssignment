package AmazonTest;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.temporal.ValueRange;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonSCN {
	WebDriver driver;

	@BeforeMethod
	public void Setup() {
		System.setProperty("Webdriver.chromedriver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	//1.	Searching for a product: The test should search for a specific product and verify that the correct search results are displayed.
	@Test(description = "Step 1 Searching for Product")
	public void Searchingforaproduct() throws InterruptedException {
		WebElement Search_product = driver.findElement(By.id("searchDropdownBox"));
		Select objSelect = new Select(Search_product);
		objSelect.selectByIndex(16);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Mobile");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(3000);
		if (driver.getPageSource().contains("Techno spark 9")) {
			System.out.println("String Found:");
		} else {
			System.out.println("String not available");
		}
	}
	//2.	Filtering the search results: The test should filter the search results by price range and verify that the filtered results are displayed correctly.
	@Test(description = "Step 2 Searching for Product")
	public void FilteringThSearchResults() {
		driver.findElement(By.xpath("//span[contains(text(),'₹1,000 - ₹5,000')][1]")).click();
		String MobilePriceRange = driver.findElement(By.xpath("//span[text()='1,324'][1]")).getText();
		System.out.println("String available" + MobilePriceRange);
		int value = 1324;
		if (ValueRange.of(1000, 5000).isValidIntValue(value)) {
			System.out.println("Value is with in the Range.");
		} else {
			System.out.println("Value is out of the Range.");
		}

	}
	//3.	Adding a product to the shopping cart: The test should navigate to a product page, select the desired options (such as size or color), and add the product to the shopping cart.
	@Test(description = "Step 3 Adding a product to the shopping cart")
	public void AddingProductToShippingCart() throws InterruptedException {
		driver.findElement(By.xpath(
				"//img[@alt=\"Nokia 105 Plus Single SIM, Keypad Mobile Phone with Wireless FM Radio, Memory Card Slot and MP3 Player | Red\"]"))
				.click();
		Thread.sleep(3000);
		driver.navigate().to(
				"https://www.amazon.in/Nokia-105-Single-Keypad-Wireless/dp/B09YDFDVNS/ref=sr_1_1?crid=1ADBXKVWWNY07&keywords=Mobile&qid=1675101665&refinements=p_36%3A1318504031&rnid=1318502031&s=electronics&sprefix=%2Celectronics%2C1432&sr=1-1&th=1");
		Thread.sleep(3000);

	}
	//4.	Proceeding to checkout: The test should proceed to the checkout page and verify that the correct product and options are displayed in the shopping cart.
	@Test(description = "Step 4 Proceeding to checkout")
	public void ProceedingToCheckout() {
		driver.navigate().to("https://www.amazon.in/cart/smart-wagon?newItems=C8e2f8942-e97b-4ce2-91b0-aa1717323b15,1");
		if (driver.getPageSource().contains("Nokia 105 Plus")) {
			System.out.println("Product is Available in cart:");
		} else {
			System.out.println("product not available");
		}
		driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
		String check = driver.findElement(By.xpath("//h1")).getText();
		Assert.assertFalse(true);

	}
	//5.	Filling out the checkout form: The test should fill out the checkout form with the user's personal and payment information and submit the form.
	@Test(description = "Step 5 Fillingoutthecheckoutform")
	public void Fillingoutthecheckoutform() {
		driver.findElement(By.xpath("//a[@id='add-new-address-popover-link']")).click();
		driver.findElement(By.xpath("input[@aria-label='Mobile number']")).sendKeys("84848484855");
		driver.findElement(By.xpath("//input[@aria-label='Full name']")).sendKeys("Asim Mullani");
		driver.findElement(By.xpath("input[@aria-label='Pincode']")).sendKeys("416012");
		driver.findElement(By.xpath("//input[@aria-label='Area, Street, Sector, Village']")).sendKeys("phulenagar");
		driver.findElement(By.xpath("//input[@aria-label='Landmark']")).sendKeys("Datta Mandir");
		driver.findElement(By.xpath("//input[@aria-label='Town/City']")).sendKeys("pune");
		WebElement Search_State = driver.findElement(By.xpath("//span[contains(text(),'Choose a state')]"));
		Select objSelect = new Select(Search_State);
		objSelect.selectByIndex(5);
		driver.findElement(By.xpath("//span[@id='shipToThisAddressButton-announce']")).click();

	}

	@AfterMethod
	public void teardDown() {
		driver.quit();
	}
}