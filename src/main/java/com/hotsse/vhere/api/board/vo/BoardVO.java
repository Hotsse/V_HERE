package com.hotsse.vhere.api.board.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	private LocalDateTime regDtt;
	private Integer thumbImgId;
	private List<Integer> imgIds;
	
	public Optional<List<Integer>> getImgIds() {
		return Optional.ofNullable(this.imgIds);
	}
}
