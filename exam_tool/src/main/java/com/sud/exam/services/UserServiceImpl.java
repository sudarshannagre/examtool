package com.sud.exam.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sud.exam.communication.EmailService;
import com.sud.exam.entity.User;
import com.sud.exam.exception.ExceptionConstants;
import com.sud.exam.exception.UserNotFoundException;
import com.sud.exam.repository.UserRepository;
import com.sud.exam.security.SHASecurity;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SHASecurity security;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public List<User> getUseList() throws UserNotFoundException {
		List<User> userList = userRepo.findAll();
		if(!userList.isEmpty()) {
			return userList;
		}
		throw new UserNotFoundException(ExceptionConstants.NO_USER_AVAILABLE);
	}

	@Override
	public User getUseById(int id) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND);
	}

	@Override
	public User getLogedIn(String userName, String password) throws UserNotFoundException {
		User logedInUser = userRepo.logedIn(userName, security.getSHA256SecuredPassword(password));
		if(logedInUser!=null) {
			return logedInUser;
		}else {
			throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND);
		}
	}

	@Override
	public User userRegistration(User user) throws SQLException {
		user.setPassword(security.getSHA256SecuredPassword(user.getPassword()));
		User createdUser = userRepo.save(user);
		if(createdUser!=null) {
			return createdUser;
		}else {
			throw new SQLException(ExceptionConstants.WRONG_REQUEST);
		}
	}

	@Override
	public User userUpdate(User user) throws UserNotFoundException  {
		if(userRepo.findById(user.getId())!=null) {
			return userRepo.save(user);
		}else {
			throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND);
		}
	}

	@Override
	public String forgotPassword(String email) throws UserNotFoundException {
		User user = userRepo.findByEmail(email);
		if(user!=null) {
			String newPassword = security.getSHA256SecuredPassword(security.generatePassword());
			user.setPassword(newPassword);
			userRepo.save(user);
			String body = "Hi "+user.getName()+",<br>You recently requested to reset your password for your Examtoolapp account.<br>"
					+ "Used below password for login.<br>Temparory Password : "+newPassword+"<br><br>Thanks and Regards,<br>Examtoolapp Team.";
			emailService.sendMail(email, "Password Reset", body);
			return "Check Your mail for new Password";
		}else {
			throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND);
		}
	}

	@Override
	public String changePassword(int id, String oldPwd, String newPwd) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			if(user.get().getPassword().equals(security.getSHA256SecuredPassword(oldPwd))) {
				user.get().setPassword(security.getSHA256SecuredPassword(newPwd));
				userRepo.save(user.get());
				System.out.println("Password Change successfully");
				return "Password Change successfully";
			}else {
				System.out.println("Old Password is Wrong");
				return "Old Password is Wrong";
			}
		}else {
			throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND);
		}
	}

}
