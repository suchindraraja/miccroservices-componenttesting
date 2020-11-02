package tomakehurst.componentTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PublicHolidaysMicroServiceComponentTestingBusinessLogicPlusDBInDDBMSMongoDB
{
	public static void main(String[] args) throws Exception
	{
		//Start the Wire mock server programmatically
		String path="cmd.exe /c start C:\\Users\\gattu\\Desktop\\wiremockserver.bat";
		Runtime.getRuntime().exec(path);
		
		//Wiremock server can completely check the files in Mappings folder
		//Connect to mock service1 instead of real service under testing
		RestAssured.baseURI="http://localhost:9999/person";
		RequestSpecification mockrequest1=RestAssured.given();
		//Send request and get response
		Response mockresponse1=mockrequest1.request(Method.GET,"/year");
		String mockresult1=mockresponse1.getBody().asString();
		System.out.println(mockresult1);
		//Connect to mock service2 instead of real service under testing
		RestAssured.baseURI="http://localhost:9999/person";
		RequestSpecification mockrequest2=RestAssured.given();
		//Send request and get response
		Response mockresponse2=mockrequest2.request(Method.GET,"/canada");
		String mockresult2=mockresponse2.getBody().asString();
		System.out.println(mockresult2);
		//Connect to real micro service under testing
		RestAssured.baseURI="https://date.nager.at/api/v2/PublicHolidays";
		RequestSpecification realrequest=RestAssured.given();
		//Send req and get response
		Response realresponse=realrequest.request(Method.GET,mockresult1+"/"+mockresult2);
		//String realresult=realresponse.getBody().asString();
		//System.out.println(realresult);
		JsonPath jp=realresponse.jsonPath();
		List<String> restres=jp.getList("date");
		for(int i=0;i<restres.size();i++)
		{
			System.out.println(restres.get(i));
		}
		
		//Connect to the database of Microservice under testing
		MongoClient mc=new MongoClient("localhost",27017);
		MongoDatabase md=mc.getDatabase("kalamdb");
		MongoCollection<Document> col=md.getCollection("holidays");
		Document doc=col.find(Filters.and(Filters.eq("year",mockresult1),Filters.regex("cc",mockresult2))).first();
		ArrayList<String> dbres=(ArrayList<String>) doc.get("date");
		System.out.println(dbres);
		
		//Validations
		if(dbres.equals(restres))
		{
			System.out.println("Component test passed");
		}
		else
		{
			System.out.println("Component test failed");
		}
		
		//Close command prompt
		Runtime.getRuntime().exec("taskkill /F /IM conhost.exe");
	}
}
