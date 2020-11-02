package tomakehurst.componentTesting;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceResponseHeaders 
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		RestAssured.baseURI="http://localhost:9999/headers/response";
		RequestSpecification mockrequest=RestAssured.given();
		//Send request and get response
		Response mockresponse=mockrequest.request(Method.GET,"");
		int sc=mockresponse.getStatusCode();
		System.out.println("Status code is: "+sc);
		Headers lh=mockresponse.getHeaders();
		System.out.println("\nHeaders are:");
		for(Header h:lh)
		{
			System.out.println(h.getName()+"-->"+h.getValue());
		}
		String mockresult=mockresponse.getBody().asString();
		System.out.println("\nResponse body is :\n"+mockresult);
	}
}
