package tomakehurst.componentTesting;


import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceWithOneResourceArrayOfResponse
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		//Way 1
		RestAssured.baseURI="http://localhost:9999/people/friends";
		RequestSpecification mockrequest1=RestAssured.given();
		//Send request and get response
		Response mockresponse1=mockrequest1.request(Method.GET,"");
		JsonPath jp1=mockresponse1.jsonPath();
		List<Map<String,String>> lm=jp1.getList("");
		for(int i=0;i<lm.size();i++)
		{
			System.out.println("Resource "+(i+1)+":");
			for(Map.Entry<String,String> m:lm.get(i).entrySet())
			{
				System.out.println(m.getKey()+"-->"+m.getValue());
			}
			System.out.println();
		}
		
		//Way 2
		/*RestAssured.baseURI="http://localhost:9999/people/friends";
		RequestSpecification mockrequest1=RestAssured.given();
		//Send request and get response
		Response mockresponse1=mockrequest1.request(Method.GET,"");
		JsonPath jp1=mockresponse1.jsonPath();
		List<Map> lm=jp1.getList("");
		System.out.println(lm.size());
		for(int i=0;i<lm.size();i++)
		{
			System.out.println("Resource "+(i+1)+":");
			for(Map m:lm)
			{
				System.out.println(jp1.getString("id"));
				System.out.println(jp1.getString("name"));
				System.out.println(jp1.getString("salary"));
				System.out.println(jp1.getString("maritalStatus"));
				System.out.println(jp1.getString("city"));
				System.out.println();
			}
		}*/	
	}
}
