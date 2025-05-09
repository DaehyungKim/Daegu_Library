package com.dglib.entity.board;

import java.time.LocalDate;
import com.dglib.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class Qna_A {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ano;	//답변글 번호
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "qno", nullable = false)
	private Qna_Q qna_q;	//fk 질문글 번호
	
	@Column(nullable = false)
	private LocalDate createDate;	//등록일
	
	@Column(nullable = true)
	private LocalDate modifyDate;	//수정일	
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;	//내용
	
	@ManyToOne
	@JoinColumn(name = "memberId", nullable = false)
	Member member;	//회원id
}
