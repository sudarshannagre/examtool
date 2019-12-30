package com.sud.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sud.exam.communication.EmailService;
import com.sud.exam.security.SHASecurity;

@SpringBootApplication
public class ExamToolApplication /* implements CommandLineRunner */{

	public static void main(String[] args) {
		SpringApplication.run(ExamToolApplication.class, args);
	}

	/*
	 * @Autowired EmailService emailService;
	 */
	
	/*
	 * @Value("${password.string}") private String profileACTIVE;
	 * 
	 * @Autowired private SHASecurity security;
	 * 
	 * @Override public void run(String... args) throws Exception {
	 * 
	 * //emailService.sendMail("sudarshan.nagre@yash.com", "testing",
	 * "testing mail");
	 * System.out.println("Password : "+security.getSHA256SecuredPassword("ram@123")
	 * );
	 * 
	 * }
	 */
	
}
