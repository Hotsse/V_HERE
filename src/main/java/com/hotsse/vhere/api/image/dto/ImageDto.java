package com.hotsse.vhere.api.image.dto;

import com.hotsse.vhere.api.image.entity.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {

	private int imgId;
	private int boardId;
	private String fileNm;
	private String filePath;
	private long fileSize;
	
	public ImageDto(Image image) {
		this.imgId = image.getId();
		this.boardId = image.getBoardId();
		this.fileNm = image.getFileNm();
		this.filePath = image.getFilePath();
		this.fileSize = image.getFileSize();
	}
}
