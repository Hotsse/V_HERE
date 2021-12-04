package com.hotsse.vhere.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.hotsse.vhere.user.dto.UserDto;
import com.hotsse.vhere.user.entity.User;
import com.hotsse.vhere.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	/**
	 * 회원 정보 조회
	 * 
	 * @param id 아이디
	 * @param pw 패스워드
	 * @return {@link UserDto} 회원정보
	 * @throws Exception
	 */
	public UserDto getUser(String id, String pw) throws Exception {
		
		Optional<User> user = userRepository.findAvailableById(id);
		
		if(user.isEmpty() 
				|| !DigestUtils.sha256Hex(pw).equalsIgnoreCase(user.get().getPw())) {
			return null;
		}
		return user.map(UserDto::new).get();
	}
	
	/**
	 * 회원가입
	 * 
	 * @param user 회원정보
	 * @return {@link True} 성공 / {@link False} 실패
	 * @throws Exception
	 */
	public boolean insertUser(UserDto userDto) throws Exception {
		
		//SHA256 해시
		String sha256Pw = DigestUtils.sha256Hex(userDto.getPw());		
		User user = new User(userDto.getId(), sha256Pw, userDto.getName());
		
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
			user.setPw(DigestUtils.sha256Hex(userDto.getPw())); //SHA256 해시
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
