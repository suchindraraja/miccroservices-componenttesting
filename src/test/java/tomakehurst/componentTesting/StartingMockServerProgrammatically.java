package tomakehurst.componentTesting;

public class StartingMockServerProgrammatically
{
	public static void main(String[] args) throws Exception
	{
		//After development of all mock services, and corresponding modifications are performed
		//When there are no further modifications to be performed in any of the file in "mappings"
		//folder and "__files" folder, we can start the wiremock server programmatically
		//But when we perform taskkill action on wiremock server, it only can close the visibility
		//of the server prompt but server will be alive at the port at which it is runnning
		//To stop it we need to perform netstat -ano in command prompt manually and kill the server
		
		//Start the Wire mock server programmatically
		String path="cmd.exe /c start C:\\Users\\gattu\\Desktop\\wiremockserver.bat";
		Runtime.getRuntime().exec(path);
		
		//Corresponding microservice component testing with the help of mock server
		
		Thread.sleep(10000);
		//Close command prompt
		Runtime.getRuntime().exec("taskkill /F /IM conhost.exe");
	}
}
