package dao;

import java.util.ArrayList;

import model.Car;

public interface CarDao
{
	ArrayList<Car> getAllCars();
	Car getCarById(int id);
}
