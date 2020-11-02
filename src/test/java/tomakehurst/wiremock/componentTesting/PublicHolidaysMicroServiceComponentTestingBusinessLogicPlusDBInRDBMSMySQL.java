package tomakehurst.wiremock.componentTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PublicHolidaysMicroServiceComponentTestingBusinessLogicPlusDBInRDBMSMySQL
{
	public static void main(String[] args) throws Exception
	{
		//Start the Wire mock server programmatically
		String path="cmd.exe /c start C:\\Users\\gattu\\Desktop\\wiremockserver.bat";
		Runtime.getRuntime().exec(path);
		
		//Wiremock server can completely check the files in Mappings folder
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
		List<String> restres=jp.getList("date");
		for(int i=0;i<restres.size();i++)
		{
			System.out.println(restres.get(i));
		}
		
		//Connect to the database of Microservice under testing
		Class.forName("com.mysql.jdbc.Driver");		//for MySQL
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/holidays","root","gatturohan");
		Statement st=con.createStatement();
		String[] splitresult=mockresult.split("/");
		int year=Integer.parseInt(splitresult[0]);
		String cc=splitresult[1];
		ResultSet res=st.executeQuery("select * from holidayslist where year="+year+" and cc like '"+cc+"%'");
		ArrayList<String> dbres=new ArrayList<String>();
		while(res.next())	//goto each row in result set
		{
			//Here 1 indicates 1st column(dates) in table
			String z=res.getString(1); //In DB index starts with 1
			System.out.println(z);
			dbres.add(z);
		}
		con.close();
		
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
