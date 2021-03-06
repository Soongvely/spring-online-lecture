package kr.co.coduck.web.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.coduck.form.CouponAddForm;
import kr.co.coduck.form.CouponUpdateForm;
import kr.co.coduck.service.CouponService;
import kr.co.coduck.vo.Coupon;
import kr.co.coduck.vo.User;

@Controller
@RequestMapping("/admin")
public class CouponController {

   @Autowired
   private CouponService couponService;
   
   @GetMapping("/coupon.hta")
   public String coupon(Model model) throws Exception {
      List<Coupon> coupons = couponService.getCouponsByAdmin();
      model.addAttribute("coupons", coupons);
      model.addAttribute("size", coupons.size());
      
      return "admin/coupon";
   }
   
   @PostMapping("/addcoupon.hta")
   public String addNewCoupon(CouponAddForm form) throws Exception {
      Coupon coupon = new Coupon();      
      BeanUtils.copyProperties(form, coupon);
      if (form.getAmount() > 0) {
         coupon.setLimited("Y");
      }
      
      couponService.addNewCoupon(coupon);
      
      return "redirect:/admin/coupon.hta";
   }

   @PostMapping("/updatecoupon.hta")
   public String updateCouponByAdmin(CouponUpdateForm form, @RequestParam("couponno") int couponNo) throws Exception {
      Coupon coupon = couponService.getCouponByCouponNo(couponNo);
      BeanUtils.copyProperties(form, coupon);
      if (form.getAmount() > 0) {
         coupon.setLimited("Y");
      }
      
      couponService.updateCouponByAdmin(coupon);;
      
      return "redirect:/admin/coupon.hta";
   }
   
   @GetMapping("/couponDetails.hta")
   @ResponseBody
   public List<Coupon> couponDetails(HttpSession session){
      User user = (User)session.getAttribute("LU");
      List<Coupon> couponDetails = couponService.getCouponsByUserNo(user.getNo());
      return couponDetails;
   }
   
   @GetMapping("/couponDetail.hta")
   // Json 응답을 줄 때 필요한 어노테이션 @ResponseBody
   @ResponseBody
   public Coupon getCouponByCouponNo(@RequestParam("couponno") int couponNo) {
      Coupon couponDetail = couponService.getCouponByCouponNo(couponNo);
      
      return couponDetail;
   }
   
   @GetMapping("/deletecoupon.hta")
   public String deleteCoupon(@RequestParam("couponno") int couponNo) {
      couponService.deleteCoupon(couponNo);
      
      return "redirect:/admin/coupon.hta";
   }
   
   @GetMapping("/deletecheckcoupons.hta")
   public String deleteCheckCoupons(@RequestParam("couponno") int[] couponNos) {
      
      couponService.deleteCoupons(couponNos);
      
      return "redirect:/admin/coupon.hta";
   }
   
   @PostMapping("/insertCouponByAdmin.hta")
   public String insertCouponByAdmin(@RequestParam("couponno") int couponNo, @RequestParam("userno") int[] userNos) {
      
      couponService.insertCouponByAdmin(couponNo, userNos);
      
      return "redirect:/admin/coupon.hta";
   }
}