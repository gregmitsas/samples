import java.rmi.*;

public class ATMServer
{
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception
	{

		// Set the security policy
		if (System.getSecurityManager() == null)
		{
			System.setSecurityManager(new RMISecurityManager());
		}

		ATMImpl robj = new ATMImpl();

		String rmiObjectName = "rmi://" + HOST + "/ATM";
		Naming.rebind(rmiObjectName, robj);

		System.out.println("\nServer is running..");
	}
}