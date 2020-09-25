package com.hotsse.vhere.user.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotsse.vhere.user.dao.UserDao;
import com.hotsse.vhere.user.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 회원 정보 조회
	 * 
	 * @param id 아이디
	 * @param pw 패스워드
	 * @return {@link UserVO} 회원정보
	 * @throws Exception
	 */
	public UserVO getUser(String id, String pw) throws Exception {
		
		//SHA256 해시
		pw = DigestUtils.sha256Hex(pw);
		
		return this.userDao.getUser(id, pw);
	}
	
	/**
	 * 회원가입
	 * 
	 * @param user 회원정보
	 * @return {@link True} 성공 / {@link False} 실패
	 * @throws Exception
	 */
	public boolean insertUser(UserVO user) throws Exception {
		
		//SHA256 해시
		String sha256Pw = DigestUtils.sha256Hex(user.getPw());
		user.setPw(sha256Pw);
		
		return (this.userDao.insertUser(user) == 1);
	}
	
	/**
	 * 패스워드 변경
	 * 
	 * @param user 회원정보
	 * @return {@link True} 성공 / {@link False} 실패
	 * @throws Exception
	 */
	public boolean updatePassword(UserVO user) throws Exception {
		
		//SHA256 해시
		String sha256Pw = DigestUtils.sha256Hex(user.getPw());
		user.setPw(sha256Pw);
		
		return (this.userDao.updatePassword(user) == 1);
	}
	
	/**
	 * 회원탈퇴
	 * 
	 * @param id 아이디
	 * @return {@link True} 성공 / {@link False} 실패
	 * @throws Exception
	 */
	public boolean deleteUser(String id) throws Exception {
		return (this.userDao.deleteUser(id) == 1);
	}
}
