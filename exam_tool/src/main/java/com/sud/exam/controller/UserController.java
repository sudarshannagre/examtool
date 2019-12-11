package com.sud.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sud.exam.entity.User;
import com.sud.exam.exception.RecordNotFoundException;
import com.sud.exam.services.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<User>> getUseList() throws RecordNotFoundException{
		return new ResponseEntity<>(userService.getUseList(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUseById(@PathVariable int id) throws RecordNotFoundException{
		return new ResponseEntity<>(userService.getUseById(id),HttpStatus.OK);
		
	}
	
	@GetMapping("/{userName}/{password}")
	public ResponseEntity<User> getLogedIn(@PathVariable String userName, @PathVariable String password) throws RecordNotFoundException{
		return new ResponseEntity<>(userService.getLogedIn(userName,password),HttpStatus.OK);
	}

}
