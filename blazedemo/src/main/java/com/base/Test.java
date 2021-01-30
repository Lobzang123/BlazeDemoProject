package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) {

		String user_dir = System.getProperty("user.dir");
		System.out.println(user_dir);
		initialization(user_dir);
		
		
	}
	public static void initialization(String user_dir) {
		System.setProperty("webdriver.chrome.driver", user_dir+"\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver.get("http://demo.automationtesting.in/Register.html");
	}	

}
