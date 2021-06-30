package com.test;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddUser {
	@Test
	public void AddUsers() {
		RestAssured.baseURI = "http://localhost:8080/api/";
		System.out.println("Add User API");
		String AddUser = given().log().all().header("Content-Type","application/json")
				.body(payload.AddUser()).when().post("users").then().assertThat()
				.statusCode(201).extract()
				.response().asString();

		System.out.println(AddUser);
		
		JsonPath js = new JsonPath(AddUser);
		String Name =js.getString("firstName");
		
		
		Assert.assertEquals(Name, "Mayur");
		}
	
}
