import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ATMImpl extends UnicastRemoteObject implements ATM
{

	private int dbamount;
	private String review;

	String url = "jdbc:mysql://localhost/rmi";
	String user = "greg";
	String pass = "gerg";
	private static Connection con;

	public ATMImpl() throws RemoteException, Exception
	{
		super();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, pass);
	}

	public boolean checkID(int validID) throws RemoteException
	{
		try
		{
			Statement st = con.createStatement();
			String query = "select * from accounts where customerID='" + validID + "'";
			ResultSet rs = st.executeQuery(query);
			
			if (!rs.next())
			{
				st.close();
				return false;
			}
			else
			{
				st.close();
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("\n" + sqle);
		}
		return true;
	}

	public int checkAmount(int validID) throws RemoteException
	{
		try
		{
			Statement st = con.createStatement();
			String query = "select balance from accounts where customerID='" + validID + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				dbamount = rs.getInt(1);
			}
			st.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("\n" + sqle);
		}
		return dbamount;
	}

	public boolean checkWithdraw(int validID, int wamount) throws RemoteException
	{
		int dbamount = checkAmount(validID);
		if (wamount > dbamount || wamount > 600)
		{
			return false;
		}
		return true;
	}

	public void withdrawMoney(int validID, int wamount) throws RemoteException
	{
		try
		{
			Statement st = con.createStatement();
			String query = "INSERT INTO `transactions` "
					+ "(`transactionID`, `customerID`, `TYPE`, `AMOUNT`, `DATE`) "
					+ "VALUES (NULL, '" + validID + "', 'WITHDRAW', '"
					+ wamount + "', '" + getDateTime() + "');";
			st.executeUpdate(query);
			st.close();

			int newamount = dbamount - wamount;
			Statement st2 = con.createStatement();
			String query2 = "UPDATE accounts SET balance ='" + newamount + "' WHERE customerID='" + validID + "'";
			st2.executeUpdate(query2);
			st2.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("\n" + sqle);
		}
	}

	public void depositMoney(int validID, int dbamount, int damount) throws RemoteException
	{
		try
		{
			Statement st = con.createStatement();
			String query = "INSERT INTO `transactions` "
					+ "(`transactionID`, `customerID`, `TYPE`, `AMOUNT`, `DATE`) "
					+ "VALUES (NULL, '" + validID + "', 'DEPOSIT', '" + damount
					+ "', '" + getDateTime() + "');";
			st.executeUpdate(query);
			st.close();

			int newamount = dbamount + damount;
			Statement st2 = con.createStatement();
			String query2 = "UPDATE accounts SET balance ='" + newamount + "' WHERE customerID='" + validID + "'";
			st2.executeUpdate(query2);
			st2.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("\n" + sqle);
		}
	}

	public String reviewAccount(int validID) throws RemoteException
	{
		try
		{
			int amount = checkAmount(validID);
			Statement st = con.createStatement();
			String query = "INSERT INTO `transactions` "
					+ "(`transactionID`, `customerID`, `TYPE`, `AMOUNT`, `DATE`) "
					+ "VALUES (NULL, '" + validID + "', 'REVIEW', '" + amount
					+ "', '" + getDateTime() + "');";
			st.executeUpdate(query);
			st.close();

			Statement st2 = con.createStatement();
			String query2 = "select customerID, name, balance from accounts where customerID='"
					+ validID + "'";
			ResultSet rs2 = st2.executeQuery(query2);
			while (rs2.next())
			{
				review = "ID: " + rs2.getString(1) + " | NAME: " + rs2.getString(2) + " | BALANCE: " + rs2.getString(3);
			}
			st2.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("\n" + sqle);

		}
		return review;
	}

	public void exitDB(int validID) throws RemoteException
	{
		try
		{
			int amount = checkAmount(validID);
			Statement st = con.createStatement();
			String query = "INSERT INTO `transactions` "
					+ "(`transactionID`, `customerID`, `TYPE`, `AMOUNT`, `DATE`) "
					+ "VALUES (NULL, '" + validID + "', 'EXIT', '" + amount
					+ "', '" + getDateTime() + "');";
			st.executeUpdate(query);
			st.close();

		}
		catch (SQLException sqle)
		{
			System.out.println("\n" + sqle);

		}
	}

	public String getDateTime()
	{
		Date transactionDate = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formatedDate = f.format(transactionDate);
		return formatedDate;
	}

}