package kr.co.coduck.service;

import java.util.List;

import kr.co.coduck.dto.OrderLectDetailListDto;
import kr.co.coduck.dto.OrderLectListDto;

public interface OrderLectService {

	void insertOrderLect(int userNo, Integer couponNo, int[] lectNos, int lectTotalPrice, String bankNo);
	List<OrderLectListDto> getOrderLectListByUserNo(int userNo);
	List<OrderLectDetailListDto> getOrderLectInfoByOrderNo(int orderNo);
}
