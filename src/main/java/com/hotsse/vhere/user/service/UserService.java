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
	
	public UserVO getUser(String id, String pw) throws Exception {
		
		//SHA256 해시
		pw = DigestUtils.sha256Hex(pw);
		
		return this.userDao.getUser(id, pw);
	}
	
	public boolean insertUser(UserVO user) throws Exception {
		
		//SHA256 해시
		String sha256Pw = DigestUtils.sha256Hex(user.getPw());
		user.setPw(sha256Pw);
		
		return (this.userDao.insertUser(user) == 1) ? true : false;
	}
	
	public boolean updatePassword(UserVO user) throws Exception {
		
		//SHA256 해시
		String sha256Pw = DigestUtils.sha256Hex(user.getPw());
		user.setPw(sha256Pw);
		
		return (this.userDao.updatePassword(user) == 1) ? true: false;
	}
	
	public boolean deleteUser(String id) throws Exception {
		return (this.userDao.deleteUser(id) == 1) ? true : false;
	}
}
