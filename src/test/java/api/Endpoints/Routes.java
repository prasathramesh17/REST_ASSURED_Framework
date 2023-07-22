package api.Endpoints;

public class Routes {

	//BASE URL
	public static String Base_URL = "https://petstore.swagger.io/v2";
	
	//MODULES-( user / pets/ store )
	//user Module
	
	public static String post_URL   = Base_URL+"/user"; 
	public static String get_URL    = Base_URL+"/user/{username}"; 
	public static String update_URL = Base_URL+"/user/{username}"; 
	public static String delete_URL = Base_URL+"/user/{username}"; 
	
}
