package tomakehurst.wiremock.componentTesting;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceRequestBodyParametersBodyPatterns 
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		RestAssured.baseURI="http://localhost:9999/myform";
		RequestSpecification req=RestAssured.given();
		//Pack parameters with values and then attach to request body
		JSONObject jo=new JSONObject();
		jo.put("rollNumber","11");
		jo.put("name","kalam");
		req.body(jo.toString());
		//Send request and get response
		Response res=req.request(Method.POST,"");
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().asString());
		JsonPath jp=res.jsonPath();
		String id=jp.getString("id");
		System.out.println(id);
	}
}
