package kr.co.coduck.service;

import java.util.List;

import kr.co.coduck.dto.LikeLectListDto;

public interface LikeLectService {

	void insertLikeLect(int userNo, int lectNo);
	List<LikeLectListDto> getLikeLectsByUserNo(int userNo);
}
