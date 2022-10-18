package com.hotsse.vhere.user.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotsse.vhere.user.dto.SecurityUser;
import com.hotsse.vhere.user.dto.UserDto;
import com.hotsse.vhere.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
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
	 * 패스워드 변경
	 * 
	 * @param userDto 회원정보
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/password")
	public ResponseEntity<Void> updatePassword(
			@RequestBody UserDto userDto) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) auth.getPrincipal();
		String id = user.getUsername();

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
	
	/**
	 * 회원탈퇴
	 * 
	 * @param response 응답
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/signout")
	public ResponseEntity<Void> deleteUser(HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) auth.getPrincipal();
		
		String id = user.getUsername();		
		if(id == null || !this.userService.deleteUser(id)) {
			return ResponseEntity.badRequest().build();
		}
		
		response.sendRedirect("/user/logout");
		return ResponseEntity.ok()
				.build();
	}
}
