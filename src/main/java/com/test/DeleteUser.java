
  package com.test;
  
  import static io.restassured.RestAssured.given;
  
  import org.testng.Assert; import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
  
  public class DeleteUser {
  
  @Test 
  public  void Delete() { 
	  System.out.println("Delete User API"); 
	  RestAssured.baseURI = "http://localhost:8080/api/";
		 //Verify the status code
  given().log().all().header("Content-Type","application/json")
  .when().delete("users/11").then().assertThat().statusCode(204);
  
  
  
  
  }
  } 
 