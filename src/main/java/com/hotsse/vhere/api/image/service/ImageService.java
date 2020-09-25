package com.hotsse.vhere.api.image.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotsse.vhere.api.image.dao.ImageDao;
import com.hotsse.vhere.api.image.vo.ImageVO;
import com.hotsse.vhere.common.service.FileService;

@Service
public class ImageService {

	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private FileService fileService;
	
	public List<Integer> getImageIds(int boardId) throws Exception {
		return this.imageDao.getImageIds(boardId);
	}
	
	public List<ImageVO> getImages(int boardId) throws Exception {
		return this.imageDao.getImages(boardId);
	}
	
	public ImageVO getImage(int imgId) throws Exception {
		return this.imageDao.getImage(imgId);
	}
	
	public boolean uploadImage(MultipartFile file, int boardId) throws Exception {
		
		String fileNm = file.getOriginalFilename();
		long fileSize = file.getSize();
		String filePath = this.fileService.uploadFile(file);		
		
		ImageVO img = ImageVO.builder()
				.boardId(boardId)
				.fileNm(fileNm)
				.filePath(filePath)
				.fileSize(fileSize)
				.build();
		
		return this.insertImage(img);
	}
	
	private boolean insertImage(ImageVO img) throws Exception {
		return (this.imageDao.insertImage(img) == 1) ? true : false;
	}
	
	public boolean deleteImage(int imgId) throws Exception {
		return (this.imageDao.deleteImage(imgId) == 1) ? true : false;
	}
	
	public void downloadImage(int imgId
			, HttpServletResponse res) throws Exception {
		
		ImageVO img = this.getImage(imgId);		
		this.fileService.downloadImg(img, res);
	}
}
