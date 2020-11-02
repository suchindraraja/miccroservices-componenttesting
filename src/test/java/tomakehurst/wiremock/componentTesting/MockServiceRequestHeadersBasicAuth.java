package tomakehurst.wiremock.componentTesting;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceRequestHeadersBasicAuth
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		RestAssured.baseURI="http://localhost:9999/authperson";
		//Provide credentials
		PreemptiveBasicAuthScheme auth=new PreemptiveBasicAuthScheme();
		auth.setUserName("kalam@example.com");
		auth.setPassword("apjabdulkalam");
		RestAssured.authentication=auth;
		//create and send request to get response
		RequestSpecification req=RestAssured.given();
		Response res=req.request(Method.GET,"");
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().asString());
		JsonPath jp=res.jsonPath();
		String sid=jp.getString("session_id");
		System.out.println(sid);
	}
}
