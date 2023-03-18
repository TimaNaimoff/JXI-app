package edu.betty.JXIpet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class JxiPetApplication {

	public static void main(String[] args) {

		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String password="GaoQiang";
		String encodedPassword=passwordEncoder.encode(password);
		System.out.println(
		);
		System.out.println("Password is: "+password);
		System.out.println("Encoded password is: "+encodedPassword);
		System.out.println(
		);
		boolean isPasswordMatch=passwordEncoder.matches(password,encodedPassword);
		System.out.println(isPasswordMatch);
		SpringApplication.run(JxiPetApplication.class, args);
	}

}
