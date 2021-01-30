package com.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.base.Base;

public class ElementUtility extends Base{

	static SoftAssert asserts = new SoftAssert();
	public static void selectDropDown(WebElement locator) {
		Select option = null;
		option = new Select(locator);
		option.selectByIndex(0);
	}
	

	public static void verifyTitle(String expTitle) {
		String actualTitle = driver.getTitle();
		asserts.assertEquals(actualTitle, expTitle, "Title mis-match...");
	}
}
