package com.hotsse.vhere;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.hotsse.vhere.api.board.controller.BoardController;
import com.hotsse.vhere.api.board.service.BoardService;
import com.hotsse.vhere.api.image.service.ImageService;

@WebMvcTest(BoardController.class)
class VHereApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BoardService boardService;
	
	@MockBean
	private ImageService imageService;
	
	private MockHttpSession session;
	
	@BeforeEach
	void setUp() throws Exception {
		session = new MockHttpSession();
		session.setAttribute("id", "dkdlrja");
		session.setAttribute("name", "윤호세");
	}
	
	@Test
	void contextLoads() throws Exception {
		
		this.mockMvc.perform(get("/api/board").session(session))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
