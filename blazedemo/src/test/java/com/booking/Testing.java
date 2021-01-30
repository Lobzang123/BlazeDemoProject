package com.booking;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.Base;
import com.utils.ElementUtility;

public class Testing extends Base{

	SoftAssert asserts = new SoftAssert();//Verify and soft assert are same 
	@BeforeTest
	public void setUP() {
		initialization();
	}

/*
 * Select flights functionality
 */
	@Test(priority = 0)
	public void selectFlights() {
		ElementUtility.verifyTitle("BlazeDemo");
		//Source
		WebElement elementSource = driver.findElement(By.name("fromPort"));
		ElementUtility.selectDropDown(elementSource);
		//Destination
		WebElement elementDestination = driver.findElement(By.name("toPort"));
		ElementUtility.selectDropDown(elementDestination);
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
	}
/*
 * Rservation functionality
 */
	@Test(priority = 1,enabled = false)
	public void reservationTest() {
		ElementUtility.verifyTitle("BlazeDemo - reserve");
		driver.findElement(By.xpath("//input[@value=\"Choose This Flight\"]")).click();
	}

	@Test(priority = 2,enabled = false)
	public void bookingFormTest() {
		ElementUtility.verifyTitle("BlazeDemo Purchase");
		driver.findElement(By.id("inputName")).sendKeys("LObzang Punchok");
		driver.findElement(By.id("address")).sendKeys("B 322, Street5");
		driver.findElement(By.id("city")).sendKeys("Bangalore");
		driver.findElement(By.id("state")).sendKeys("Karnataka");
		driver.findElement(By.id("zipCode")).sendKeys("560049");
		driver.findElements(By.xpath("//input[@value=\"Choose This Flight\"]"));//Select first flight in list
		
		WebElement elementDestination = driver.findElement(By.name("cardType"));
		ElementUtility.selectDropDown(elementDestination);
		driver.findElement(By.id("creditCardNumber")).sendKeys("1234 4567 1234 4321");
		driver.findElement(By.id("creditCardMonth")).sendKeys("11");
		driver.findElement(By.id("creditCardYear")).sendKeys("2028");
		driver.findElement(By.id("nameOnCard")).sendKeys("Lobzang Punchok");
		driver.findElement(By.xpath("//input[@value=\"Purchase Flight\"]")).submit();
	}
/*
 * Confirm booking once done successfully
 */
	@Test(priority = 3,enabled = false)
	public void confirmBooking() {
		ElementUtility.verifyTitle("BlazeDemo Confirmation");
		asserts.assertNotNull(driver.findElement(By.xpath("//tbody/tr/td[text()='Id']")));
		asserts.assertNotNull(driver.findElement(By.xpath("//tbody/tr[2]/td[text()='Status']")));
		asserts.assertAll();
	}
	@AfterTest
	public void tearDown() throws InterruptedException {
		//Thread.sleep(3000);
		driver.quit();
	}
}
