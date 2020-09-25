package com.hotsse.vhere.api.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotsse.vhere.api.board.vo.BoardVO;
import com.hotsse.vhere.core.base.BaseDao;

@Repository
public class BoardDao extends BaseDao {

	public List<BoardVO> getBoards(double swLat, double swLng, double neLat, double neLng) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("swLat", swLat);
		param.put("swLng", swLng);
		param.put("neLat", neLat);
		param.put("neLng", neLng);		
		
		return sqlSession.selectList("board.getBoards", param);
	}
	
	public BoardVO getBoard(int boardId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardId", boardId);
		
		return sqlSession.selectOne("board.getBoard", param);
	}
	
	public int insertBoard(BoardVO board) throws Exception {
		
		this.sqlSession.insert("board.insertBoard", board);
		
		return board.getBoardId();
	}
	
	public int updateBoard(BoardVO board) throws Exception {
		return sqlSession.update("board.updateBoard", board);
	}
	
	public int deleteBoard(int boardId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardId", boardId);
		
		return sqlSession.update("board.deleteBoard", param);
	}
}
