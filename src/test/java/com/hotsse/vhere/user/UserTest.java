package com.hotsse.vhere.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotsse.vhere.user.entity.User;
import com.hotsse.vhere.user.repository.UserRepository;

@SpringBootTest
public class UserTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void test() throws Exception {
		User user = userRepository.findById("dkdlrja").get();
		System.out.println(user);
	}
}
