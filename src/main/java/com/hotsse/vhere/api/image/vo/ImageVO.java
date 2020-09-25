package com.hotsse.vhere.api.image.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ImageVO {

	private int imgId;
	private int boardId;
	private String fileNm;
	private String filePath;
	private long fileSize;
}
