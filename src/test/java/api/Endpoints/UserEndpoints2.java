package api.Endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {

	//adding a common & static method-> resourcebundle(java.util.pack)
	
	static ResourceBundle getURL()
	 
	 {
		 ResourceBundle routes = ResourceBundle.getBundle("routes");
		
		 return routes;
		
		
	}
	
	//calling the common method inside crud implementation
	
	public static Response createUser(User payload)
	{
		
	String post_url = 	getURL().getString("post_URL");
	
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(post_url);
		
		return response;
	}
	
	public static Response readUser(String username)
	{
		
		String get_url = 	getURL().getString("get_URL");
		Response response = given()
				.accept(ContentType.JSON)
		.pathParam("username", username)
		
		.when()
		.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String username,User payload)
	{
		
		String put_url = 	getURL().getString("update_URL");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
		        .pathParam("username", username)
		        .body(payload)
		
		.when()
		.put(put_url);
		
		return response;
	}
	
	public static Response deleteUser(String username)
	{
		String del_url = 	getURL().getString("delete_URL");
		
		Response response = given()
		.pathParam("username", username)
		
		
		.when()
		.delete(del_url);
		
		return response;
	}
}
