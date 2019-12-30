package com.sud.exam.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sud.exam.entity.User;
import com.sud.exam.exception.UserNotFoundException;
import com.sud.exam.services.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<User>> getUseList() throws UserNotFoundException{
		return new ResponseEntity<>(userService.getUseList(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUseById(@PathVariable int id) throws UserNotFoundException{
		return new ResponseEntity<>(userService.getUseById(id),HttpStatus.OK);
		
	}
	
	@GetMapping("/{userName}/{password}")
	public ResponseEntity<User> getLogedIn(@PathVariable String userName, @PathVariable String password) throws UserNotFoundException{
		return new ResponseEntity<>(userService.getLogedIn(userName,password),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> userRegistration(@RequestBody User user) throws SQLException{
		return new ResponseEntity<>(userService.userRegistration(user),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<User> userUpdate(@RequestBody User user) throws SQLException, UserNotFoundException{
		return new ResponseEntity<>(userService.userUpdate(user),HttpStatus.OK);
	}
	
	@PutMapping("/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) throws SQLException, UserNotFoundException{
		return new ResponseEntity<>(userService.forgotPassword(email),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/{oldPwd}/{newPwd}")
	public ResponseEntity<String> changePassword(@PathVariable int id,@PathVariable String oldPwd,@PathVariable String newPwd) throws UserNotFoundException{
		return new ResponseEntity<>(userService.changePassword(id,oldPwd,newPwd),HttpStatus.OK);
	}
}
