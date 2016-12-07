package ie.gmit.sw;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class StringServiceRunner {
	public static void main(String[] args) throws Exception{
	//A string, representing the message we want to associate with our Message object
			
			
			
			//Create an instance of a MessageService. As MessageServiceImpl implements the MessageService
			//interface, it can be referred to as a MessageService type.
			StringService ss = new StringServiceImpl();
			
			//Start the RMI regstry on port 1099
			LocateRegistry.createRegistry(1099);
			
			//Bind our remote object to the registry with the human-readable name "howdayService"
			Naming.rebind("test", ss);
			
			//Print a nice message to standard output
			System.out.println("Server ready.");
	}
}


