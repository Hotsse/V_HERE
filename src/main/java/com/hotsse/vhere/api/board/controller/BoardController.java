package com.hotsse.vhere.api.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hotsse.vhere.api.board.service.BoardService;
import com.hotsse.vhere.api.board.vo.BoardVO;
import com.hotsse.vhere.api.image.service.ImageService;
import com.hotsse.vhere.core.base.BaseController;

@RestController
@RequestMapping(value = "/api/board")
public class BoardController extends BaseController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<BoardVO>> getBoards(
			@RequestParam(name = "swLat", defaultValue = "-90.0") double swLat
			, @RequestParam(name = "swLng", defaultValue = "-180.0") double swLng
			, @RequestParam(name = "neLat", defaultValue = "90.0") double neLat
			, @RequestParam(name = "neLng", defaultValue = "180.0") double neLng) throws Exception {
		
		List<BoardVO> boards = this.boardService.getBoards(swLat, swLng, neLat, neLng);
		return ResponseEntity.ok(boards);
	}
	
	@GetMapping(value = "/{boardId}")
	public ResponseEntity<BoardVO> getBoard(
			@PathVariable(name = "boardId", required = true) int boardId) throws Exception {
		
		BoardVO board = this.boardService.getBoard(boardId);
		if(board != null) {
			List<Integer> imgIds = this.imageService.getImageIds(boardId);
			board.setImgIds(imgIds);
		}
		
		return ResponseEntity.ok(board);
	}
	
	@PostMapping
	public ResponseEntity<Void> insertBoard(
			BoardVO board
			, @RequestPart(name = "uploadFiles") List<MultipartFile> files
			, HttpSession session
			) throws Exception {
		
		String id = (String)session.getAttribute("id");
		board.setRegId(id);
		
		int boardId = this.boardService.insertBoard(board);
		
		for(MultipartFile file : files) {
			this.imageService.uploadImage(file, boardId);
		}
		
		return ResponseEntity.created(null).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> updateBoard(@RequestBody BoardVO board) throws Exception {
		if(!this.boardService.updateBoard(board)) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteBoard(int boardId) throws Exception {
		if(!this.boardService.deleteBoard(boardId)) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
