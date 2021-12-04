package com.hotsse.vhere.api.image.entity;

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
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // H2 auto_increment
	@Column(name = "img_id")
	private Integer id;
	
	@Column(nullable = false)
	private int boardId;
	
	@Column(length = 100, nullable = false)
	private String fileNm;
	
	@Column(length = 500, nullable = false)
	private String filePath;
	
	@Column(nullable = false)
	private long fileSize;
	
	@Column(columnDefinition = "CHAR", length = 1, nullable = false)
	private String useYn;
	
	public Image(int boardId, String fileNm, String filePath, long fileSize) {
		this.boardId = boardId;
		this.fileNm = fileNm;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
	
	@PrePersist
	public void preInsert() {
		this.useYn = "Y";
	}
}
