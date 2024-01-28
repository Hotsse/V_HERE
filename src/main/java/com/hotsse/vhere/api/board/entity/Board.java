package com.hotsse.vhere.api.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // H2 auto_increment
	@Column(name = "board_id")
	private Integer id;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(columnDefinition = "CLOB", nullable = false)
	private String content;
	
	@Column(columnDefinition = "DECIMAL", nullable = false)
	private double latitude;
	
	@Column(columnDefinition = "DECIMAL", nullable = false)
	private double longitude;
	
	private int viewCnt;

	private int likeCnt;
	
	private String regId;
	
	private LocalDateTime regDtt;
	
	@Column(columnDefinition = "CHAR", length = 1)
	private String useYn;
	
	public Board(String title, String content, double latitude, double longitude, String regId) {
		this.title = title;
		this.content = content;
		this.latitude = latitude;
		this.longitude = longitude;
		this.regId = regId;
	}
	
	@PrePersist
	public void preInsert() {
		this.viewCnt = 0;
		this.regDtt = LocalDateTime.now();
		this.useYn = "Y";
	}
}
