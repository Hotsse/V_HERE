package com.hotsse.vhere.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class User {
	
	@Id
	@Column(length = 50)
	private String id;
	
	@Column(columnDefinition = "CHAR", length = 60)
	private String pw;
	
	@Column(length = 50)
	private String name;
	
	@Column(columnDefinition = "CHAR", length = 1, nullable = false)
	private String useYn;
	
	public User(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	@PrePersist
	public void preInsert() {
		this.useYn = "Y";
	}
}
