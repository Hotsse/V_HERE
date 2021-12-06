package com.hotsse.vhere.user.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotsse.vhere.user.dto.SecurityUser;
import com.hotsse.vhere.user.dto.UserDto;
import com.hotsse.vhere.user.entity.User;
import com.hotsse.vhere.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findAvailableById(username).orElse(null);
		if(user == null) {
			throw new UsernameNotFoundException(username + " is not avaliable username");
		}
		
		// 사용자 권한 정보 조회
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new SecurityUser(username, user.getPw(), user.getName(), authorities);
	}
	
	/**
	 * 회원가입
	 * 
	 * @param user 회원정보
	 * @return {@link True} 성공 / {@link False} 실패
	 * @throws Exception
	 */
	public boolean insertUser(UserDto userDto) throws Exception {
		
		User user = new User(userDto.getId(), passwordEncoder.encode(userDto.getPw()), userDto.getName());
		
		try {
			// TODO: PK 의 존재로 persist 가 아닌 merge 가 실행됨. reg_dtt 를 생성하거나 use_yn 으로 isNew 조건을 변경할 것 
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 패스워드 변경
	 * 
	 * @param user 회원정보
	 * @return {@link True} 성공 / {@link False} 실패
	 * @throws Exception
	 */
	@Transactional
	public boolean updatePassword(UserDto userDto) throws Exception {
		
		try {
			User user = userRepository.findById(userDto.getId()).get();
			user.setPw(passwordEncoder.encode(userDto.getPw()));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 회원탈퇴
	 * 
	 * @param id 아이디
	 * @return {@link True} 성공 / {@link False} 실패
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteUser(String id) throws Exception {
		
		try {
			User user = userRepository.findById(id).get();
			user.setUseYn("N");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
