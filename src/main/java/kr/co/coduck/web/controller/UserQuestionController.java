package kr.co.coduck.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.coduck.dto.UserQuestionDto;
import kr.co.coduck.form.userQuestionToAdm;
import kr.co.coduck.service.AdminQnaService;
import kr.co.coduck.service.UserQuestionService;
import kr.co.coduck.vo.AdQna;
import kr.co.coduck.vo.Pagination;
import kr.co.coduck.vo.User;

@Controller
@RequestMapping("/userquestion")
public class UserQuestionController {

	@Autowired
	private UserQuestionService userQuestionService;
	
	@Autowired
	private AdminQnaService adminQnaService;
	
	//1:1문의 상세조회
	@GetMapping("/userQnaToAdmDetail.hta")
	public String detailQuestionToAdm(Model model, @RequestParam("qnaNo") int qnaNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qna", userQuestionService.getAdQnaByNo(qnaNo));
		map.put("images", userQuestionService.getAdQnaFilesByQnaNo(qnaNo));
		map.put("ans", adminQnaService.getAnswerByNo(qnaNo));
		System.out.println("=====================================");
		System.out.println("qnaNo :" + qnaNo);
		System.out.println("images"+ userQuestionService.getAdQnaFilesByQnaNo(qnaNo));
		model.addAttribute("map", map);
		System.out.println("sdfdsfdsfdsfdsf"+map.get("qna"));
		
		return "user/userQnaToAdmDetail";
	}
	
	//1:1문의 목록 조회
	@GetMapping("/userqnatoadmlist.hta")
	public String showQuestionListToAdm(Model model, HttpSession session,
			@RequestParam(value = "pageno", required = false, defaultValue = "1") int pageNo) {
		
		Map<String, Integer> criteria = new HashMap<String, Integer>();
		User user = (User)session.getAttribute("LU");
		
		//전체 데이터 불러오기(전체데이터 개수를 pagination에 넘기기 위해)
		int cnt = userQuestionService.getAllAdQnaCntByUserNo(user.getNo());
		Pagination pagination = new Pagination(pageNo, cnt, 10, 5);
		
		criteria.put("userNo", user.getNo());
		criteria.put("beginIndex", pagination.getBeginIndex());
		criteria.put("endIndex", pagination.getEndIndex());
		List<AdQna> qnas = userQuestionService.getAdQnasByCriteria(criteria);
		
		model.addAttribute("qnas", qnas);
		model.addAttribute("pagination", pagination);
		
		return "user/userQnaToAdmList";
	}
	
	//관리자에게 1:1질문
    @PostMapping("/questionToAdm.hta")
    public String insertAdQna(userQuestionToAdm form, HttpSession session, Model model) throws IOException {
        User user = (User)session.getAttribute("LU");
        
        int qnaNo = userQuestionService.insertAdQna(form, user.getNo());
        model.addAttribute("qna", userQuestionService.getAdQnaByNo(qnaNo));
        model.addAttribute("imgs", userQuestionService.getAdQnaFilesByQnaNo(qnaNo));
        model.addAttribute("qnas", userQuestionService.getAdQnasByUserNo(user.getNo()));
        return "user/userQnaToAdmList";
    }
	
	@GetMapping("/myquestionlist.hta")
	public String myquestionlist(HttpSession session, Model model) {
		User user = (User)session.getAttribute("LU");
		List<UserQuestionDto> userQuestionList = userQuestionService.getUserQuestionListByUserNo(user.getNo());
		model.addAttribute("userQuestionList", userQuestionList);
		return "user/myquestionlist";
	}
	
	@GetMapping("/userquestion.hta")
	@ResponseBody
	public List<UserQuestionDto> userquestion(HttpSession session, Model model, @RequestParam(value = "sort", required = false, defaultValue = "1") int sort) {
		User user = (User)session.getAttribute("LU");
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("userNo", user.getNo());
		criteria.put("sort", sort);
		List<UserQuestionDto> userQuestionListNY = userQuestionService.searchQuestion(criteria);
		return userQuestionListNY;
		
	}
}
