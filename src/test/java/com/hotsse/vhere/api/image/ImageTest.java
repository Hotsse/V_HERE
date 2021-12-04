package com.hotsse.vhere.api.image;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotsse.vhere.api.image.repository.ImageRepository;

@SpringBootTest
@Transactional
public class ImageTest {

	@Autowired
	private ImageRepository imageRepository;
	
	@Test
	void test() throws Exception {
		int boardId = 481;
		imageRepository.findAvailableByBoardId(boardId);
	}
	
}
