package com.dglib.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;


@Data
public class MemberDTO extends User{
	private static final long serialVersionUID = 1L;
	
	private String mid;
	private String pw;
	private String name;
	private String email;
	private String roleName;
	private int mno;
	
	public MemberDTO(String mid, String pw, String name, String email, String roleName) {
		super(mid, pw, List.of(new SimpleGrantedAuthority("ROLE_"+roleName)));
		this.mid = mid;
		this.name = name;
		this.email = email;
		this.roleName = roleName;
	}

	public Map<String, Object> getClaims(){
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("mid", mid);
		dataMap.put("name", name);
		dataMap.put("email", email);
		dataMap.put("roleName", roleName);
		
		return dataMap;
	}
	

}


