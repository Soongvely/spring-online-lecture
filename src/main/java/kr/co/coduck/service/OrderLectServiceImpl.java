package kr.co.coduck.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.coduck.dao.CouponDao;
import kr.co.coduck.dao.LectCartDao;
import kr.co.coduck.dao.LectDao;
import kr.co.coduck.dao.OrderLectDao;
import kr.co.coduck.dao.UserDao;
import kr.co.coduck.dto.OrderLectDetailListDto;
import kr.co.coduck.dto.OrderLectListDto;
import kr.co.coduck.vo.OrdLect;
import kr.co.coduck.vo.OrderLectInfo;
import kr.co.coduck.vo.User;

@Service
public class OrderLectServiceImpl implements OrderLectService{
   
   @Autowired
   private UserDao userDao;
   @Autowired
   private LectCartDao lectCartDao;
   @Autowired
   private CouponDao couponDao;
   @Autowired
   private OrderLectDao orderLectDao;
   
   @Override
   public void insertOrderLect(int userNo, Integer couponNo, int[] lectNos, int lectTotalPrice, String bankNo) {
      System.out.println(lectNos.length);
      OrdLect ordLect = new OrdLect();
      ordLect.setUserNo(userNo);
      ordLect.setTotalPrice(lectTotalPrice);
      ordLect.setStatus("결제완료");
      orderLectDao.insertOrderLect(ordLect);   
      
      OrderLectInfo orderLectInfo = new OrderLectInfo();
      
      int LectNo = 0;
      //List<Lect> b = new ArrayList<Lect>();
      for(int i = 0; i<lectNos.length; i++) {
    	  LectNo = lectNos[i];
         //Lect lect = lectDao.getLectByNo(n);
         //b.add(lect);
         orderLectInfo.setOrderNo(ordLect.getNo());
         orderLectInfo.setCouponNo(couponNo);
         orderLectInfo.setLectNo(LectNo);
         orderLectInfo.setOrdLectAmount(1);
         orderLectDao.insertOrderLectInfo(orderLectInfo);
      }
      
      int cartDeleteLectNo = 0;

      for(int i = 0; i<lectNos.length; i++) {
         cartDeleteLectNo = lectNos[i];
         
         lectCartDao.deleteCartLect(cartDeleteLectNo);
      }
      
      if (couponNo != null) {
    	  
    	  couponDao.deleteCouponBoxByCouponNo(couponNo);
      }
      
      int point = (int)(lectTotalPrice * 0.001);
      
      User user = userDao.getUserProfilByNo(userNo);
      user.setPoint(user.getPoint() + point);
      userDao.updateUser(user);
      
   }

	@Override
	public List<OrderLectListDto> getOrderLectListByUserNo(int userNo) {
		User user = userDao.getUserProfilByNo(userNo);
		List<OrderLectListDto> userOrderList = orderLectDao.getOrderLectListByUserNo(user.getNo());
		return userOrderList;
	}

	@Override
	public List<OrderLectDetailListDto> getOrderLectInfoByOrderNo(int orderNo) {
		return orderLectDao.getOrderLectInfoByOrderNo(orderNo);
	}
	
	
}




































