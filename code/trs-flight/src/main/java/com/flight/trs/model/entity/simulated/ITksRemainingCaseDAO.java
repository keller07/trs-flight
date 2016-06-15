package com.flight.trs.model.entity.simulated;

import java.util.List;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月15日 上午2:52:35
 * @version V1.0
 */
public interface ITksRemainingCaseDAO {

	List<TksRemainingCase> get();
	
	TksRemainingCase get(TksRemainingCaseId id);
	
	List<TksRemainingCase> get(String flightNo, String depAirportCode, String arrAirportCode, String depTime, String depDate);

}
