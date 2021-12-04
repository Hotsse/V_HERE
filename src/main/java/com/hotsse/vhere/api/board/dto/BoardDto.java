package com.hotsse.vhere.api.board.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.hotsse.vhere.api.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

	private int boardId;
	private String title;
	private String content;
	private double latitude;
	private double longitude;
	private int viewCnt;
	private String regId;
	private LocalDateTime regDtt;
	private List<Integer> imgIds;
	
	public Optional<List<Integer>> getImgIds() {
		return Optional.ofNullable(this.imgIds);
	}
	
	public BoardDto(Board board) {
		this.boardId = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.latitude = board.getLatitude();
		this.longitude = board.getLongitude();
		this.viewCnt = board.getViewCnt();
		this.regId = board.getRegId();
		this.regDtt = board.getRegDtt();
		
	}
}
