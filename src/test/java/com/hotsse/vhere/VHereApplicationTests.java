package com.hotsse.vhere;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotsse.vhere.api.board.service.BoardService;
import com.hotsse.vhere.api.board.vo.BoardVO;
import com.hotsse.vhere.api.image.service.ImageService;
import com.hotsse.vhere.api.image.vo.ImageVO;

@SpringBootTest
class VHereApplicationTests {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ImageService imageService;

	@Test
	void contextLoads() throws Exception {
		
		List<ImageVO> images = this.imageService.getImages(2);
		
		System.out.println(images.toString());
	}

}
