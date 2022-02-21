package testNG;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGTask {
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
	static String FirstName;
	@Test(priority=1,groups = "one")
	public  void MobileSearch() throws InterruptedException {
		Thread.sleep(5000);

		WebElement MobileName= driver.findElement(By.name("q"));
		MobileName.sendKeys("iPhone"+Keys.ENTER);
		WebElement Mobile=	driver.findElement(By.xpath("//div[text()='APPLE iPhone 12 Mini (Black, 64 GB)']"));
		FirstName=	Mobile.getText();
		System.out.println(FirstName);
		Mobile.click();

	}
	static String LastName;
	@Test(priority=2,groups = "one")
	public void WindowsHandle() {
		String ParentWindow  =	driver.getWindowHandle();
		Set<String> ChildWindow=driver.getWindowHandles();
		for (String string : ChildWindow) {
			if(!string.equals(ParentWindow)) {
				driver.switchTo().window(string);
				WebElement name=driver.findElement(By.xpath("//span[text()='APPLE iPhone 12 Mini (Black, 64 GB)']"));
				LastName=	name.getText();
				System.out.println(LastName);
			}

		}
	}
	@Test(priority=3,groups = "one")
	public void buyNow() {
		WebElement BuyNowButton=driver.findElement(By.xpath("//button[text()='BUY NOW']"));
		boolean isEnable= BuyNowButton.isEnabled();
		System.out.println("Buy now Option is "+isEnable);

	}
	@Test(priority=4)
	public void nameCheck() {
		if (FirstName.equals(LastName)) {
			System.out.println("Mobile Name is Equal");
		}
		else {
			System.out.println("Mobile Name is Not Equal");
		}
		

	}
	
	@Test(priority=5,enabled = false)
	public void takeSS() throws IOException, InterruptedException {
		Thread.sleep(1000);
		TakesScreenshot screenshot= (TakesScreenshot)driver;
	     File File1	=screenshot.getScreenshotAs(OutputType.FILE);
		 File File2= new File("D:\\WorkPlace\\testNG\\target.photo.jpg");
		 FileUtils.copyFile(File1, File2);

	}




}
