package com.hotsse.vhere.api.board.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hotsse.vhere.api.board.dto.BoardDto;
import com.hotsse.vhere.api.board.service.BoardService;
import com.hotsse.vhere.api.image.service.ImageService;
import com.hotsse.vhere.core.base.BaseController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/board")
@RequiredArgsConstructor
public class BoardController extends BaseController {

	private final BoardService boardService;
	
	private final ImageService imageService;
	
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
	@GetMapping
	public ResponseEntity<List<BoardDto>> getBoards(
			@RequestParam(name = "swLat", defaultValue = "-90.0") double swLat
			, @RequestParam(name = "swLng", defaultValue = "-180.0") double swLng
			, @RequestParam(name = "neLat", defaultValue = "90.0") double neLat
			, @RequestParam(name = "neLng", defaultValue = "180.0") double neLng) throws Exception {
		
		List<BoardDto> boards = this.boardService.getBoards(swLat, swLng, neLat, neLng);
		return ResponseEntity.ok(boards);
	}
	
	/**
	 * 게시글 조회
	 * 
	 * @param boardId 게시글번호
	 * @return {@link BoardDto} 게시글
	 * @throws Exception
	 */
	@GetMapping(value = "/{boardId}")
	public ResponseEntity<BoardDto> getBoard(
			@PathVariable(name = "boardId", required = true) int boardId) throws Exception {
		
		BoardDto board = Optional.ofNullable(this.boardService.getBoard(boardId))
				.map(b -> {
					try {
						b.setImgIds(this.imageService.getImageIds(boardId));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return b;
				})
				.orElseGet(null);
		
		return ResponseEntity.ok(board);
	}
	
	/**
	 * 게시글 생성
	 * 
	 * @param board 게시글
	 * @param files 첨부파일들(이미지)
	 * @param session 세션
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> insertBoard(
			BoardDto boardDto
			, @RequestPart(name = "uploadFiles") List<MultipartFile> files
			, HttpSession session
			) throws Exception {
		
		String id = (String)session.getAttribute("id");
		boardDto.setRegId(id);
		
		int boardId = this.boardService.insertBoard(boardDto);
		
		for(MultipartFile file : files) {
			this.imageService.uploadImage(file, boardId);
		}
		
		return ResponseEntity.created(null).build();
	}
	
	/**
	 * 게시글 수정
	 * 
	 * @param board 게시글
	 * @return
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Void> updateBoard(@RequestBody BoardDto board) throws Exception {
		if(!this.boardService.updateBoard(board)) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(null);
	}
	
	/**
	 * 게시글 삭제
	 * 
	 * @param boardId 게시글번호
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/{boardId}")
	public ResponseEntity<Void> deleteBoard(
			@PathVariable(name = "boardId", required = true) int boardId) throws Exception {
		if(!this.boardService.deleteBoard(boardId)) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
