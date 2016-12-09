package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {
	int distance;
	
	protected StringServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	Resultator r = new ResultatorImpl(); 
	private static final long serialVersionUID = 1L;
	public void stringCompareService(int distance){	
		             try {          
		                          
		          r.setResult("Distance is: "+distance);
		          r.setProcessed();           
		  		

             }catch (Exception ex){

                 ex.printStackTrace();

             }
		            

         } 
		
	

	@Override
	public Resultator compare(String s, String t, String algo) throws RemoteException {
		// TODO Auto-generated method stub
		//Algos al = null;
		
		
		switch (algo) {
		case "Levenshtein Distance":
			Levenshtein le = new Levenshtein();
			distance=le.distance(s, t);
			stringCompareService( distance);
			
			
			break;

		case "DamerauLevenshtein":
			DamerauLevenshtein dl = new DamerauLevenshtein();
			dl.distance(s, t);
			stringCompareService( distance);
			break;
		case "Hamming Distance":
			HammingDistance ha = new HammingDistance();
			ha.distance(s, t);
			stringCompareService( distance);
			break;

		default:
			break;
		}
		return r;
	
	}

}
