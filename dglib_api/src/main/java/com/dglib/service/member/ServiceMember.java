package com.dglib.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.dglib.dto.member.MemberSeaerchByMnoDTO;

public interface ServiceMember {
	
	Page<MemberSeaerchByMnoDTO> searchByMno(String mno, Pageable pageable);
	
	

}
