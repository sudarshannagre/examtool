package com.sud.exam.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sud.exam.entity.TestTypes;
import com.sud.exam.exception.ExceptionConstants;
import com.sud.exam.exception.RecordNotFoundException;
import com.sud.exam.repository.TestTypesRepository;

@Service
public class TestTypesServiceImpl implements TestTypesService{

	@Autowired
	TestTypesRepository testTypesRepo;
	
	@Override
	public List<TestTypes> getTestList() throws RecordNotFoundException {
		List<TestTypes> testTypesList = testTypesRepo.findAll();
		if(!testTypesList.isEmpty()) {
			return testTypesList;
		}else {
			throw new RecordNotFoundException(ExceptionConstants.NO_RECORD_AVAILABLE);
		}
	}

	@Override
	public TestTypes getTestById(int id) throws RecordNotFoundException {
		Optional<TestTypes> testTypes = testTypesRepo.findById(id);
		if(testTypes.isPresent()) {
			return testTypes.get();
		}else {
			throw new RecordNotFoundException(ExceptionConstants.RECORD_NOT_FOUND+id);
		}
	}

	@Override
	public TestTypes createTest(TestTypes testType) throws SQLException {
		TestTypes createdTestTypes = testTypesRepo.save(testType);
		if(createdTestTypes != null) {
			return createdTestTypes;
		}
		throw new SQLException(ExceptionConstants.WRONG_REQUEST);
	}

	@Override
	public TestTypes deleteTest(int id) throws RecordNotFoundException {
		Optional<TestTypes> testType = testTypesRepo.findById(id);
		if(testType.isPresent()) {
			testTypesRepo.deleteById(id);
			return testType.get();
		}
		throw new RecordNotFoundException(ExceptionConstants.RECORD_NOT_FOUND+id);
	}

	@Override
	public void updateCount(TestTypes testTypes,int count) {
		Optional<TestTypes> currentTestCount = testTypesRepo.findById(testTypes.getId()); 
		if(currentTestCount.isPresent()) {
			testTypes = currentTestCount.get();
			testTypes.setQuestionCount(currentTestCount.get().getQuestionCount()+count);
			testTypesRepo.save(testTypes);
		}
	}
	
}
