package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * @author Lobzang Punchok
 * @dated 30th Jan 2020 
 * Base class: Initial Setup functionality
 * This class expects: Pre-Condition: driver in place of project folder under drivers folder
 * this scenario uses chromeDriver as an example here
 */
public class Base {
	public static WebDriver driver;
	static String user_dir = System.getProperty("user.dir");
	public static void initialization() {
		System.setProperty("webdriver.chrome.driver", user_dir+"\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://blazedemo.com/");
		driver.manage().window().maximize();
	}

}
