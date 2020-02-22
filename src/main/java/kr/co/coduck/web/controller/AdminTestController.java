package kr.co.coduck.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.coduck.dto.SearchTestDetailDto;
import kr.co.coduck.dto.SearchTestDto;
import kr.co.coduck.form.SearchTestFormByAdm;
import kr.co.coduck.service.AdminTestService;
import kr.co.coduck.service.CategoryService;
import kr.co.coduck.service.TestService;

@Controller
@RequestMapping("/admin")
public class AdminTestController {
	
	@Autowired
	private AdminTestService adminTestService; 
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/searchTestByAdm.hta")
	@ResponseBody
	public SearchTestDto searchTestByAdmin(SearchTestFormByAdm form){
		System.out.println("=============================");
		System.out.println("폼 조회 : " + form);
		System.out.println("=============================");
		System.out.println("관리자 시험 조회 : " + adminTestService.searchTestByAdm(form));
		
		SearchTestDto searchTestDto = new SearchTestDto();
		searchTestDto.setUpCategories(categoryService.getCatesByMainNo(20000));
		if(form.getMainCateNo() == -1) {
			searchTestDto.setDownCategories(categoryService.getTestCategoryByAdmin());
		} else {
			searchTestDto.setDownCategories(categoryService.getCatesByMainNo(form.getMainCateNo()));
		}
		if(form.getEpName().equals("")) {
			searchTestDto.setEps(testService.getAllTestEp());
		} else {
			searchTestDto.setEps(testService.getTestEpsByCateNo(form.getCateNo()));
		}
		searchTestDto.setSearchTestDetailDtos(adminTestService.searchTestByAdm(form));
		
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("searchTestDto : " + searchTestDto);
		return searchTestDto;
	}
	
	@GetMapping("/test.hta")
	public String test() {
		return "admin/test";
	}
}