package com.hotsse.vhere.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotsse.vhere.user.service.UserService;
import com.hotsse.vhere.user.vo.UserVO;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserVO> login(
			@RequestParam(name = "id", required = true) String id
			, @RequestParam(name = "pw", required = true) String pw
			, HttpSession session) throws Exception {
		
		UserVO user = this.userService.getUser(id, pw);
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.build();
		}
		
		session.setAttribute("id", user.getId());
		session.setAttribute("name", user.getName());
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) throws Exception {
		
		session.invalidate();
		return ResponseEntity.ok(null);
	}
	
	@PostMapping
	public boolean insertUser(@RequestBody UserVO user) throws Exception {
		return this.userService.insertUser(user);
	}
	
	@DeleteMapping
	public boolean deleteUser(HttpSession session) throws Exception {
		
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			return false;
		}
		
		return this.userService.deleteUser(id);
	}
	
	@PutMapping(value = "/password")
	public boolean updatePassword(
			@RequestBody UserVO user
			, HttpSession session) throws Exception {
		
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			return false;
		}
		
		user.setId(id);		
		return this.userService.updatePassword(user);
	}
}
