package ie.gmit.sw;

import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

public class ServiceHandler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String remoteHost = null;
	private static long jobNumber = 0;
	private LinkedList<RequestBuilder> inQueue = new LinkedList<RequestBuilder>();
	private static Map<String, Resultator> outQueue = new ConcurrentHashMap<String, Resultator>();
	
	
	//private BlockingQueue<> queue;

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
		
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		StringService ss= null;
		try {
			ss = (StringService) Naming.lookup("rmi://localhost:1099/test");
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		
		
		
		
		
		//Initialise some request variables with the submitted form info. These are local to this method and thread safe...
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");


		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			Resultator r = new ResultatorImpl();
			//Add job to in-queue
			
			RequestBuilder rb = new RequestBuilder(algorithm, s, t, taskNumber);
			
			//System.out.println(rb.toString());
			inQueue.add(rb);
		
			System.out.println(inQueue.size());
			//pass into in queue(linked list)
			//pass list into thread request
			//.poll on the thread request side 
			
			
			threadRequests tr = new threadRequests(taskNumber,ss,inQueue,outQueue); 
					
					tr.s=s;
					tr.t=t;
					tr.algo=algorithm;
					tr.run();
			    		
					
					jobNumber++;
					
		}else{
			//Check out-queue for finished job
			//
		
			
			Resultator r = outQueue.get(taskNumber);
			
			if(r.isProcessed()){
				System.out.println(taskNumber+" Has completed and"+r.getResult());
				
			}
		}
		
		
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
		out.print("<br>This servlet should only be responsible for handling client request and returning responses. Everything else should be handled by different objects.");
		out.print("Note that any variables declared inside this doGet() method are thread safe. Anything defined at a class level is shared between HTTP requests.");				
		out.print("</b></font>");

		out.print("<P> Next Steps:");	
		out.print("<OL>");
		out.print("<LI>Generate a big random number to use a a job number, or just increment a static long variable declared at a class level, e.g. jobNumber.");	
		out.print("<LI>Create some type of an object from the request variables and jobNumber.");	
		out.print("<LI>Add the message request object to a LinkedList or BlockingQueue (the IN-queue)");			
		//out.print("<LI>Return the jobNumber to the client web browser with a wait interval using <meta http-equiv=\"refresh\" content=\"10\">. The content=\"10\" will wait for 10s.");	
		out.print("<LI>Have some process check the LinkedList or BlockingQueue for message requests.");	
		out.print("<LI>Poll a message request from the front of the queue and make an RMI call to the String Comparison Service.");			
		out.print("<LI>Get the <i>Resultator</i> (a stub that is returned IMMEDIATELY by the remote method) and add it to a Map (the OUT-queue) using the jobNumber as the key and the <i>Resultator</i> as a value.");	
		out.print("<LI>Return the result of the string comparison to the client next time a request for the jobNumber is received and the <i>Resultator</i> returns true for the method <i>isComplete().</i>");	
		out.print("</OL>");	
		
		//if processed == false
		out.print("<form name=\"frmRequestDetails\">");
		
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
				
		//You can use this method to implement the functionality of an RMI client
		
		//
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}