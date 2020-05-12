package com.himanshu.SeleniumPractice;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public WebDriver driver;
	@BeforeTest

	public void init() throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver","src/test/java/chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://www.indmoney.com/");
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}	
		@Test
		public void sort() throws InterruptedException
		{
		driver.findElement(By.xpath("(//a[@href='/investments'])[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='/stocks']")).click();//.sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//ul[@role='menu'])[3]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h1[contains(.,'Top Gainers')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='Change']")).click();
		driver.findElement(By.xpath("//li[contains(.,'Name')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Z-A']")).click();
		driver.findElement(By.xpath("//li[contains(.,'A-Z')]")).click();
		Thread.sleep(3000);
		ArrayList<String> obtainedList = new ArrayList<>();
		while(true)
		{
		Thread.sleep(3000);	
		List <WebElement> Stocks = driver.findElements(By.xpath("//h1"));
		for(WebElement i: Stocks)
		{
		
			String name=i.getText();
			System.out.println(name);
			obtainedList.add(name.toLowerCase());
			
		}
		try{
		WebElement e =driver.findElement(By.xpath("//li[@title='Next Page' and @aria-disabled='false']"));
		e.click();
		}
		catch(Exception e){		
			break;
		
		}
		}
		ArrayList<String> expectedList = new ArrayList<>();
		for(String a: obtainedList)
		{
			expectedList.add(a.toLowerCase());
		}
		Collections.sort(expectedList);
		System.out.println(expectedList);
		assertEquals(obtainedList, expectedList);
		}
		
		//Test 2
		@Test
		public void sortPrice() throws InterruptedException
		{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='1']")).click();
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_UP).build().perform();
		action.sendKeys(Keys.PAGE_UP).build().perform();
		action.sendKeys(Keys.PAGE_UP).build().perform();
		action.sendKeys(Keys.PAGE_UP).build().perform();
		driver.findElement(By.xpath("//div[@title='Name']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//li[contains(.,'Price')]")).click();
		Thread.sleep(3000);
		ArrayList<Double> obtainedList1 = new ArrayList<>();
		while(true)
		{
		Thread.sleep(3000);	
		List <WebElement> StocksP = driver.findElements(By.cssSelector("div:nth-child(2) > span"));
		for(WebElement i: StocksP)
		{
		
			String price=i.getText();
			System.out.println(price);
			obtainedList1.add(Double.valueOf(price.replaceAll("₹","").replaceAll(",","")));
			
		}
		try{
		WebElement e =driver.findElement(By.xpath("//li[@title='Next Page' and @aria-disabled='false']"));
		e.click();
		}
		catch(Exception e){		
			break;
		
		}
		}
		ArrayList<Double> expectedList1 = new ArrayList<>();
		for(Double a: obtainedList1)
		{
			expectedList1.add(a);
		}
		Collections.sort(expectedList1);
		System.out.println(expectedList1);
		assertEquals(obtainedList1, expectedList1);

		}
		
		@AfterTest
		public void close() {
		driver.quit();
		}

}
