package com.sud.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sud.exam.entity.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer>,PagingAndSortingRepository<Questions, Integer>{
	
//	@Query("")
//	public List<Questions> searchQuestionsByType(String type);

	@Query("from Questions where test_id=?1")
	public List<Questions> getQuestionsByType(int id);
}
