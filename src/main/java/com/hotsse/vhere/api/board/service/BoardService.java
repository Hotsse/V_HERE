package com.hotsse.vhere.api.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotsse.vhere.api.board.dao.BoardDao;
import com.hotsse.vhere.api.board.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVO> getBoards(double swLat, double swLng, double neLat, double neLng) throws Exception {
		return this.boardDao.getBoards(swLat, swLng, neLat, neLng);
	}
	
	public BoardVO getBoard(int boardId) throws Exception {
		return this.boardDao.getBoard(boardId);
	}
	
	public int insertBoard(BoardVO board) throws Exception {
		
		int boardId = this.boardDao.insertBoard(board);
		return boardId;
	}
	
	public boolean updateBoard(BoardVO board) throws Exception {
		return (this.boardDao.updateBoard(board) == 1) ? true : false;
	}
	
	public boolean deleteBoard(int boardId) throws Exception {
		return (this.boardDao.deleteBoard(boardId) == 1) ? true : false;
	}
}
