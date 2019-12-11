package com.sud.exam.services;

import java.util.List;

import com.sud.exam.entity.User;
import com.sud.exam.exception.RecordNotFoundException;

public interface UserService {

	public List<User> getUseList() throws RecordNotFoundException;
	
	public User getUseById(int id) throws RecordNotFoundException;

	public User getLogedIn(String userName, String password) throws RecordNotFoundException;
}
