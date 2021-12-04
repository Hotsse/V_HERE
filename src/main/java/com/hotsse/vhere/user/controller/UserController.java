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

import com.hotsse.vhere.user.dto.UserDto;
import com.hotsse.vhere.user.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 로그인
	 * 
	 * @param id 아이디
	 * @param pw 패스워드
	 * @param session 세션
	 * @return {@link UserDto} 회원정보
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(
			@RequestParam(name = "id", required = true) String id
			, @RequestParam(name = "pw", required = true) String pw
			, HttpSession session) throws Exception {
		
		UserDto user = this.userService.getUser(id, pw);
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.build();
		}
		
		session.setAttribute("id", user.getId());
		session.setAttribute("name", user.getName());
		
		return ResponseEntity.ok(user);
	}
	
	/**
	 * 로그아웃
	 * 
	 * @param session 세션
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) throws Exception {
		
		session.invalidate();
		return ResponseEntity.ok(null);
	}
	
	/**
	 * 회원가입
	 * 
	 * @param user 회원정보
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/signup")
	public ResponseEntity<Void> insertUser(UserDto user) throws Exception {
		
		if(!this.userService.insertUser(user)) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok()
				.build();
	}
	
	/**
	 * 회원탈퇴
	 * 
	 * @param session 세션
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/signout")
	public ResponseEntity<Void> deleteUser(HttpSession session) throws Exception {
		
		String id = (String)session.getAttribute("id");
		
		if(id == null || !this.userService.deleteUser(id)) {
			return ResponseEntity.badRequest().build();
		}
		
		session.invalidate();
		
		return ResponseEntity.ok()
				.build();
	}
	
	/**
	 * 패스워드 변경
	 * 
	 * @param user 회원정보
	 * @param session 세션
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/password")
	public ResponseEntity<Void> updatePassword(
			@RequestBody UserDto userDto
			, HttpSession session) throws Exception {
		
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			return ResponseEntity.badRequest().build();
		}		
		userDto.setId(id);
		
		if(!this.userService.updatePassword(userDto)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok()
				.build();
	}
}
