package com.sud.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sud.exam.entity.TestTypes;

@Repository
public interface TestTypesRepository extends JpaRepository<TestTypes, Integer>,PagingAndSortingRepository<TestTypes, Integer>{

}
