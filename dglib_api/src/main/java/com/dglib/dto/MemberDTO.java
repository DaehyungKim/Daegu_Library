package com.dglib.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.dglib.entity.MemberRole;

import lombok.Data;

@Data
public class MemberDTO extends User{
	private String id;
	private String pw;
	private String name;
	private String email;
	private int mno;
	private String gender;
	private LocalDate birthDate;
	private String phone;
	private String addr;
	
	
	public MemberDTO(String id, MemberRole role) {
		super(id, null, List.of(new SimpleGrantedAuthority("ROLE_"+role.name())));
	}

	
	
	


}


