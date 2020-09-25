package com.hotsse.vhere.api.image.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotsse.vhere.api.image.service.ImageService;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
	
	@Autowired
	private ImageService imageService;

	/**
	 * 이미지 보기
	 * 
	 * @param imgId 이미지번호
	 * @param res Response 객체
	 * @throws Exception
	 */
	@GetMapping(value = "/{imgId}")
	public void downloadImage(
			@PathVariable(name = "imgId", required = true) int imgId
			, HttpServletResponse res) throws Exception {
		
		this.imageService.downloadImage(imgId, res);
	}
}
