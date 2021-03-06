package kr.co.coduck.service;

import java.util.List;

import kr.co.coduck.dto.AdminLectCriteria;
import kr.co.coduck.dto.AdminLectDto;
import kr.co.coduck.dto.AdminLessonDto;

public interface AdminLectService {

	List<AdminLectDto> getLectByCriteria(AdminLectCriteria adminLectCriteria);
	int getLectCntByCriteria(AdminLectCriteria adminLectCriteria);
	
	void confirmLect(int lectNo);
	void denyLect(int lectNo);
	
	List<AdminLessonDto> getLessonsByLectNo(int lectNo); 
}
