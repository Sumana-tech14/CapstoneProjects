package com.TestFiles;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.sql.Date;

import com.PojoFiles.AddContact;
import com.PojoFiles.AddUser;
import com.PojoFiles.LoginUser;

public class ContactListApplication {
	
	String token1;
	String token2;
	String ID;
	
  @Test(priority=1)
  public void testAddNewUser() {
	  
	  // create Payload
	  AddUser userData = new AddUser();
	  
	  userData.setFirstName("Test");
	  userData.setLastName("User");
	  long time = System.currentTimeMillis(); 
	  userData.setEmail("test"+time+"@fake.com");
	  userData.setPassword("myPassword");
	  
	 Response res = given()
			 		.header("content-Type","application/json")
			 		.body(userData)
			 		
			 		.when().post("https://thinking-tester-contact-list.herokuapp.com/users");
	 
				 // validate  status code
				 Assert.assertEquals(res.getStatusCode(), 201,"Status code not matched!!!");
				 System.out.println("Status code matched........"+res.getStatusCode());
				 
				 // validate Status Message
				 Assert.assertTrue(res.getStatusLine().contains("Created"), "Status message not matched!!!");
				 System.out.println("Status message is : "+res.getStatusLine());
				 
				 // store generated token
				 token1 = res.jsonPath().getString("token");
				 System.out.println("Generated token is : "+ token1);
			 		
			 	res.then().log().body();
			 	System.out.println("-----------------------------------------------------------");
	   
  }
  
  @Test (dependsOnMethods="testAddNewUser",priority =2)
  public void testGetUserProfile() {
	  
	  Response res = given()
			  			.header("Authorization","Bearer "+ token1)
			  			
			  			.when().get("https://thinking-tester-contact-list.herokuapp.com/users/me");
	  
	  					//validate status code
	  					Assert.assertEquals(res.getStatusCode(),200 , "Status code not matched!!!");
	  					System.out.println("Status code matched...."+res.getStatusCode());
	  					
	  					// validate Status message
	  					Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!!");
	  					System.out.println("Status message matched...."+res.getStatusLine());
	  					
	  					res.then().log().body();
	  				 	System.out.println("-----------------------------------------------------------");

			  			
  }
  
  @Test(dependsOnMethods="testAddNewUser",priority=3)
  public void testUpdateUser() {
	  
	  // create payload
	  AddUser userData = new AddUser();
	  userData.setFirstName("Updated");
	  userData.setLastName("Username");
	  long time = System.currentTimeMillis();
	  userData.setEmail("test2"+time+"@fake.com");
	  userData.setPassword("myNewPassword");
	  Response res = given()
			  			.header("Authorization","Bearer "+ token1)
			  			
			  			.when().patch("https://thinking-tester-contact-list.herokuapp.com/users/me");
			  			
			  			res.then().log().body();
			  			
			  			// validate status code
			  			Assert.assertEquals(res.getStatusCode(),200 , "Status code not matched!!!");
			  			System.out.println("Status code matched....."+res.getStatusCode());
			  			
			  			// validate Status message
			  			Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!");
			  			System.out.println("Status message matched....."+res.getStatusLine());
					 	System.out.println("-----------------------------------------------------------");

			  
  }
  
  @Test(priority=4)
  public void testLoginUser() {
	  
	  // create Payload
	  LoginUser loginData = new LoginUser();
	  loginData.setEmail("test2732026@gmail.com");
	  loginData.setPassword("test123");
	  
	  Response res = given()
			  			.header("content-Type","application/json")
			  			.body(loginData)
			  			
			  			.when().post("https://thinking-tester-contact-list.herokuapp.com/users/login");
			  			
			  			res.then().log().body();
			  			
			  			// validate status code
			  			Assert.assertEquals(res.getStatusCode(), 200 , "Status code not matched!!!");
			  			System.out.println("Status code matched......"+res.getStatusCode());
			  			
			  			// validate status message
			  			Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!");
			  			System.out.println("Status message matched....."+res.getStatusLine());
			  			
			  			// Store generated Token
			  			token2 = res.jsonPath().getString("token");
			  			System.out.println("Generated token value is : "+token2);
			  			
			  			System.out.println("-----------------------------------------------------------");		
	  
  }
  
  @Test(dependsOnMethods="testLoginUser", priority=5)
  public void testAddContact() {
	  
	// create payload
	  AddContact addData= new AddContact();
		
		addData.setFirstName("John");
		addData.setLastName("Doe");
		addData.setBirthdate("1970-01-01");
		addData.setEmail("jdoe@fake.com");	
		addData.setPhone("8005555555");
		addData.setStreet1("1 Main St.");
		addData.setStreet2("Apartment A");
		addData.setCity("Anytown");
		addData.setStateProvince("KS");
		addData.setPostalCode("12345");
		addData.setCountry("USA");
		
	  
	  Response res = given()
			  		.header("Authorization","Bearer "+ token2)
			  		.contentType(ContentType.JSON)
			  		.body(addData)
			  		
			  		.when().post("https://thinking-tester-contact-list.herokuapp.com/contacts");
	  
	  				res.then().log().body();
	  				
	  				// validate status code
	  				Assert.assertEquals(res.getStatusCode(), 201 , "Status code not matched!!!");
		  			System.out.println("Status code matched......"+res.getStatusCode());
		  			
		  			// validate status message
		  			Assert.assertTrue(res.getStatusLine().contains("Created"), "Status message not matched!!!");
		  			System.out.println("Status message matched....."+res.getStatusLine());
		  			
		  			// store generated ID
		  			ID = res.jsonPath().getString("_id");
		  			System.out.println("Generated ID is : "+ID);
		  			System.out.println("-----------------------------------------------------------");	
  }
  
  @Test(dependsOnMethods="testLoginUser", priority=6)
  public void testGetContactList() {
	  
	  Response res = given()
			  		.header("Authorization", "Bearer "+token2)
			  		
			  		.when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
	  
	  				res.then().log().body();
	  				
	  				// validate status code
	  				Assert.assertEquals(res.getStatusCode(), 200 , "Status code not matched!!!");
		  			System.out.println("Status code matched......"+res.getStatusCode());
		  			// validate status message
		  			Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!");
		  			System.out.println("Status message matched....."+res.getStatusLine());
  }
  
  @Test(dependsOnMethods="testAddContact", priority=7)
  public void testGetContact() {
	  
	  Response res = given()
			  		.header("Authorization", "Bearer "+token2)
			  		
			  		.when().get("https://thinking-tester-contact-list.herokuapp.com/contacts/"+ID);
	  
					  res.then().log().body();
						
						// validate status code
						Assert.assertEquals(res.getStatusCode(), 200 , "Status code not matched!!!");
						System.out.println("Status code matched......"+res.getStatusCode());
						// validate status message
						Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!");
						System.out.println("Status message matched....."+res.getStatusLine());
		

	  
	  
  }
  
  @Test(dependsOnMethods="testAddContact", priority = 8)
  public void testUpdateContact() {
	// create payload
		 AddContact addData= new AddContact();
		
		addData.setFirstName("Amy");
		addData.setLastName("Miller");
		addData.setBirthdate("1992-02-02");
		addData.setEmail("amiller@fake.com");	
		addData.setPhone("8005554242");
		addData.setStreet1("13 School St.");
		addData.setStreet2("Apt. 5");
		addData.setCity("Washington");
		addData.setStateProvince("QC");
		addData.setPostalCode("A1A1A1");
		addData.setCountry("Canada");
		
	  
	  Response res = given()
			  			.header("Authorization", "Bearer "+token2)
			  			.contentType(ContentType.JSON)
			  			.body(addData)
			  					  			
			  			.when().put("https://thinking-tester-contact-list.herokuapp.com/contacts/"+ID);
	  
						  res.then().log().body();
							
							// validate status code
							Assert.assertEquals(res.getStatusCode(), 200 , "Status code not matched!!!");
							System.out.println("Status code matched......"+res.getStatusCode());
							
							// validate status message
							Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!");
							System.out.println("Status message matched....."+res.getStatusLine());
							
							// validate Email
							String email = res.jsonPath().getString("email");
							Assert.assertEquals(email,"amiller@fake.com" ,"Update Failed....Email not matched!!! " );
							System.out.println("Update successful....Email is : "+email);


  }
  
  @Test(dependsOnMethods="testUpdateContact", priority=9)
  public void testPartialUpdateContact() {
	  
	  // create payload
	 // AddContact addData = new AddContact();
	//  addData.setFirstName("Anna");
	  
	  Response res = given()
			  .header("Authorization", "Bearer "+token2)
	  			.contentType(ContentType.JSON)
	  			//.body(addData)
	  			.body("{\n"
	  					+ "    \"firstName\": \"Anna\"\n"
	  					+ "}")
	  					
	  			
	  			.when().patch("https://thinking-tester-contact-list.herokuapp.com/contacts/"+ID);
	  

				  res.then().log().body();
					
					// validate status code
					Assert.assertEquals(res.getStatusCode(), 200 , "Status code not matched!!!");
					System.out.println("Status code matched......"+res.getStatusCode());
					
					// validate status message
					Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!");
					System.out.println("Status message matched....."+res.getStatusLine());
					
					// validate first name : Anna
					String fName= res.jsonPath().getString("firstName");
					Assert.assertEquals(fName, "Anna" , "Update Failed....First Name not matched!!!");
					System.out.println("Partial Update successful....First Name is : "+fName);

	  			
			  		
  }
  
  @Test(priority=10)
  public void testLogoutUser() {
	  
	  Response res = given()
			  			.header("Authorization", "Bearer "+token2)
			  			
			  			.when().post("https://thinking-tester-contact-list.herokuapp.com/users/logout");
	  
	  					res.then().log().body();
	  					
	  					// Validate status code
	  					Assert.assertEquals(res.getStatusCode(),200,"Status code not matched!!!!");
	  					System.out.println("Status code is : "+res.getStatusCode());
	  					
	  					// Validate status message
	  					Assert.assertTrue(res.getStatusLine().contains("OK"), "Status message not matched!!!");
	  					System.out.println("'Status message matched....."+res.getStatusLine());
	  					
  }
}


// create payload
	/*  AddContact addData= new AddContact();
	
	addData.setFirstName("John");
	addData.setLastName("Doe");
	addData.setBirthdate("1970-01-01");
	addData.setEmail("jdoe@fake.com");	
	addData.setPhone("8005555555");
	addData.setStreet1("1 Main St.");
	addData.setStreet2("Apartment A");
	addData.setCity("Anytown");
	addData.setStateProvince("KS");
	addData.setPostalCode("12345");
	addData.setCountry("USA");
	*/
