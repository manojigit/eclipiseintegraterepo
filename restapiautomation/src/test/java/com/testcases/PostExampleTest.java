package com.testcases;

import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostExampleTest {



	@Test
	public void testCase() throws JsonParseException, JsonMappingException, IOException {


		RequestSpecification requestSpecification =	RestAssured.given();

		requestSpecification.contentType("application/json");

		//For Post we should pass request payload
	//	String jsonString = "{\"name\" : \"admintest\",\"job\" : \"QA\"}";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Manoj");
		jsonObject.put("job", "QALEAD");

		requestSpecification.body(jsonObject.toString());


		Response response =	requestSpecification.post("https://reqres.in/api/users");


		System.out.println(response.getStatusCode());

		System.out.println(response.asPrettyString());
		
		System.out.println(response.jsonPath().getString("id"));

		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.jsonPath().getString("id"));

		
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		
		
		 UserData data = new UserData();
		 
		 
		
	UserData expected =	mapper.readValue(response.body().asString(), UserData.class);
	
	System.out.println("test ::: "+expected);
	
	UserData actual = mapper.readValue(Paths.get("src\\test\\resources\\userresponse.json").toFile(), UserData.class);
	
	System.out.println("Actual ::" +actual);
		
	expected.setId(actual.getId());
	expected.setCreatedAt(actual.getCreatedAt());
	Assert.assertEquals(actual,expected);

	}
}
