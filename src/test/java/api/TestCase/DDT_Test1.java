package api.TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Endpoints.UserEndpoints;
import api.Payloads.User;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class DDT_Test1 {

	@Test(priority = 1,dataProvider = "data",dataProviderClass = DataProviders.class)
	public void testpostuser(String UserID, String UserName, String FirstName, String LastName,String Email, String Password, String Phone) {
		
		User Userpayload = new User();
		
		Userpayload.setId(Integer.parseInt(UserID));
		Userpayload.setUsername(UserName);
		Userpayload.setFirstName(FirstName);
		Userpayload.setLastName(LastName);
		Userpayload.setEmail(Email);
		Userpayload.setPassword(Password);
		Userpayload.setPhone(Phone);
		
		Response response = UserEndpoints.createUser(Userpayload);
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
