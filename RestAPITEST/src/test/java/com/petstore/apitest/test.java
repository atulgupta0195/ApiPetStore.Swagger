package com.petstore.apitest;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
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
	static Response loginUserResponse;
	static String baseURI = "https://petstore.swagger.io/v2/user";

	@Test(priority = 1)
	public void getUserResponse() {
		RestAssured.baseURI = baseURI;
		userResponse = RestAssured.given().header("Accept", "application/json").when().get("/GAGupta");

	}

	@Test(priority = 2)
	public void postUserCreateWithArray() {
		RestAssured.baseURI = baseURI;
		Faker faker = new Faker();
		String id = faker.number().digit();
		String phonenumber = faker.number().digits(9);
		String name = faker.esports().game();
		String subname = faker.name().lastName();
		String username = name + subname + id;
		String password = subname + id;
		String gmailid = username + "@gmail.com";

		String postReqBody = "[{\"id\":0,\"username\":\"" + username + "\",\"firstName\":\"" + name
				+ "\",\"lastName\":\"" + subname + "\",\"email\":\"" + gmailid + "\",\"password\":\"" + password
				+ "\",\"phone\":\"" + phonenumber + "\",\"userStatus\":0}]";

		postuserResponse = RestAssured.given().body(postReqBody).when().contentType("application/json")
				.post("/createWithArray");

	}

	@Test(priority = 3)
	public void postCreateWithList() {
		RestAssured.baseURI = baseURI;
		Faker faker = new Faker();
		String id = faker.number().digit();
		String phonenumber = faker.number().digits(9);
		String name = faker.esports().game();
		String subname = faker.name().lastName();
		String username = name + subname + id;
		String password = subname + id;
		String gmailid = username + "@gmail.com";

		String postReqBody = "[{\"id\":0,\"username\":\"" + username + "\",\"firstName\":\"" + name
				+ "\",\"lastName\":\"" + subname + "\",\"email\":\"" + gmailid + "\",\"password\":\"" + password
				+ "\",\"phone\":\"" + phonenumber + "\",\"userStatus\":0}]";
		System.out.println("postReqBody = " + postReqBody);

		postuserResponse = RestAssured.given().body(postReqBody).when().contentType("application/json")
				.post("/createWithList");
		System.out.println("Created postCreateWithList user Response = ===== " + postuserResponse.asString());

	}

	@Test(priority = 4)
	public void putUser() {
		RestAssured.baseURI = baseURI;

		String id = "347889";
		String phonenumber = "1234789";
		String name = "TesterAPi";
		String subname = "newtech";
		String username = "testernewtech ";
		String password = subname + id;
		String gmailid = username + "@gmail.com";

		String putUserReqBody = "[{\"id\":0,\"username\":\"" + username + "\",\"firstName\":\"" + name
				+ "\",\"lastName\":\"" + subname + "\",\"email\":\"" + gmailid + "\",\"password\":\"" + password
				+ "\",\"phone\":\"" + phonenumber + "\",\"userStatus\":0}]";

		putUserReq = RestAssured.given().body(putUserReqBody).when().contentType("application/json").put("/GAGupta");

	}

	@Test(priority = 5)
	public void deleteUser() {
		Response eventResponse = RestAssured.given().when().contentType("application/json").delete("/atulG");

		eventResponse.then().assertThat().statusCode(200);
	}

	@Test(priority = 6)
	public void loginUser() {
		RestAssured.baseURI = baseURI;
		String username = "CS:GOBernier0";
		String password = "Bernier0";
		loginUserResponse = RestAssured.given().when().contentType("application/json")
				.get("/login?username=" + username + "&password=" + username + "");

		System.out.println("loginUserResponse = ===== " + loginUserResponse.asString());
		loginUserResponse.then().assertThat().statusCode(200);
	}

	@Test(priority = 10)
	public void logoutUser() {
		RestAssured.baseURI = baseURI;

		logoutUserResponse = RestAssured.given().when().contentType("application/json").get("/logout");

		((Validatable<ValidatableResponse, Response>) logoutUserResponse).then().assertThat().statusCode(200);
	}

	@AfterTest
	public void validationResponse() {
		userResponse.then().assertThat().statusCode(200);

		Headers allheaders = userResponse.headers();

		for (Header header : allheaders) {

			if (header.getName().contentEquals("Content-Type")) {

				assertEquals("application/json", header.getValue());

			}

		}

	}

}
