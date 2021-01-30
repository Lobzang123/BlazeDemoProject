package com.test;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utils.ExtentReportListner;
import com.utils.ValidatorUtility;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(ExtentReportListner.class)
public class BaseTest extends ExtentReportListner{
	
	SoftAssert asserts = new SoftAssert();
	@BeforeClass
	public void baseTest() {
		RestAssured.baseURI = "https://api.spacexdata.com/v4/launches/latest";
	}

	@Test
	public void validateGetResponseFieldsforNotNullorBlank() {
		Response response = RestAssured
		.given()
		.when()
				.get()
		
		;
		asserts.assertNotNull(response.getBody());
		ValidatorUtility.responseKeyValidationFromJsonObject(response, "launchpad");
		ValidatorUtility.responseKeyValidationFromJsonObject(response, "payloads");
		ValidatorUtility.responseKeyValidationFromJsonObject(response, "rocket");
		ValidatorUtility.responseKeyValidationFromJsonObject(response, "crew");
		ValidatorUtility.responseKeyValidationFromJsonObject(response, "webcast");
		ValidatorUtility.responseKeyValidationFromJsonObject(response, "ships");
		ValidatorUtility.responseTimeValidation(response);
	}

	
	@Test
	public void validateGetResponseFieldsAreMatching() {
		Response response = RestAssured
		.given()
		.when()
				.get()
		;
		ValidatorUtility.responseCodeValiddation(response, 200);
		asserts.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		asserts.assertEquals(response.statusCode(), 200);
		asserts.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");

	    response.then()
	    .assertThat()
	    .body("flight_number", equalTo(115))
	    .body("name", equalTo("Transporter-1"))
	    .body("date_precision", equalTo("hour"))
	    .body("cores[0].landing_type", equalTo("ASDS"))
	    
	    		;
	}
	
	@AfterClass
	public void tearDown() {
		
	}
}
