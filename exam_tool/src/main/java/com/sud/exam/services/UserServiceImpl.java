package com.sud.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sud.exam.entity.User;
import com.sud.exam.exception.ExceptionConstants;
import com.sud.exam.exception.RecordNotFoundException;
import com.sud.exam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<User> getUseList() throws RecordNotFoundException {
		List<User> userList = userRepo.findAll();
		if(!userList.isEmpty()) {
			return userList;
		}
		throw new RecordNotFoundException(ExceptionConstants.NO_RECORD_AVAILABLE);
	}

	@Override
	public User getUseById(int id) throws RecordNotFoundException {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new RecordNotFoundException(ExceptionConstants.RECORD_NOT_FOUND+id);
	}

	@Override
	public User getLogedIn(String userName, String password) throws RecordNotFoundException {
		User logedInUser = userRepo.logedIn(userName, password);
		if(logedInUser!=null) {
			return logedInUser;
		}else {
			throw new RecordNotFoundException(ExceptionConstants.RECORD_NOT_FOUND);
		}
	}

}
