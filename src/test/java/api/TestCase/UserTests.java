package api.TestCase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndpoints;
import api.Payloads.User;
import io.restassured.response.Response;


public class UserTests {

	Faker faker;
	User UserPayload;
	
//	@BeforeClass
	public void setupdata()
	{
		 faker = new Faker();
		 UserPayload = new User();
		 
		 UserPayload.setId(faker.idNumber().hashCode());
		 UserPayload.setUsername(faker.name().username());
		 UserPayload.setFirstName(faker.name().firstName());
		 UserPayload.setLastName(faker.name().lastName());
		 UserPayload.setEmail(faker.internet().safeEmailAddress());
		 UserPayload.setPassword(faker.internet().password(5,10));
		 UserPayload.setPhone(faker.phoneNumber().cellPhone());
		 
	}
	
//	@Test(priority = 1)
	public void testpostuser() {
		
		Response response = UserEndpoints.createUser(UserPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
//	@Test(priority = 2)
	public void testGetUserByName() {
		
		Response response = UserEndpoints.readUser(this.UserPayload.getUsername());
		response.then().log().all();
		response.statusCode();
		Assert.assertEquals(response.getStatusCode(),200);
		//405 becoz server permission to http request is denied
	}
	
//	@Test(priority = 3)
	public void updateusername() {
		
		//updated data
		UserPayload.setFirstName(faker.name().firstName());
		 UserPayload.setLastName(faker.name().lastName());
		 UserPayload.setEmail(faker.internet().safeEmailAddress());
		
         Response response = UserEndpoints.updateUser(this.UserPayload.getUsername(),UserPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		//cross check-//get -readuser(string)
		Response responseafterupdate = UserEndpoints.readUser(this.UserPayload.getUsername());
		responseafterupdate .then().log().all();
		Assert.assertEquals(responseafterupdate.getStatusCode(),200);
		//405 becoz server permission to http request is denied
	}
	
//	@Test(priority = 4)
       public void testdeleteUserByName() {
		
		Response response = UserEndpoints.deleteUser(this.UserPayload.getUsername());
		response.then().log().all();
		//response.statusCode();
		Assert.assertEquals(response.getStatusCode(),200);
		//405 becoz server permission to http request is denied
	
}
}
