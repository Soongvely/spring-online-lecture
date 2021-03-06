package kr.co.coduck.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.coduck.dto.AdQnaDto;
import kr.co.coduck.dto.AdminAnswerDto;
import kr.co.coduck.dto.AdminQnaCriteria;
import kr.co.coduck.dto.AdminQnaDto;
import kr.co.coduck.form.AnswerForm;
import kr.co.coduck.service.AdminQnaService;
import kr.co.coduck.vo.AdQnaFile;
import kr.co.coduck.vo.Pagination;
import kr.co.coduck.vo.User;

@Controller
@RequestMapping("/admin")
public class AdminQnaController {

	@Autowired
	private AdminQnaService adminQnaService;
	
	@GetMapping("/qna.hta")
	public String qnaSearch(AdminQnaCriteria criteria, @RequestParam(value = "pageno", required = false, defaultValue = "1") int pageNo, Model model) {
		
		int totalCnt = adminQnaService.getQnaCntByCriteria(criteria);
		Pagination pagination = new Pagination(pageNo, totalCnt, 10, 5);
		
		criteria.setBeginIndex(pagination.getBeginIndex());
		 criteria.setEndIndex(pagination.getEndIndex()); 
	    List<AdminQnaDto> qnaList = adminQnaService.getQnaByCriteria(criteria);
	    
	    model.addAttribute("qnaList", qnaList); 
	    model.addAttribute("pagination", pagination);
	
		return "admin/qna";
	}
	
	@GetMapping("/qnadetail.hta")
	@ResponseBody
	public Map<String, Object> qnadetail(@RequestParam("qnaNo") int qnaNo) throws Exception {
		AdQnaDto qna = adminQnaService.getQnaByNo(qnaNo);
		AdminAnswerDto answer = adminQnaService.getAnswerByNo(qnaNo);
		List<AdQnaFile> qnaFiles = adminQnaService.getAdQnaFilesByQnaNo(qnaNo);
		
		Map<String, Object> qnaDetail = new HashMap<String, Object>();
		
		qnaDetail.put("qna", qna);
		qnaDetail.put("answer", answer);
		qnaDetail.put("qnaFiles", qnaFiles);
		
		return qnaDetail;
	}
	
	// 문의내역 답변하기 전에 문의내용 확인하기 (모달)
	@GetMapping("/beforeanswer.hta")
	@ResponseBody
	public AdQnaDto beforeAnswer(@RequestParam("qnaNo") int qnaNo) {
		return adminQnaService.getQnaByNo(qnaNo);
	}
	
	// 문의내역 답변하기
	@PostMapping("/addanswer.hta")
	public String addAnswer(AnswerForm form, @RequestParam("qnaNo") int qnaNo, HttpSession session) throws Exception {
		User user = (User)session.getAttribute("LU");
		AdQnaDto qna = adminQnaService.getQnaByNo(qnaNo);
		AdminAnswerDto answer = new AdminAnswerDto();
		answer.setUserNo(user.getNo());
		BeanUtils.copyProperties(form, answer);
			
		qna.setIsAnswered("Y");
		
		adminQnaService.updateQna(qna);
		adminQnaService.addAnswer(answer);
		
		return "redirect:/admin/qna.hta";
	}
}
