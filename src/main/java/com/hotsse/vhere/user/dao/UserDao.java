package com.hotsse.vhere.user.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotsse.vhere.core.base.BaseDao;
import com.hotsse.vhere.user.vo.UserVO;

@Repository
public class UserDao extends BaseDao {

	public UserVO getUser(String id, String pw) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("pw", pw);
		
		return sqlSession.selectOne("user.getUser", param);
	}
	
	public int insertUser(UserVO user) throws Exception {
		return sqlSession.insert("user.insertUser", user);
	}
	
	public int updatePassword(UserVO user) throws Exception {
		return sqlSession.update("user.updatePassword", user);
	}
	
	public int deleteUser(String id) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		
		return sqlSession.update("user.deleteUser", param);
	}
}
