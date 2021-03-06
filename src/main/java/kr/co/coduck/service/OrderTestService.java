package kr.co.coduck.service;

import java.util.List;
import java.util.Map;

import kr.co.coduck.dto.OrderTestDetailListDto;
import kr.co.coduck.vo.OrdTest;
import kr.co.coduck.vo.OrdTestInfo;

public interface OrderTestService {

	void insertOrderTest(int userNo, Integer couponNo, int[] testNos, int testTotalPrice, String bankNo);
	void insertOrderTestInfo(OrdTestInfo ordTestInfo);
	List<OrderTestDetailListDto> getOrderTestInfoByOrderNo(int userNo);
	OrdTest getOrdTestByTestNoNUserNo(Map<String, Integer> map);
}
