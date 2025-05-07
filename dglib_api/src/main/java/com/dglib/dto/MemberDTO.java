package com.dglib.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberDTO extends User{
	
	public MemberDTO(String id, Collection<? extends GrantedAuthority> authorities) {
		super(id, null, authorities);
		// TODO Auto-generated constructor stub
	}

	private String id;
	


}
