package dao;

import java.util.ArrayList;

import model.User;

public interface UserDao
{
	void addUser(User user);
	void updateUser(User user);
	User getUserByEmail(String email);
	ArrayList<User> getAllUsers();
	User checkLogin(String email, String password);
}
