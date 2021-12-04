package com.hotsse.vhere.api.board.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hotsse.vhere.api.board.dto.BoardDto;
import com.hotsse.vhere.api.board.entity.Board;
import com.hotsse.vhere.api.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
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
	public List<BoardDto> getBoards(double swLat, double swLng, double neLat, double neLng) throws Exception {		
		return boardRepository.findAllByCoordinate(swLat, swLng, neLat, neLng)
			.stream()
			.map(BoardDto::new)
			.collect(Collectors.toList());
	}
	
	/**
	 * 게시글 조회
	 * 
	 * @param boardId 게시글번호
	 * @return {@link BoardDto} 게시글
	 * @throws Exception
	 */
	public BoardDto getBoard(int boardId) throws Exception {		
		return boardRepository.findById(boardId)
			.map(BoardDto::new)
			.orElse(null);
	}
	
	/**
	 * 게시글 생성
	 * 
	 * @param board 게시글
	 * @return 생성된 게시글번호
	 * @throws Exception
	 */
	public int insertBoard(BoardDto boardDto) throws Exception {		
		Board board = new Board(
				boardDto.getTitle()
				, boardDto.getContent()
				, boardDto.getLatitude()
				, boardDto.getLongitude()
				, boardDto.getRegId());		
		Board newBoard = boardRepository.save(board);
		return newBoard.getId();
	}
	
	/**
	 * 게시글 수정
	 * 
	 * @param board 게시글
	 * @return true = 성공 / false = 실패
	 * @throws Exception
	 */
	@Transactional
	public boolean updateBoard(BoardDto boardDto) throws Exception {
		
		try {
			Board board = boardRepository.findById(boardDto.getBoardId()).get();		
			board.setTitle(boardDto.getTitle());
			board.setContent(boardDto.getContent());
			board.setLatitude(boardDto.getLatitude());
			board.setLongitude(boardDto.getLongitude());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 게시글 삭제
	 * 
	 * @param boardId 게시글번호
	 * @return true = 성공 / false = 실패
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteBoard(int boardId) throws Exception {
		
		try {
			Board board = boardRepository.findById(boardId).get();
			board.setUseYn("N");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
