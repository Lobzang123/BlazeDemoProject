package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetAPITest {

	@BeforeTest
	public void SetUP() {
		//RestAssured.baseURI = "https://samples.openweathermap.org/data/2.5/history/city?q=London,UK&appid=b1b1%205%20e88fa797225412429c1c50c122a1";
	}
	@Test(priority = 0, enabled = true)
	public void verifyRecordSuccessfulRead() {

		RestAssured.given()
		.when()
		.get("https://samples.openweathermap.org/data/2.5/history/city?q=London,UK&appid=b1b1%205%20e88fa797225412429c1c50c122a1")
		.then()
		.statusCode(200)
		.log()
		.all()

		;
	}

	@Test(priority = 1, enabled = true)
	public void validateResponse() {
		RestAssured.given()
				.when()
					.get("https://samples.openweathermap.org/data/2.5/history/city?q=London,UK&appid=b1b1%205%20e88fa797225412429c1c50c122a1")
				.then()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK")
					.contentType("application/json; charset=utf-8")
				.log()
				.all()
				;
		Assert.assertNotNull("X-Request-Id");
		//Here we can validate response through parsing the response

	}


}
