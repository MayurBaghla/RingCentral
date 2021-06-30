package com.test;

import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;

import org.hamcrest.*;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class GetUser{

//validate if Get users api is working
//Given- all input details
//when- submit the api
//then- validate the response
	// Get API
	@Test
	public void GetUsers() {
		RestAssured.baseURI = "http://localhost:8080/api/";

		String GetUser = given().log().all().when().get("users").then().assertThat().statusCode(200).extract()
				.response().asString();

		System.out.println(GetUser);

		JsonPath js = new JsonPath(payload.DefaultGetResponse()); // for parsing Json

		// get number of links
		int LinksCount = js.getInt("links.size()");
		System.out.println("The total number of links are " + LinksCount);
		// verify number
		Assert.assertEquals(LinksCount, 2);

		// get number of Content
		int ContentCount = js.getInt("content.size()");
		System.out.println("The total number of content are " + ContentCount);
		// verify number
		Assert.assertEquals(ContentCount, 20);

		// verify first link rel
		String LinkRel = js.get("links[0].rel");
		System.out.println("First link's rel is " + LinkRel);
		Assert.assertEquals(LinkRel, "self");

		// verify first name of id =5 as Mary
		String FirstName = js.get("content[4].firstName");
		System.out.println("First name is " + FirstName);
		Assert.assertEquals(FirstName, "Mary");

		// print all firstnames and id
		for (int i = 0; i < ContentCount; i++) {
			int id = js.get("content[" + i + "].id");
			String firstNames = js.get("content[" + i + "].firstName");
			System.out.println(id);
			System.out.println(firstNames);
		}

		// verify if id=6 then first name is Alex

		System.out.println("verify if first name is Alex then last name is Portman");
		for (int i = 0; i < ContentCount; i++) {

			String firstNames = js.get("content[" + i + "].firstName");
			if (firstNames.equalsIgnoreCase("Alex")) {
				String LastName = js.get("content[" + i + "].lastName");
				System.out.println(LastName);
				break;

			}
		}
		// get Users by id API
		System.out.println("get Users by id API");
		String GetUserbyId = given().log().all().queryParam("id", 5).when().get("users").then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println(GetUserbyId);
		

			}
	
	
	
}
