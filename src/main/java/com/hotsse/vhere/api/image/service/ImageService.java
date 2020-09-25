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
	
	/**
	 * 이미지번호 리스트 조회
	 * 
	 * @param boardId 게시글번호
	 * @return {@link List} 이미지번호 리스트
	 * @throws Exception
	 */
	public List<Integer> getImageIds(int boardId) throws Exception {
		return this.imageDao.getImageIds(boardId);
	}
	
	/**
	 * 이미지 리스트 조회
	 * @param boardId 게시글번호
	 * @return {@link List} 이미지 리스트
	 * @throws Exception
	 */
	public List<ImageVO> getImages(int boardId) throws Exception {
		return this.imageDao.getImages(boardId);
	}
	
	/**
	 * 이미지 조회
	 * 
	 * @param imgId 이미지번호
	 * @return {@link ImageVO} 이미지
	 * @throws Exception
	 */
	public ImageVO getImage(int imgId) throws Exception {
		return this.imageDao.getImage(imgId);
	}
	
	/**
	 * 이미지 업로드
	 * 
	 * @param file 이미지파일
	 * @param boardId 게시글번호
	 * @return true = 성공 / false = 실패
	 * @throws Exception
	 */
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
	
	/**
	 * 이미지 정보 생성
	 * @param img 이미지
	 * @return true = 성공 / false = 실패
	 * @throws Exception
	 */
	private boolean insertImage(ImageVO img) throws Exception {
		return (this.imageDao.insertImage(img) == 1);
	}
	
	/**
	 * 이미지 정보 삭제
	 * @param imgId 이미지번호
	 * @return true = 성공 / false = 삭제
	 * @throws Exception
	 */
	public boolean deleteImage(int imgId) throws Exception {
		return (this.imageDao.deleteImage(imgId) == 1);
	}
	
	/**
	 * 이미지 뷰 다운로드
	 * @param imgId 이미지번호
	 * @param res Response 객체
	 * @throws Exception
	 */
	public void downloadImage(int imgId
			, HttpServletResponse res) throws Exception {
		
		ImageVO img = this.getImage(imgId);		
		this.fileService.downloadImg(img, res);
	}
}
