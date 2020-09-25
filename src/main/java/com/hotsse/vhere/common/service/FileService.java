package com.hotsse.vhere.common.service;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hotsse.vhere.api.image.vo.ImageVO;
import com.hotsse.vhere.core.base.BaseService;

@Service
public class FileService extends BaseService {

	@Value("${file.path}")
	private String storagePath;
	
	/**
	 * 파일 업로드
	 * 
	 * @param file 파일
	 * @return 파일경로
	 * @throws Exception
	 */
	public String uploadFile(MultipartFile file) throws Exception {
		
		// 파일 경로 생성
		String today = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMdd")); // 날짜별 디렉토리 분기		
		String uuid = UUID.randomUUID().toString(); // 파일명 암호화
		String filePath = String.format("/%s/%s", today, uuid);
		
		try {
			File fs = new File(this.storagePath + filePath);
			
			if(!fs.exists()) {
				
				if(fs.getParentFile().mkdirs()) {
					fs.createNewFile();
				}
				
				file.transferTo(fs);
			}
			else {
				throw new FileAlreadyExistsException(null);
			}
		}
		catch(Exception e) {
			return null;
		}
		
		return filePath;		
	}
	
	/**
	 * 이미지 뷰 다운로드
	 * 
	 * @param img 이미지 정보
	 * @param res Response 객체
	 * @throws Exception
	 */
	public void downloadImg(ImageVO img, HttpServletResponse res) throws Exception {
		
		if(img != null) {
			String filePath = this.storagePath + img.getFilePath();
			
			FileSystemResource imgFile = new FileSystemResource(filePath);
			
			String mediaType = MediaType.ALL_VALUE;
			if(img.getFileNm().contains(".jpg") || img.getFileNm().contains(".JPG")) {
				mediaType = MediaType.IMAGE_JPEG_VALUE;
			}
			else if(img.getFileNm().contains(".png") || img.getFileNm().contains(".PNG")) {
				mediaType = MediaType.IMAGE_PNG_VALUE;
			}
			else if(img.getFileNm().contains(".gif") || img.getFileNm().contains(".gif")) {
				mediaType = MediaType.IMAGE_GIF_VALUE;
			}
			
			res.setContentType(mediaType);
			StreamUtils.copy(imgFile.getInputStream(), res.getOutputStream());
		}
	}
}
