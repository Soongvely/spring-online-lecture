package kr.co.coduck.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.co.coduck.dto.AdminQnaCriteria;
import kr.co.coduck.dto.AdminQnaDto;
import kr.co.coduck.vo.AdQna;
import kr.co.coduck.vo.AdQnaFile;

@Transactional
public interface AdminQnaService {
	List<AdQnaFile> getAdQnaFilesByQnaNo(int qnaNo);
	List<AdQna> getAdQnasByUserNo(int userNo);
	AdQna getAdQnaByNo(int no);
	List<AdminQnaDto> getQnaByCriteria(AdminQnaCriteria adminQnaCriteria);
	int getQnaCntByCriteria(AdminQnaCriteria adminQnaCriteria);
}
