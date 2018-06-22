package jobSearch;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JobSearchAutomation {
	public static void main(String[] args) throws InterruptedException {
		List<String> keywords = new ArrayList();
		keywords.add("SDET");
		keywords.add("Java Developer");
		keywords.add("Test Automation");
		keywords.add("Test Engineer");
		keywords.add("Java");
		keywords.add("Html");
		keywords.add("Selenium web driver");
		keywords.add("Selenium");
		keywords.add("gitHub");
		keywords.add("Maven");
		keywords.add("Cucumber");
		keywords.add("Selenium, cucumber");
		keywords.add("Java SDET");
		keywords.add("Maven, github");
		keywords.add("Maven, selenium");
		keywords.add("application testing");
		keywords.add("SDET");
		keywords.add("java tester");
		keywords.add("java, selenium");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https:\\dice.com");
		driver.manage().window().fullscreen();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String expectedTitle1 = "Job Search for Technology Professionals | Dice.com";
		String actualTitle1 = driver.getTitle();
		if (actualTitle1.equals(expectedTitle1)) {
			System.out.println("Homepage has been loaded successfully!");
		} else {
			System.out.println("homepage has NOT been loaded successfully!");
			System.out.println("expected: " + expectedTitle1);
			System.out.println("actual: " + actualTitle1);
		}
		// System.out.println(actualTitle1);
		// Assert.assertEquals(actualTitle1, expectedTitle1);
		String location="78717";
		for (int i = 0; i < keywords.size(); i++) {
			System.out.println("search number: "+(i+1));
			String keyword=keywords.get(i);
			
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);
			driver.findElement(By.id("findTechJobs")).click();

		
		String actualTitle2 = driver.getTitle();
		int index=actualTitle2.indexOf(" ");
		String expectedTitle2 = driver.getTitle().substring(0, index) + " "+keyword+" jobs in "+location+" | Dice.com" ;
		// System.out.println(actualTitle2);
		// System.out.println( expectedTitle2 );
		if (actualTitle2.equals(expectedTitle2)) {
			System.out.println("Search results page has been loaded successfully!");
		} else {
			System.out.println("Search results page has NOT been loaded successfully!");
			System.out.println("expected: " + expectedTitle2);
			System.out.println("actual: " + actualTitle2);
		}
		// Assert.assertEquals(actualTitle2, expectedTitle2);
		// String count=driver.findElement(By.id("posiCountId")).getAttribute("value");
		// String count=driver.findElement(By.id("posiCountId")).getText();
		// String
		// count=driver.findElement(By.cssSelector("span[id='posiCountId']")).getText();
		// System.out.println(actualTitle2);
		String jobTitle = driver.findElement(By.className("jobs-page-header-h1")).getText();
		// System.out.println(jobTitle);
		String count = driver.findElement(By.xpath("//span[@id='posiCountId']")).getText();
		if (actualTitle2.contains(jobTitle) && actualTitle2.contains(count)) {
			System.out.println("page title contains " +keyword+" and count of results");
		} else {
			System.out.println("page title does not contain search keyword and count of results");
		}
		if (driver.getPageSource().contains(count)) {
			System.out.println("count results(" + count + ") displayed on the results page");
		}
		keywords.set(i, keyword+"-"+count);
		Thread.sleep(2000);
		driver.get("https:\\dice.com");

		// System.out.println(count);
		// driver.close();
		}

		System.out.println("test completed at " + LocalDateTime.now());
		Thread.sleep(3000);
		driver.close();
		for(String each:keywords) {
		System.out.println(each);
		}

	}
}
