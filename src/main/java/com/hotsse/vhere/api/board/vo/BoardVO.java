package com.hotsse.vhere.api.board.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {

	private int boardId;
	private String title;
	private String content;
	private double latitude;
	private double longitude;
	private int viewCnt;
	private String regId;
	private Date regDtt;
	private Integer thumbImgId;
	private List<Integer> imgIds;
}
