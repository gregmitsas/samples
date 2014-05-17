import java.rmi.*;

public interface ATM extends Remote
{
	public boolean checkID(int validID) throws RemoteException;
	public int checkAmount(int validID) throws RemoteException;
	public boolean checkWithdraw(int validID, int wamount) throws RemoteException;
	public void withdrawMoney(int validID, int wamount) throws RemoteException;
	public void depositMoney(int validID, int dbamount, int damount) throws RemoteException;
	public String reviewAccount(int validID) throws RemoteException;
	public void exitDB(int validID) throws RemoteException;
}