package tomakehurst.componentTesting;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockServiceQueryParameters 
{
	public static void main(String[] args) throws Exception
	{
		//Start Mock server by double clicking on batch file
		//Before starting, mock server can completely check the files in Mappings folder
		//Connect to mock service to get required output
		
		RestAssured.baseURI="http://localhost:9999/queryparameters";
		RequestSpecification req=RestAssured.given();
		//Send request and get response
		Response res=req.queryParam("id","45").queryParam("search_item","address").request(Method.GET,"");
		int sc=res.getStatusCode();
		System.out.println("Status code is: "+sc);
		String fullresbody=res.getBody().asString();
		System.out.println("\nResponse body is :\n"+fullresbody);
		
		JsonPath jp=res.jsonPath();
		String fname=jp.getString("firstName");
		System.out.println("\nFirst name is "+fname);
		
		//Way 1
		String flatno=jp.getString("address.flatno");
		System.out.println("Flat no is "+flatno);
		String block=jp.getString("address.block");
		System.out.println("Block no is "+block);
		String phase=jp.getString("address.phase");
		System.out.println("Phase no is "+phase);
		String city=jp.getString("address.city");
		System.out.println("City is "+city);
		String state=jp.getString("address.state");
		System.out.println("State is "+state);
		String postal=jp.getString("address.postal");
		System.out.println("Postal no is "+postal);
		//Way 2
		Map<String,String> m=jp.getMap("address");
		System.out.println("Flat no is "+m.get("flatno"));
		System.out.println("Block no is "+m.get("block"));
		System.out.println("Phase no is "+m.get("phase"));
		System.out.println("City is "+m.get("city"));
		System.out.println("State is "+m.get("state"));
		System.out.println("Postal no is "+m.get("postal"));
		
		
		List<Map<String,String>> lm=jp.getList("phone");
		for(int i=0;i<lm.size();i++)
		{
			for(Map<String,String> map:lm)
			{
				System.out.println("\nNumber type is "+map.get("type"));
				System.out.println("Number is "+map.get("number"));
			}
			/*for(Map.Entry<String, String> map:lm.get(i).entrySet())
			{
				System.out.println(map.getKey()+"--"+map.getValue());
			}*/
		}
	}
}
