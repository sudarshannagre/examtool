package com.sud.exam.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sud.exam.entity.TestTypes;
import com.sud.exam.exception.RecordNotFoundException;
import com.sud.exam.services.TestTypesService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/testtypes")
public class TestTypesController {

	@Autowired
	TestTypesService testTypeService;
	
	@GetMapping("")
	public ResponseEntity<List<TestTypes>> getTestList() throws RecordNotFoundException{
		return new ResponseEntity<>(testTypeService.getTestList(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TestTypes> getTestById(@PathVariable int id) throws RecordNotFoundException{
		return new ResponseEntity<>(testTypeService.getTestById(id),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<TestTypes> createTest(@RequestBody TestTypes testType) throws SQLException{
		return new ResponseEntity<>(testTypeService.createTest(testType),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TestTypes> deleteTest(@PathVariable int id) throws RecordNotFoundException{
		return new ResponseEntity<>(testTypeService.deleteTest(id),HttpStatus.OK);
	}
}
