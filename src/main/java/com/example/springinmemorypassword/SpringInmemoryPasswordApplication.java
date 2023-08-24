package com.example.springinmemorypassword;

import com.example.springinmemorypassword.entity.User;
import com.example.springinmemorypassword.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringInmemoryPasswordApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringInmemoryPasswordApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception {
		final List<User> userList = Arrays.asList(User.builder().username("naveen").password(bCryptPasswordEncoder.encode("mishra")).role("ADMIN").build(),
				User.builder().username("chitresh").password(bCryptPasswordEncoder.encode("nirala")).role("USER").build(),
				User.builder().username("prabhakar").password(bCryptPasswordEncoder.encode("kanti")).role("USER").build());
		final List<User> users = userRepository.saveAll(userList);
		System.out.println(users);
	}
}
