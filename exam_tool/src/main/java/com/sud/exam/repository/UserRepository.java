package com.sud.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sud.exam.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,PagingAndSortingRepository<User, Integer>{
	
	@Query("from User where name=?1 and password=?2")
	public User logedIn(String userName, String password);

}
