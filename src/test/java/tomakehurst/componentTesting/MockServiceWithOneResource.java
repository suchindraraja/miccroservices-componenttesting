package tomakehurst.componentTesting;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceWithOneResource 
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service instead of real service under testing
		RestAssured.baseURI="http://localhost:9999/person/p1";
		RequestSpecification mockrequest=RestAssured.given();
		//Send request and get response
		Response mockresponse=mockrequest.request(Method.GET,"");
		String mockresult=mockresponse.getBody().asString();
		System.out.println(mockresult);
		
		//Connect to real micro service under testing
		RestAssured.baseURI="https://date.nager.at/api/v2/PublicHolidays";
		RequestSpecification realrequest=RestAssured.given();
		//Send req and get response
		Response realresponse=realrequest.request(Method.GET,mockresult);
		//String realresult=realresponse.getBody().asString();
		//System.out.println(realresult);
		JsonPath jp=realresponse.jsonPath();
		List<String> l=jp.getList("date");
		for(int i=0;i<l.size();i++)
		{
			System.out.println(l.get(i));
		}
	}
}
