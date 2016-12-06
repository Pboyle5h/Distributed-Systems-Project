package ie.gmit.sw;
import java.rmi.*;
import java.rmi.registry.*;

public class StringServiceRunner {
	public static void main(String[] args) throws Exception{
	//A string, representing the message we want to associate with our Message object
			String myMessage = "Hello from 10.2.2.65";
			
			//Create an instance of the class Message and pass the string as an argument to its constructor.
			//Resultator johnsMessage = new ResultatorImpl(myMessage);
			
			//Create an instance of a MessageService. As MessageServiceImpl implements the MessageService
			//interface, it can be referred to as a MessageService type.
			StringService ss = new StringServiceImpl();
			
			//Start the RMI regstry on port 1099
			LocateRegistry.createRegistry(1099);
			
			//Bind our remote object to the registry with the human-readable name "howdayService"
			Naming.rebind("howdayService", ss);
			
			//Print a nice message to standard output
			System.out.println("Server ready.");
	}
}


