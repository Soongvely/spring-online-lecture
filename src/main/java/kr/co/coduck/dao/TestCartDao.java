package kr.co.coduck.dao;

import java.util.List;
import java.util.Map;

import kr.co.coduck.dto.CartChoiceTestListDto;
import kr.co.coduck.dto.CartTestDto;

public interface TestCartDao {

	List<CartTestDto> getCartTestListUserByNo(int userNo);
	CartChoiceTestListDto getCartChoiceTestListByCartChoiceTestNo(Map<String, Object> criteria);
	void deleteCartTest(int testNo);
	
}
