package com.hotsse.vhere.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hotsse.vhere.user.entity.User;
import com.hotsse.vhere.user.repository.UserRepository;

@SpringBootTest
public class UserTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() throws Exception {
		User user = userRepository.findById("dkdlrja").get();
		System.out.println(user);
	}
	
	@Test
	void encodeTest() throws Exception {
		
		final String raw = "test1234";
		
		final String r1 = passwordEncoder.encode(raw);
		
		System.out.println(r1);
		System.out.println(passwordEncoder.matches(raw, r1));
	}
}
