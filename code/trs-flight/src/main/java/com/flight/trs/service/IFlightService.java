package com.flight.trs.service;

import com.flight.trs.model.Flight;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月17日 下午5:15:27
 * @version V1.0
 */
public interface IFlightService {

	public Iterable<Flight> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			char nonStop, String depTimeRange, String classOfService,int mixCountOfSpareSeats);
	
}
