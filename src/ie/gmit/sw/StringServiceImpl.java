package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	protected StringServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Resultator compare(String s, String t, String algo) throws RemoteException {
		// TODO Auto-generated method stub
		//Algos al = null;
		switch (algo) {
		case "Levenshtein":
			Levenshtein le = new Levenshtein();
			le.distance(s, t);
			break;

		case "DamerauLevenshtein":
			DamerauLevenshtein dl = new DamerauLevenshtein();
			dl.distance(s, t);
			break;
		case "HammingDistance":
			HammingDistance ha = new HammingDistance();
			ha.distance(s, t);
			break;

		default:
			break;
		}
		
		Resultator r = new ResultatorImpl();	
		return r;
	}

}
