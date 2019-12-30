package com.sud.exam.services;

import java.sql.SQLException;
import java.util.List;

import com.sud.exam.entity.TestTypes;
import com.sud.exam.exception.RecordNotFoundException;

public interface TestTypesService {
	
	public List<TestTypes> getTestList() throws RecordNotFoundException;
	
	public TestTypes getTestById(int id) throws RecordNotFoundException;
	
	public TestTypes createTest(TestTypes testType) throws SQLException;
	
	public TestTypes deleteTest(int id) throws RecordNotFoundException;
	
	public void updateCount(TestTypes testTypes,int count);

}
