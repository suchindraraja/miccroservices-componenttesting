package tomakehurst.componentTesting;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceMappingsWithMultipleResourcesWithMultipleFieldsInResponse2
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		//Way 1
		//Resources(No of resources are 12)
		for(int i=1;i<=12;i++)
		{
			RestAssured.baseURI="http://localhost:9999/details/"+i;
			RequestSpecification mockrequest1=RestAssured.given();
			//Send request and get response
			Response mockresponse1=mockrequest1.request(Method.GET,"");
			JsonPath jp1=mockresponse1.jsonPath();
			String id=jp1.getString("id");
			String name=jp1.getString("name");
			String sal=jp1.getString("salary");
			String ms=jp1.getString("maritalStatus");
			String city=jp1.getString("city");
			System.out.println(id);
			System.out.println(name);
			System.out.println(sal);
			System.out.println(ms);
			System.out.println(city);
			System.out.println();
		}
		
		//Way 2
		//Resource 1
		/*RestAssured.baseURI="http://localhost:9999/details/1";
		RequestSpecification mockrequest1=RestAssured.given();
		//Send request and get response
		Response mockresponse1=mockrequest1.request(Method.GET,"");
		JsonPath jp1=mockresponse1.jsonPath();
		String x1=jp1.getString("id");
		String y1=jp1.getString("name");
		String z1=jp1.getString("salary");
		String p1=jp1.getString("maritalStatus");
		String q1=jp1.getString("city");
		System.out.println(x1);
		System.out.println(y1);
		System.out.println(z1);
		System.out.println(p1);
		System.out.println(q1);
		System.out.println();
		
		//Resource 2
		RestAssured.baseURI="http://localhost:9999/details/2";
		RequestSpecification mockrequest2=RestAssured.given();
		//Send request and get response
		Response mockresponse2=mockrequest2.request(Method.GET,"");
		JsonPath jp2=mockresponse2.jsonPath();
		String x2=jp2.getString("id");
		String y2=jp2.getString("name");
		String z2=jp2.getString("salary");
		String p2=jp2.getString("maritalStatus");
		String q2=jp2.getString("city");
		System.out.println(x2);
		System.out.println(y2);
		System.out.println(z2);
		System.out.println(p2);
		System.out.println(q2);
		System.out.println();*/
		
		//Resource 3
		//Resource 4
		//Resource 5
		//Resource 6
		//Resource 7
		//Resource 8
		//Resource 9
		//Resource 10
		//Resource 11
		//Resource 12
	}
}
