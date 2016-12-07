package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResultatorImpl extends UnicastRemoteObject implements Resultator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ResultatorImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getResult() throws RemoteException {
	
		return "test";
		 
	}

	@Override
	public void setResult(String result) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isProcessed() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setProcessed() throws RemoteException {
		// TODO Auto-generated method stub

	}

}
