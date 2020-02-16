package kr.co.coduck.dao;

import java.util.List;

import kr.co.coduck.vo.LectureCriteria;
import kr.co.coduck.vo.Question;

public interface QuestionDao {

	List<Question> getQuestionByLectureNo(LectureCriteria cri);
	
}
