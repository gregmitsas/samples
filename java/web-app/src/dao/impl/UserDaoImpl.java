package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import dao.UserDao;
import database.DatabaseManager;

public class UserDaoImpl implements UserDao
{
	
	public void addUser(User user)
	{
		query = "insert into user(email, password, first_name, last_name, phone) values(?, ?, ?, ?, ?)";
		try
		{
			conn = DatabaseManager.getConnection();
			pst = conn.prepareStatement(query);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFirstName());
			pst.setString(4, user.getLastName());
			pst.setString(5, user.getPhone());
			pst.executeUpdate();
		}
		catch (SQLException sqle)
		{
			System.out.print(sqle);
		}
		finally
		{
			DatabaseManager.closePreparedStatement(pst);
			DatabaseManager.closeConnection(conn);
		}

	}

	public void updateUser(User user)
	{
		query = "update user set email = ?, password = ?, first_name = ?, last_name = ?, phone = ? where user_id = ?";
		try
		{
			conn = DatabaseManager.getConnection();
			pst = conn.prepareStatement(query);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFirstName());
			pst.setString(4, user.getLastName());
			pst.setString(5, user.getPhone());
			pst.setInt(6, user.getId());
			pst.executeUpdate();
		}
		catch (SQLException sqle)
		{
			System.out.print(sqle);
		}
		finally
		{
			DatabaseManager.closePreparedStatement(pst);
			DatabaseManager.closeConnection(conn);
		}
	}

	public User getUserByEmail(String email)
	{
		query = "select * from user where email ='" + email + "'";
		try
		{
			conn = DatabaseManager.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
			{
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPhone(rs.getString("phone"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.print(sqle);
		}
		finally
		{
			DatabaseManager.closePreparedStatement(pst);
			DatabaseManager.closeConnection(conn);
		}
		return user;
	}

	public ArrayList<User> getAllUsers()
	{
		query = "select * from user";
		try
		{
			conn = DatabaseManager.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			usersList = new ArrayList<User>();
			while (rs.next())
			{
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPhone(rs.getString("phone"));
				usersList.add(user);
			}
			return usersList;
		}
		catch (SQLException sqle)
		{
			System.out.print(sqle);
		}
		finally
		{
			DatabaseManager.closePreparedStatement(pst);
			DatabaseManager.closeConnection(conn);
		}
		return null;
	}

	public User checkLogin(String email, String password)
	{
		query = "select * from user where email ='" + email + "' and password = '" + password + "'";
		try
		{
			conn = DatabaseManager.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
			{
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPhone(rs.getString("phone"));
				return user;
			}
		}
		catch (SQLException sqle)
		{
			System.out.print(sqle);
		}
		finally
		{
			DatabaseManager.closePreparedStatement(pst);
			DatabaseManager.closeConnection(conn);
		}
		return null;
	}

	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private String query;
	private User user;
	private ArrayList<User> usersList;

}
