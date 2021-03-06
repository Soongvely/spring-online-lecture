package kr.co.coduck.service;

import java.util.List;

import kr.co.coduck.vo.Answer;
import kr.co.coduck.vo.LectureCriteria;
import kr.co.coduck.vo.Question;

public interface QuestionService {
	
	List<Question> getQuestionByLectureNo(LectureCriteria cri);
	List<Question> getQuestionByLessonNo(LectureCriteria cri);

	Answer getAnswerByQuestionNo(int questionNo);
	
	void insertQuestion(Question question);
	Question getQuestionByQuestionNo(int questionNo);
	
	List<Question> getQuestionByRecent(int lectureNo);
}
