package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class threadRequests implements Runnable {
	 //private long jobNo;
	 StringService ss= null;
		String s;
		String t;
		String algo;
		String jobNo;
		private LinkedList<RequestBuilder> inQueue ;
		private static Map<String, Resultator> outQueue ;
	    
	    public threadRequests(String jobNo, StringService ss2, LinkedList<RequestBuilder> in, Map<String, Resultator> out) {
	        this.jobNo = jobNo;
	        this.ss=ss2;
	        this.inQueue=in;
	        this.outQueue=out;
	    }
	

	
	@Override
	public void run() {		
		
		try 
		{		
			
			
			//add result and task number to the out queue(map) 
			//task number, result
			
			Resultator r = ss.compare( s,  t, algo);
			outQueue.put(jobNo, r);
			inQueue.poll();
			
			System.out.println(r.getResult());
				
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}


