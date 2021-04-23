package New01.Rest_Script;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class rest_testcases {
	
 //Smoke_Testing(HTTP Status Code)_for all posts .
	@Test(priority = 1)
  public void validate_response_code_testcase1() {
  given().get("https://jsonplaceholder.typicode.com/posts").then()
  .assertThat().statusCode(200);  
  }
	//Smoke_Testing(HTTP Status Code)_for post with ID 1 .
	@Test(priority = 2)
	  public void validate_response_code_testcase2() {
	  given().get("https://jsonplaceholder.typicode.com/posts/1").then()
	  .assertThat().statusCode(200);  
	  }

	//Smoke_Testing(HTTP Status Code)_for post using option order
	@Test (priority = 3)
	  public void validate_response_code_testcase3() {
	  given().options("https://jsonplaceholder.typicode.com/posts").then()
	  .assertThat().statusCode(204);  
	  }
	
	//Invalid Source code with invalid url  .
	@Test(priority = 4)
	  public void invalidate_response_code_testcase() {
	  given().get("https://jsonplaceholder.typicode.com/postss/1").then()
	  .assertThat().statusCode(404);  
	  }
	
	//Validate response body for all Posts
	@Test (priority = 5)
	  public void validate_response_body_testcase() {
	  given().get("https://jsonplaceholder.typicode.com/posts").then()
	  .assertThat().body("[0].'title'", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")).and()
	  .assertThat().body("[10].'userId'", equalTo(2)).and()
	  .assertThat().body("[53].'body'", equalTo("totam corporis dignissimos\nvitae dolorem ut occaecati accusamus\nex velit deserunt\net exercitationem vero incidunt corrupti mollitia"));
	}
	//Validate response body for posts with id 1
	@Test (priority = 6)
	  public void validate_response_body_testcase2() {
	  given().get("https://jsonplaceholder.typicode.com/posts/1").then()
	  .assertThat().body("'title'", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")).and()
	  .assertThat().body("'userId'", equalTo(1)).and()
	  .assertThat().body("'body'", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
	}
	
	//Validate response body for posts with using option order
	@Test(priority = 7)
	  public void validate_response_code_testcase() {
		String response =RestAssured.options("https://jsonplaceholder.typicode.com/posts").andReturn().asString();
	  Assert.assertTrue(response.contains(""));  
	  }
	
	/*@Test
	 public void validate_response_body_testcase_for_url() {
		  given().get("https://jsonplaceholder.typicode.com/posts").then()
		  .assertThat().body("[0].'title'", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
	     }*/
	 
	
	//Insert / after Url
	@Test (priority = 5)
	  public void invalidate_response_body_testcase3() {
	  given().get("https://jsonplaceholder.typicode.com/posts/").then()
	  .assertThat().body("[46].'title'", equalTo("quibusdam cumque rem aut deserunt"));
	}
	  
	
	//Invalid URL using numbers
		@Test (priority = 8 )
		  public void invalidate_response_body_testcase() {
		String response =RestAssured.get("https://jsonplaceholder.typicode.com/posts/1000").andReturn().asString();
		Assert.assertTrue(response.contains("{}"));  
		}
		//Invalid URL using letters
		@Test (priority = 9 )
		  public void invalidate_response_body_testcase1() {
		String response =RestAssured.get("https://jsonplaceholder.typicode.com/posts/a").andReturn().asString();
		Assert.assertTrue(response.contains("{}"));  
		}
		//Post New Items
        @Test (priority = 10 )
		  public void Post_new_Item() {
			
			JSONObject request = new JSONObject();
			request.put("userId", "11");
			request.put("id", "101");
			request.put("title", "Test_title");
			request.put("userId", "Test_title");
			given().contentType(ContentType.JSON).
		     accept(ContentType.JSON).
		     body(request.toJSONString())
		     .when().post("https://jsonplaceholder.typicode.com/posts")
		     .then().statusCode(201);
	    }  		
        //Assert if adding Existed Id,should put last Id + 1
        @Test (priority = 11 )
		  public void Post_new_Item_with_Existed_ID() {
			
			JSONObject request = new JSONObject();
			request.put("userId", "11");
			request.put("id", "1");
			request.put("title", "Test_title");
			request.put("userId", "Test_title");
			given().contentType(ContentType.JSON).
		     accept(ContentType.JSON).
		     body(request.toJSONString())
		     .when().post("https://jsonplaceholder.typicode.com/posts")
		     .then().assertThat().body("'id'", equalTo(101));
        }  		   
	    
		}

