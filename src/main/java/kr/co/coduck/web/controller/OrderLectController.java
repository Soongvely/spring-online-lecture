package kr.co.coduck.web.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.coduck.dto.OrderLectDetailListDto;
import kr.co.coduck.dto.OrderLectListDto;
import kr.co.coduck.service.CartLectService;
import kr.co.coduck.service.LectService;
import kr.co.coduck.service.OrderLectService;
import kr.co.coduck.service.UserService;
import kr.co.coduck.vo.User;

@Controller
@RequestMapping("/order")
public class OrderLectController {

	@Autowired
	private CartLectService cartLectService;
	@Autowired
	private LectService lectService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderLectService orderLectService;
	
	@PostMapping("/orderlects.hta")
	public String orderLect(@RequestParam(value = "choicecouponno", required = false, defaultValue = "0") Integer couponNo, HttpSession session,
							@RequestParam("lectno") int[] lectNos,
							@RequestParam("lecttotalprices") int lectTotalPrice,
							@RequestParam("bankno") String bankNo) {
		//System.out.println(couponNo);
		System.out.println(lectNos.length);
		System.out.println(lectTotalPrice);
		System.out.println(bankNo);
		User user = (User)session.getAttribute("LU");
		if(!user.getBankNumber().equals(bankNo)) {
			return "redirect:/order/orderlectform.hta?error=fail";
		}
		if (couponNo == 0) {
			couponNo = null;
		}
		orderLectService.insertOrderLect(user.getNo(), couponNo, lectNos, lectTotalPrice, bankNo);
		
		return "redirect:/home.hta";
	}
	
	@GetMapping("/userorderlectlist.hta")
	public String userOrderLectList(HttpSession session, Model model) {
		User user = (User)session.getAttribute("LU");
		List<OrderLectListDto> userOrderLists = orderLectService.getOrderLectListByUserNo(user.getNo());
		model.addAttribute("userOrderLists", userOrderLists);
		return "order/userorderlectlist";
	}
	
	@GetMapping("/orderLectDetail.hta")
	@ResponseBody
	public List<OrderLectDetailListDto> userOrderLectDetails(@RequestParam("orderno") int orderNo){
		List<OrderLectDetailListDto> userOrderLectDetails = orderLectService.getOrderLectInfoByOrderNo(orderNo);
		return userOrderLectDetails;
	}
		
	
	

	
}
