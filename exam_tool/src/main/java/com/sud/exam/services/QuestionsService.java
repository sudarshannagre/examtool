package com.sud.exam.services;

import java.sql.SQLException;
import java.util.List;

import com.sud.exam.entity.Questions;
import com.sud.exam.exception.RecordNotFoundException;

public interface QuestionsService {

	public List<Questions> getQuestionsList() throws RecordNotFoundException;
	
	public Questions getQuestionById(int id) throws RecordNotFoundException;
	
	public Questions deleteQuestion(int id) throws RecordNotFoundException;
	
	public Questions createQuestion(Questions questuion) throws SQLException;
	
	public Questions updateQuestion(Questions question) throws RecordNotFoundException;
	
	public List<Questions> searchQuestionsByType(String type);

	public List<Questions> getQuestionsByType(int id) throws RecordNotFoundException;
	
}
