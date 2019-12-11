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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sud.exam.entity.Questions;
import com.sud.exam.exception.RecordNotFoundException;
import com.sud.exam.services.QuestionsService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/questions")
public class QuestionsController {
	
	@Autowired
	QuestionsService questionService;
	
	@GetMapping("/")
	public ResponseEntity<List<Questions>> getQuestionsList() throws RecordNotFoundException{
		return new ResponseEntity<>(questionService.getQuestionsList(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Questions> getQuestionById(@PathVariable int id) throws RecordNotFoundException{
		return new ResponseEntity<>(questionService.getQuestionById(id),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Questions> deleteQuestion(@PathVariable int id) throws RecordNotFoundException{
		return new ResponseEntity<>(questionService.deleteQuestion(id),HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Questions> createQuestion(@RequestBody Questions questions) throws SQLException{
		return new ResponseEntity<>(questionService.createQuestion(questions),HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Questions> updateQuestion(@RequestBody Questions questions) throws RecordNotFoundException{
		return new ResponseEntity<>(questionService.updateQuestion(questions),HttpStatus.OK);
	}
	
	@GetMapping("/search/{type}")
	public ResponseEntity<List<Questions>> searchQuestionsByType(@PathVariable String type){
		return new ResponseEntity<>(questionService.searchQuestionsByType(type),HttpStatus.OK);
	}
	
	@GetMapping("/types/{id}")
	public ResponseEntity<List<Questions>> getQuestionsByType(@PathVariable int id) throws RecordNotFoundException{
		return new ResponseEntity<>(questionService.getQuestionsByType(id),HttpStatus.OK);
	}
	
}
