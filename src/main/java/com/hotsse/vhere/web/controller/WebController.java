package com.hotsse.vhere.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotsse.vhere.core.base.BaseController;

@Controller
public class WebController extends BaseController {

	@GetMapping(value = "")
	public String index() throws Exception {
		return "redirect:/board/list";
	}
	
	@GetMapping(value = "/user/login")
	public String userLogin() throws Exception {
		return "pages/user/login";
	}
	
	@GetMapping(value = "/board/list")
	public String boardList() throws Exception {
		return "pages/board/list";
	}
	
	@GetMapping(value = "/board/write")
	public String boardWrite() throws Exception {
		return "pages/board/write";
	}
}
