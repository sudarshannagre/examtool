package com.sud.exam.exception;

public enum ExceptionEnum {

	RECORDNOTFOUNDEXCEPTION("No record found for id : ");
	
	private String name;

	ExceptionEnum(String name) {
		this.name = name;
	}
	
}
