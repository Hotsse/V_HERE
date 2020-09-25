package com.hotsse.vhere.user.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotsse.vhere.core.base.BaseDao;
import com.hotsse.vhere.user.vo.UserVO;

@Repository
public class UserDao extends BaseDao {

	/**
	 * 회원 정보 조회
	 * 
	 * @param id 아이디
	 * @param pw 패스워드
	 * @return {@link UserVO} 회원정보
	 * @throws Exception
	 */
	public UserVO getUser(String id, String pw) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("pw", pw);
		
		return this.sqlSession.selectOne("user.getUser", param);
	}
	
	/**
	 * 회원가입
	 * 
	 * @param user 회원정보
	 * @return
	 * @throws Exception
	 */
	public int insertUser(UserVO user) throws Exception {
		return this.sqlSession.insert("user.insertUser", user);
	}
	
	/**
	 * 패스워드 변경
	 * 
	 * @param user 회원정보
	 * @return
	 * @throws Exception
	 */
	public int updatePassword(UserVO user) throws Exception {
		return this.sqlSession.update("user.updatePassword", user);
	}
	
	/**
	 * 회원탈퇴
	 * 
	 * @param id 아이디
	 * @return
	 * @throws Exception
	 */
	public int deleteUser(String id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		
		return this.sqlSession.update("user.deleteUser", param);
	}
}
