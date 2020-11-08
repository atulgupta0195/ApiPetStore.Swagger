package com.petstore.apitest;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;

import Config.PropertiesFile;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

public class test {

	static Response userResponse;

	static Response postuserResponse;
	static ResponseBodyData putUserReq;
	static ResponseBodyData logoutUserResponse;
	static Response loginUserResponse ;
	public static void main(String[] args) {
		  postUserCreateWithArray();
		//postCreateWithList();
		//getUserResponse();
		//validationResponse();
		// putUser();
		   loginUser();
		  logoutUser();
	}

public static void getUserResponse() {
	RestAssured.baseURI = "http://petstore.swagger.io/v2/user";
		userResponse = RestAssured.given() .header("Accept", "application/json").when().get("/GAGupta");
		System.out.println(" user Response = ===== " + userResponse.asString());

	}
public static void postUserCreateWithArray() {
	RestAssured.baseURI = "https://petstore.swagger.io/v2/user";
	Faker faker = new Faker();
	String  id = faker.number().digit();
	String  phonenumber = faker.number().digits(9) ;
	String name = faker.esports().game();
	String subname = faker.name().lastName();
	String username = name + subname +  id;
	String password = subname +  id;
	String gmailid =  username + "@gmail.com";
	 
	String postReqBody =  "[{\"id\":0,\"username\":\""+username+ "\",\"firstName\":\""+name+"\",\"lastName\":\""+subname+"\",\"email\":\""+gmailid+"\",\"password\":\""+password+"\",\"phone\":\""+phonenumber+"\",\"userStatus\":0}]";
	System.out.println("postReqBody = " + postReqBody);

	postuserResponse = RestAssured.given().body(postReqBody).when().contentType("application/json").post("/createWithArray");
	System.out.println("Created postUserCreateWithArray user Response = ===== " + postuserResponse.asString());


}
public static void postCreateWithList() {
	RestAssured.baseURI = "https://petstore.swagger.io/v2/user";
	Faker faker = new Faker();
	String  id = faker.number().digit();
	String  phonenumber = faker.number().digits(9) ;
	String name = faker.esports().game();
	String subname = faker.name().lastName();
	String username = name + subname +  id;
	String password = subname +  id;
	String gmailid =  username + "@gmail.com";
	 
	String postReqBody =  "[{\"id\":0,\"username\":\""+username+ "\",\"firstName\":\""+name+"\",\"lastName\":\""+subname+"\",\"email\":\""+gmailid+"\",\"password\":\""+password+"\",\"phone\":\""+phonenumber+"\",\"userStatus\":0}]";
	System.out.println("postReqBody = " + postReqBody);

	postuserResponse = RestAssured.given().body(postReqBody).when().contentType("application/json").post("/createWithList");
	System.out.println("Created postCreateWithList user Response = ===== " + postuserResponse.asString());

	
}	
public static void putUser() {
	RestAssured.baseURI = "https://petstore.swagger.io/v2/user";
	 
	String  id = "347889";
	String  phonenumber =  "1234789" ;
	String name = "TesterAPi";
	String subname = "newtech";
	String username = "testernewtech ";
	String password = subname +  id;
	String gmailid =  username + "@gmail.com";
	 
	String putUserReqBody =  "[{\"id\":0,\"username\":\""+username+ "\",\"firstName\":\""+name+"\",\"lastName\":\""+subname+"\",\"email\":\""+gmailid+"\",\"password\":\""+password+"\",\"phone\":\""+phonenumber+"\",\"userStatus\":0}]";
	System.out.println("postReqBody = " + putUserReqBody);

	  putUserReq = RestAssured.given().body(putUserReqBody).when().contentType("application/json").put("/GAGupta");
	System.out.println("Created putUser user Response = ===== " + putUserReq.asString());

	
}

public static void deleteUser() {
	Response eventResponse = RestAssured.given().when()
			.contentType("application/json").delete("/atulG" );

	System.out.println("Created event Response = ===== " + eventResponse.asString());
	eventResponse.then().assertThat().statusCode(200);
}

 
public static void loginUser() {
	RestAssured.baseURI = "https://petstore.swagger.io/v2/user";
	String username = "CS:GOBernier0";
	String password = "Bernier0";
	  loginUserResponse = RestAssured.given().when()
			.contentType("application/json").get("/login?username="+username+"&password="+username+"");

	System.out.println("loginUserResponse = ===== " + loginUserResponse.asString());
	loginUserResponse.then().assertThat().statusCode(200);
} 


public static void logoutUser() {
	RestAssured.baseURI = "https://petstore.swagger.io/v2/user";
	 
	  logoutUserResponse = RestAssured.given().when()
			.contentType("application/json").get("/logout");

	System.out.println("logoutUserResponse = ===== " + logoutUserResponse.asString());
	((Validatable<ValidatableResponse, Response>) logoutUserResponse).then().assertThat().statusCode(200);
}

public static  void validationResponse() {
		userResponse.then().assertThat().statusCode(200);
		 
		Headers allheaders = userResponse.headers();
		System.out.println("==========================*** Response.Headers ***===============================\n");

		for (Header header : allheaders) {
		 
			if (header.getName().contentEquals("Content-Type")) {
				System.out.println(
						"____________________________________________________________________________________");
				assertEquals("application/json", header.getValue());
				System.out.println("===============Validate header " + "ContentTypeName" + "===================\n");
				System.out.println("Header Name :" + header.getName() + "--------- Header Value :" + header.getValue());
 			}else {
				System.out.println("____________________________________________________________________________________");
				System.out.println("===============Validate header " + "ContentTypeName" + "===================\n");
				System.out.println("Header Name :" + header.getName() + "--------- Header Value :" + header.getValue());
 
			}
 
 		}

	}


}
