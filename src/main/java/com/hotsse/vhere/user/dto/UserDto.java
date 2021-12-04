package com.hotsse.vhere.user.dto;

import com.hotsse.vhere.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String id;
	
	private String pw;
	
	private String name;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();	
	}
}
