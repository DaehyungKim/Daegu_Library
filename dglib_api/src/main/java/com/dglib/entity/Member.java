package com.dglib.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
	@Id
	private String id;

	private String pw;

	private String name;
	
	private int mno;
	
	private String gender;
	
	private LocalDate birthDate;
	private String phone;
	private String addr;
	private String email;
	private boolean checkSms;
	private boolean checkEmail;
	private boolean checkTerms;
	private int panalty;
	private LocalDate joinDate;
	
	private String kakao;
	
	@Enumerated(EnumType.STRING)
    private MemberRole role;
	
	@Enumerated(EnumType.STRING)
    private MemberState state;
	
	
	

}
