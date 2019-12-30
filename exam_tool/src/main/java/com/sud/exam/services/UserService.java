package com.sud.exam.services;

import java.sql.SQLException;
import java.util.List;

import com.sud.exam.entity.User;
import com.sud.exam.exception.UserNotFoundException;

public interface UserService {

	public List<User> getUseList() throws UserNotFoundException;
	
	public User getUseById(int id) throws UserNotFoundException;

	public User getLogedIn(String userName, String password) throws UserNotFoundException;
	
	public User userRegistration(User user) throws SQLException;

	public User userUpdate(User user) throws UserNotFoundException;
	
	public String forgotPassword(String email) throws UserNotFoundException;

	public String changePassword(int id, String oldPwd, String newPwd) throws UserNotFoundException;
	
}
