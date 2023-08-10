package com.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SecondPostExampleTest {




	@Test
	public void testcase() {


		RequestSpecification requestSpecification =	RestAssured.given();

		requestSpecification.contentType("application/json");

		JSONObject jsonObject = new JSONObject();
		
		RandomStringUtils randomStringUtils = new RandomStringUtils();
		randomStringUtils.randomAlphanumeric(6);
		System.out.println(randomStringUtils.randomNumeric(6));
		
		jsonObject.put("id",Integer.parseInt(randomStringUtils.randomNumeric(6)));
		jsonObject.put("name","Indian Air Lines");
		jsonObject.put("country ","India");
		jsonObject.put("logo","https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/sri_lanka.png");
		jsonObject.put("slogan","From India");
		jsonObject.put("head_quaters","Hyderabad, India");
		jsonObject.put("website","www.qualitythought.com");
		jsonObject.put("established","2000");
		
		requestSpecification.body(jsonObject.toString());

		Response response =	requestSpecification.post("https://api.instantwebtools.net/v1/airlines");

		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
