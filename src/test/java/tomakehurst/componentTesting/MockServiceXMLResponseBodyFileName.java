package tomakehurst.componentTesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceXMLResponseBodyFileName 
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		RestAssured.baseURI="http://localhost:9999/webservicesserver/numberconversion";
		RequestSpecification req=RestAssured.given();
		//Send request and get response
		Response res=req.request(Method.GET,"");
		System.out.println("Status code is: "+res.getStatusCode());
		System.out.println("Full response body is:\n"+res.getBody().asString());
		XmlPath xp=res.xmlPath();
		String x=xp.getString("**.findAll{it.name()=='NumberToDollarsResult'}");
		System.out.println(x);
	}
}
