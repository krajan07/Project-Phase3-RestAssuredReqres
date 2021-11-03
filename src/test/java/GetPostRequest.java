import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetPostRequest {

	final Logger LR = LogManager.getLogger(GetPostRequest.class.getSimpleName());

	//Setting the Base URI Path for the test
		@Before
		public void beforeTest() {
			// Set Base path
			RestAssured.baseURI = "https://reqres.in/";
			PropertyConfigurator.configure("conf/log4j.properties");
		}
		
		//Listing the user and checking for the Status Code
		@Test
		public void getUsers() {
			//Create a Request
			RequestSpecification request = RestAssured.given();
			System.out.println("\n");
			LR.info("Inside getUsers test case");
			//Setup and Execute request
			Response response= request.get("api/users?page=2");
			JsonPath jsonData = response.jsonPath();
			LR.info("Total Data : " + jsonData.getInt("total"));
			LR.info("Per Page Data : " + jsonData.getInt("per_page"));
			LR.debug("id's in the page = " + jsonData.getString("data.id"));
			int statusCode = response.getStatusCode();
			assertEquals(200,statusCode);
			LR.info("getUsers test case completed successfully!!!\n");
			System.out.println("\n");
		}
		
		//Creating user using the Post Request
		@Test
		public void postUser() {
			//Create a Request
			RequestSpecification request = RestAssured.given();
			LR.info("Inside postUser test case");
			//Setup and Execute request
			String user = "{\"name\": \"krishna\",\"job\": \"manager\"}";
			request.body(user).post("api/users");
			LR.info("postUser test case completed successfully!!! \n");
		}

}
