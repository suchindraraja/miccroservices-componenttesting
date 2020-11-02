package tomakehurst.wiremock.componentTesting;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceMappingsWithMultipleResourcesWithOneFieldInResponse
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		//Way 1
		//Resource 1
		RestAssured.baseURI="http://localhost:9999/user/1";
		RequestSpecification mockrequest1=RestAssured.given();
		//Send request and get response
		Response mockresponse1=mockrequest1.request(Method.GET,"");
		JsonPath jp1=mockresponse1.jsonPath();
		String x1=jp1.getString("id");
		System.out.println(x1);
		
		//Resource 2
		RestAssured.baseURI="http://localhost:9999/user/2";
		RequestSpecification mockrequest2=RestAssured.given();
		//Send request and get response
		Response mockresponse2=mockrequest2.request(Method.GET,"");
		JsonPath jp2=mockresponse2.jsonPath();
		String x2=jp2.getString("id");
		System.out.println(x2);
		
		//Resource 3
		RestAssured.baseURI="http://localhost:9999/user/3";
		RequestSpecification mockrequest3=RestAssured.given();
		//Send request and get response
		Response mockresponse3=mockrequest3.request(Method.GET,"");
		JsonPath jp3=mockresponse3.jsonPath();
		String x3=jp3.getString("id");
		System.out.println(x3);
		
		//Resource 4
		RestAssured.baseURI="http://localhost:9999/user/4";
		RequestSpecification mockrequest4=RestAssured.given();
		//Send request and get response
		Response mockresponse4=mockrequest4.request(Method.GET,"");
		JsonPath jp4=mockresponse4.jsonPath();
		String x4=jp4.getString("id");
		System.out.println(x4);
		
		//Resource 5
		RestAssured.baseURI="http://localhost:9999/user/5";
		RequestSpecification mockrequest5=RestAssured.given();
		//Send request and get response
		Response mockresponse5=mockrequest5.request(Method.GET,"");
		JsonPath jp5=mockresponse5.jsonPath();
		String x5=jp5.getString("id");
		System.out.println(x5);
		
		//Way 2
		//Resources(No of resources are 5)
		/*for(int i=1;i<=5;i++)
		{
			RestAssured.baseURI="http://localhost:9999/user/"+i;
			RequestSpecification mockrequest1=RestAssured.given();
			//Send request and get response
			Response mockresponse1=mockrequest1.request(Method.GET,"");
			JsonPath jp1=mockresponse1.jsonPath();
			String id=jp1.getString("id");
			System.out.println(id);
		}*/
	}
}
