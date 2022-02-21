package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TvSearch {
	static	 WebDriver driver;

	@BeforeClass(groups = "one")
	public void driverSetUp() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}
	@AfterClass(groups = "one")
	public void closeDriver() {
		driver.quit();

	}
	@Test(priority=0,groups = "one")
	public void login() throws InterruptedException {
		Thread.sleep(5000);

		try {
			WebElement Close= driver.findElement(By.xpath("//button[text()='✕']"));
			Close.click();
		} catch (Exception e) {
			System.out.println("Login Not Needed");
		}
	}
	
	@Test(priority=1,groups = "one")
	public  void MobileSearch() throws InterruptedException {
		Thread.sleep(5000);

		WebElement MobileName= driver.findElement(By.name("q"));
		MobileName.sendKeys("HP Laptop"+Keys.ENTER);
		WebElement name=	driver.findElement(By.xpath("//div[text()='HP Core i3 11th Gen - (8 GB/512 GB SSD/Windows 11 Home) 15s-dy3501TU Thin and Light Laptop']"));
		String LapName= name.getText();
		System.out.println("Laptop Name is :"+LapName);


	}

}
