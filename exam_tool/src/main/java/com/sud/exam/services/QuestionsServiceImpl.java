package com.sud.exam.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sud.exam.entity.Questions;
import com.sud.exam.exception.ExceptionConstants;
import com.sud.exam.exception.RecordNotFoundException;
import com.sud.exam.repository.QuestionsRepository;

@Service
public class QuestionsServiceImpl implements QuestionsService{

	@Autowired
	QuestionsRepository questionRepo; 
	
	@Override
	public List<Questions> getQuestionsList() throws RecordNotFoundException {
		List<Questions> questionsList = questionRepo.findAll();
		if(!questionsList.isEmpty()) {
			return questionsList;
		}else {
			throw new RecordNotFoundException(ExceptionConstants.NO_RECORD_AVAILABLE);
		}
	}

	@Override
	public Questions getQuestionById(int id) throws RecordNotFoundException {
		Optional<Questions> question = questionRepo.findById(id);
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new RecordNotFoundException(ExceptionConstants.RECORD_NOT_FOUND+id);
		}
	}

	@Override
	public Questions deleteQuestion(int id) throws RecordNotFoundException {
		Optional<Questions> question = questionRepo.findById(id);
		if(question.isPresent()) {
			questionRepo.delete(question.get());
			return question.get();
		}else {
			throw new RecordNotFoundException(ExceptionConstants.RECORD_NOT_FOUND+id);
		}
	}

	@Override
	public Questions createQuestion(Questions questuion) throws SQLException {
		Questions createdQuestion = questionRepo.save(questuion);
		if(createdQuestion!=null) {
			return createdQuestion;
		}else {
			throw new SQLException(ExceptionConstants.WRONG_REQUEST);
		}
	}

	@Override
	public Questions updateQuestion(Questions question) throws RecordNotFoundException {
		if(questionRepo.findById(question.getId())!=null) {
			return questionRepo.save(question);
		}else {
			throw new RecordNotFoundException(ExceptionConstants.RECORD_NOT_FOUND+question.getId());
		}
	}

	@Override
	public List<Questions> searchQuestionsByType(String type) {
		return null;
	}

	@Override
	public List<Questions> getQuestionsByType(int id) throws RecordNotFoundException {
		List<Questions> questionList = questionRepo.getQuestionsByType(id);
		if(!questionList.isEmpty()) {
			return questionList;
		}else {
			throw new RecordNotFoundException(ExceptionConstants.NO_RECORD_AVAILABLE);
		}
	}

}
