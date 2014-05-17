import java.rmi.*;
import java.security.*;
import java.util.Scanner;

public class ATMClient
{
	private static final String HOST = "192.168.1.77";
	private static ATM ref;
	private static Scanner keyboard = new Scanner(System.in);

	private static boolean back;
	private static int typeOfProcess;
	private static int givenID;

	public static void main(String[] args)
	{

		// Set the security policy
		if (System.getSecurityManager() == null)
		{
			System.setSecurityManager(new RMISecurityManager());
		}

		try
		{
			System.out.println("\nTrying to connect..");
			ref = (ATM) Naming.lookup("rmi://" + HOST + "/ATM");
			System.out.println("\nConnected!");

			while (true)
			{
				while (true)
				{
					System.out.print("\nATM> Enter your ID: ");
					givenID = getValidInt(keyboard.next());
					boolean gotValidID = ref.checkID(givenID);
					if (gotValidID == true)
					{
						break;
					}
					else
					{
						System.out.println("\nATM> ID *" + givenID + "* does not exist! Try again!");
					}
				}

				while (true)
				{
					displayOptions();
					typeOfProcess = getSelectedTypeOfProcess(keyboard.next());

					if (typeOfProcess == 1)
					{
						while (true)
						{
							System.out
									.print("\nATM> Enter amount of money you want to withdraw: ");
							int wamount = getValidInt(keyboard.next());
							boolean gotValidAmount = ref.checkWithdraw(givenID, wamount);
							if (gotValidAmount == true)
							{
								ref.withdrawMoney(givenID, wamount);
								System.out.print("\nATM> Done! " + wamount + " withdrawed from your account!\n");
								break;
							}
							else
							{
								System.out.println("\nATM> *WARNING*");
								System.out.println("ATM> AMOUNT EXCEEDS YOUR INITIAL DEPOSIT OR AMOUNT > 600. TRY AGAIN!!");
								System.out.println("ATM> *WARNING*");
							}
						}
					}

					if (typeOfProcess == 2)
					{
						System.out.print("\nATM> Enter amount of money you want to deposit: ");
						int damount = getValidInt(keyboard.next());
						int dbamount = ref.checkAmount(givenID);
						ref.depositMoney(givenID, dbamount, damount);
						System.out.print("\nATM> Done! " + damount + " added to your account!\n");
					}

					if (typeOfProcess == 3)
					{
						String review = ref.reviewAccount(givenID);
						System.out.println("\nATM> " + review);
						System.out.print("\nATM> Go Back To Options? (y/n): ");
						back = goBack(keyboard.next());
						if (back == false)
						{
							System.out.println("\nATM> USER ID: *" + givenID + "* IS LEAVING..");
							ref.exitDB(givenID);
							System.out.println("\nATM> BYE!");
							break;
						}
					}

					if (typeOfProcess == 4)
					{
						System.out.println("\nATM> USER ID: *" + givenID + "* IS LEAVING..");
						ref.exitDB(givenID);
						System.out.println("\nATM> BYE!");
						break;
					}
				}
				
				break;
			}

		}
		catch (RemoteException re)
		{
			System.out.println("\nRemote Exception");
		}
		catch (AccessControlException ace)
		{
			System.out.println("\nSecurity Access Exception");
		}
		catch (Exception e)
		{
			System.out.println("\n\nClient Left");
		}
	}

	private static int getValidInt(String userInput)
	{

		int validInt;
		while (true)
		{
			try
			{
				validInt = Integer.parseInt(userInput);
				if (validInt > 0)
				{
					return validInt;
				}
				else
				{
					System.out.print("\nATM> Invalid input. Please type in a POSITIVE INTEGER: ");
					userInput = keyboard.next();
				}
			}
			catch (NumberFormatException nfe)
			{
				System.out.print("\nATM> Invalid input. Please type in an INTEGER: ");
				userInput = keyboard.next();
			}
		}
	}

	private static void displayOptions()
	{
		System.out.print("\nATM> Select Transaction Type To Proceed: \n"
							+ "     1.Withdraw Money\n" + "     2.Deposit Money\n"
							+ "     3.Review Account\n" + "     4.Exit\t\t");
	}

	private static int getSelectedTypeOfProcess(String userInput)
	{
		int selectedTypeOfProcess;
		while (true)
		{
			try
			{
				selectedTypeOfProcess = Integer.parseInt(userInput);
				if (isValidTypeOfProcess(selectedTypeOfProcess))
				{
					return selectedTypeOfProcess;
				}
				else
				{
					throw new NumberFormatException();
				}

			}
			catch (NumberFormatException nfe)
			{
				System.out.println("\nATM> Invalid type of transaction.");
				iErrMsg();
				userInput = keyboard.next();
			}
		}
	}

	private static boolean isValidTypeOfProcess(int typeOfProcess)
	{
		if (typeOfProcess >= 1 && typeOfProcess <= 4)
		{
			return true;
		}
		return false;
	}

	private static void iErrMsg()
	{
		System.out.println("\n\nATM> Available choices are: ");
		System.out.println("ATM> Withdraw Money\t> Press 1");
		System.out.println("ATM> Deposit Money\t> Press 2");
		System.out.println("ATM> Review Account\t> Press 3");
		System.out.println("ATM> Exit\t\t> Press 4");
		System.out.print("ATM> : ");
	}

	private static boolean goBack(String userInput)
	{
		char back;
		while (true)
		{
			if (userInput.length() == 1)
			{
				back = userInput.charAt(0);
				if (back == 'y' || back == 'Y')
				{
					return true;
				}
				else if (back == 'n' || back == 'N')
				{
					return false;
				}
				else
				{
					System.out.print("\nATM> You need to type in  y/n. \nATM> Go Back? (y/n): ");
					userInput = keyboard.next();
				}
			}
			else
			{
				System.out.print("\nATM> You need to type in  y/n. \nATM> Go Back? (y/n): ");
				userInput = keyboard.next();
			}
		}
	}

}