package com.hotsse.vhere.api.image.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotsse.vhere.api.image.dto.ImageDto;
import com.hotsse.vhere.api.image.entity.Image;
import com.hotsse.vhere.api.image.repository.ImageRepository;
import com.hotsse.vhere.common.service.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	private final FileService fileService;
	
	/**
	 * 이미지번호 리스트 조회
	 * 
	 * @param boardId 게시글번호
	 * @return {@link List} 이미지번호 리스트
	 * @throws Exception
	 */
	public List<Integer> getImageIds(int boardId) throws Exception {
		return imageRepository.findAvailableByBoardId(boardId)
			.stream()
			.map(image -> image.getId())
			.collect(Collectors.toList());
	}
	
	/**
	 * 이미지 리스트 조회
	 * @param boardId 게시글번호
	 * @return {@link List} 이미지 리스트
	 * @throws Exception
	 */
	public List<ImageDto> getImages(int boardId) throws Exception {		
		return imageRepository.findAvailableByBoardId(boardId)
				.stream()
				.map(ImageDto::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * 이미지 조회
	 * 
	 * @param imgId 이미지번호
	 * @return {@link ImageDto} 이미지
	 * @throws Exception
	 */
	public ImageDto getImage(int imgId) throws Exception {		
		return imageRepository.findById(imgId)
				.map(ImageDto::new)
				.orElse(null);
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
		
		ImageDto img = ImageDto.builder()
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
	private boolean insertImage(ImageDto imgDto) throws Exception {
		
		try {
			Image img = new Image(
					imgDto.getBoardId()
					, imgDto.getFileNm()
					, imgDto.getFilePath()
					, imgDto.getFileSize());
			imageRepository.save(img);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 이미지 정보 삭제
	 * @param imgId 이미지번호
	 * @return true = 성공 / false = 삭제
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteImage(int imgId) throws Exception {
		
		try {
			Image image = imageRepository.findById(imgId).get();
			image.setUseYn("N");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 이미지 뷰 다운로드
	 * @param imgId 이미지번호
	 * @param res Response 객체
	 * @throws Exception
	 */
	public void downloadImage(int imgId
			, HttpServletResponse res) throws Exception {
		
		ImageDto img = this.getImage(imgId);		
		this.fileService.downloadImg(img, res);
	}
}
