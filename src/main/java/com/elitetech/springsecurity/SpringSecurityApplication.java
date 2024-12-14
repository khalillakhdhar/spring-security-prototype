package com.elitetech.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elitetech.springsecurity.entity.UserInfo;
import com.elitetech.springsecurity.service.UserInfoService;

@SpringBootApplication
public class SpringSecurityApplication  {

	
	@Autowired
	UserInfoService userInfoService; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	

}
