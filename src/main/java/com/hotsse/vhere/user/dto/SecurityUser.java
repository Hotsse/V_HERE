package com.hotsse.vhere.user.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecurityUser implements UserDetails {

	private String username;
	private String password;
	private String nickname;
	
	private List<GrantedAuthority> authorities;
	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	public SecurityUser(String username, String password, String nickname, List<GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.authorities = authorities;
	}
}
