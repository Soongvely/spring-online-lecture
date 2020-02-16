package kr.co.coduck.service;

import java.util.List;

import kr.co.coduck.vo.LectureCriteria;
import kr.co.coduck.vo.Question;

public interface QuestionService {
	
	List<Question> getQuestionByLectureNo(LectureCriteria cri);
}
