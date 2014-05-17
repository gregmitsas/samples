package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Car;
import dao.CarDao;
import database.DatabaseManager;

public class CarDaoImpl implements CarDao
{
	
	public ArrayList<Car> getAllCars()
	{
		carList = new ArrayList<Car>();
		query = "select * from car";
		try
		{
			conn = DatabaseManager.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				car = new Car();
				car.setId(rs.getInt("car_id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getInt("year"));
				car.setEngine(rs.getString("engine"));
				car.setAvailability(rs.getBoolean("availability"));
				car.setPricePerDay(rs.getDouble("price"));
				carList.add(car);
			}
			return carList;
		}
		catch (SQLException e)
		{
			System.out.print(e);
		}
		finally
		{
			DatabaseManager.closePreparedStatement(pst);
			DatabaseManager.closeConnection(conn);
		}
		return null;
	}

	public Car getCarById(int id)
	{
		query = "select * from car where car_id ='" + id + "'";
		try
		{
			conn = DatabaseManager.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
			{
				car = new Car();
				car.setId(rs.getInt("car_id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getInt("year"));
				car.setEngine(rs.getString("engine"));
				car.setAvailability(rs.getBoolean("availability"));
				car.setPricePerDay(rs.getDouble("price"));
			}
			return car;
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
	private Car car;
	private ArrayList<Car> carList;

}
