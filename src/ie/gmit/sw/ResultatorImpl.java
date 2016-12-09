package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResultatorImpl extends UnicastRemoteObject implements Resultator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String result;
	public boolean processed = false;
	protected ResultatorImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getResult() throws RemoteException {
	
		return result;
		 
	}

	@Override
	public void setResult(String result) throws RemoteException {
		this.result=result;

	}

	@Override
	public boolean isProcessed() throws RemoteException {
		
		return processed;
	}

	@Override
	public void setProcessed() throws RemoteException {
		processed=true;

	}

}
