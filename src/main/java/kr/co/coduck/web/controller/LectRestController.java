package kr.co.coduck.web.controller;

import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.coduck.dto.LectureCourseDto;
import kr.co.coduck.dto.LectureDto;
import kr.co.coduck.dto.LessonDto;
import kr.co.coduck.service.CategoryService;
import kr.co.coduck.service.LectService;
import kr.co.coduck.service.QuestionService;
import kr.co.coduck.vo.Answer;
import kr.co.coduck.vo.Category;
import kr.co.coduck.vo.LectureCriteria;
import kr.co.coduck.vo.Lesson;
import kr.co.coduck.vo.LessonHistory;
import kr.co.coduck.vo.Pagination;
import kr.co.coduck.vo.Question;
import kr.co.coduck.vo.User;

@RequestMapping("/lecture/api")
@RestController
public class LectRestController {

	private static Logger log = Logger.getLogger(LectRestController.class);

	@Autowired
	private LectService lectService;

	@Autowired
	private QuestionService questionService;
	// @RequestMapping(name = "/lecture",method = RequestMethod.GET)

	// 카테고리 및 조건별 강좌 조회
	@PostMapping("/searchLecture")
	public Map<String, Object> mainByCateNo(@RequestBody LectureCriteria cri) { // @RequestBody :폼으로 전송하고 Post 방식으로 받을 때

		// Pagination
		int totalCount = lectService.getLectureCountByCriteria(cri);

		Pagination pagination = new Pagination(cri.getPage(), totalCount, 16, 3);

		cri.setBeginIndex(pagination.getBeginIndex());
		cri.setEndIndex(pagination.getEndIndex());

		List<LectureCourseDto> lectures = lectService.getLectureByCriteria(cri);

		lectures.forEach(lecture -> {
			lecture.setLectureDto(lectService.getAllCountByLectureNo(lecture.getLecture().getNo()));
		});

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("lectures", lectures);
		model.put("beginPage", pagination.getBeginPage());
		model.put("endPage", pagination.getEndPage());
		model.put("totalPage", pagination.getTotalPagesCount());

		return model;
	}

	// 강좌: 조건별 질문 조회
	@GetMapping("/searchQuestion")
	public List<Question> questionByLectureNo(LectureCriteria cri) {

		return questionService.getQuestionByLectureNo(cri);
	}

	// 레슨: 조건별 질문 및 답변 조회
	@GetMapping("/searchLessonQuestion")
	public List<Question> questionByLessonNo(LectureCriteria cri) {

		return questionService.getQuestionByLessonNo(cri);
	}

	// 질문에 대한 답변
	@GetMapping("/getAnswer")
	public Answer getAnswerByQuestionNo(@RequestParam("questionNo") int questionNo) {

		return questionService.getAnswerByQuestionNo(questionNo);
	}

	// 레슨히스토리
	@GetMapping("/updateHistory")
	public LessonDto updateLessonHistory(LessonHistory lessonHistory, HttpSession session) {

		User user = (User) session.getAttribute("LU");
		lessonHistory.setUserNo(user.getNo());

		LessonHistory searchedLessonHistory = lectService.getLessonHistoryByLessonHistory(lessonHistory);

		if (searchedLessonHistory == null) {
			lectService.insertLessonHistory(lessonHistory);

		} else {
			Lesson lesson = lectService.getLessonByLessonNo(lessonHistory.getLessonNo());

			if (lesson.getTotalTime() * 0.9 < searchedLessonHistory.getAccumulateTime()) {
				searchedLessonHistory.setIsWatched("Y");
				lectService.updateLessonByLessonHistory(searchedLessonHistory);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userNo", user.getNo());
				map.put("lectureNo", searchedLessonHistory.getLectNo());

				return lectService.getProgressPercentInDashboard(map);

			} else {
				searchedLessonHistory.setAccumulateTime(searchedLessonHistory.getAccumulateTime() + 1);
				searchedLessonHistory.setLastTime(lessonHistory.getLastTime());
				lectService.updateLessonByLessonHistory(searchedLessonHistory);
			}

		}

		return null;
	}

	@GetMapping("/lessonPlayer")
	public Map<String, Object> lessonPlayer(LessonHistory lessonHistory, HttpSession session) {

		User user = (User) session.getAttribute("LU");
		lessonHistory.setUserNo(user.getNo());

		LessonHistory searchedLessonHistory = lectService.getLessonHistoryByLessonHistory(lessonHistory);
		Lesson lesson = lectService.getLessonByLessonNo(lessonHistory.getLessonNo());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("history", searchedLessonHistory);
		model.put("lesson", lesson);

		return model;
	}

	@GetMapping("/addQuestion")
	public Question addQuestion(@RequestParam("lessonNo") int lessonNo, @RequestParam("contents") String contents, HttpSession session) {
		
		Question question = new Question();
		
		User user = (User) session.getAttribute("LU");
		question.setUser(user);
		
		Lesson lesson = new Lesson();
		lesson.setNo(lessonNo);
		question.setLesson(lesson);
		question.setContents(contents);
		
		questionService.insertQuestion(question);
		
		Question searchedQuestion = questionService.getQuestionByQuestionNo(question.getNo());
		
		return searchedQuestion;
	}


}
