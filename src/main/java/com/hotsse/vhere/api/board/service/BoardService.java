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
	
	/**
	 * 게시글 리스트 조회
	 * 
	 * @param swLat 남서경도
	 * @param swLng 남서위도
	 * @param neLat 북동경도
	 * @param neLng 북동위도
	 * @return {@link List} 게시글 리스트
	 * @throws Exception
	 */
	public List<BoardVO> getBoards(double swLat, double swLng, double neLat, double neLng) throws Exception {
		return this.boardDao.getBoards(swLat, swLng, neLat, neLng);
	}
	
	/**
	 * 게시글 조회
	 * 
	 * @param boardId 게시글번호
	 * @return {@link BoardVO} 게시글
	 * @throws Exception
	 */
	public BoardVO getBoard(int boardId) throws Exception {
		return this.boardDao.getBoard(boardId);
	}
	
	/**
	 * 게시글 생성
	 * 
	 * @param board 게시글
	 * @return 생성된 게시글번호
	 * @throws Exception
	 */
	public int insertBoard(BoardVO board) throws Exception {
		int boardId = this.boardDao.insertBoard(board);
		return boardId;
	}
	
	/**
	 * 게시글 수정
	 * 
	 * @param board 게시글
	 * @return true = 성공 / false = 실패
	 * @throws Exception
	 */
	public boolean updateBoard(BoardVO board) throws Exception {
		return (this.boardDao.updateBoard(board) == 1);
	}
	
	/**
	 * 게시글 삭제
	 * 
	 * @param boardId 게시글번호
	 * @return true = 성공 / false = 실패
	 * @throws Exception
	 */
	public boolean deleteBoard(int boardId) throws Exception {
		return (this.boardDao.deleteBoard(boardId) == 1);
	}
}
