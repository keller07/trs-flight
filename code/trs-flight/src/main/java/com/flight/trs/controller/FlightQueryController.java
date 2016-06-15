package com.flight.trs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.flight.trs.model.Flight;
import com.flight.trs.model.info.DomesticFlightQueryInfo;
import com.flight.trs.service.basic.impl.AirportService;
import com.flight.trs.service.impl.FlightService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月18日 下午4:49:51
 * @version V1.0
 */
@Controller
@RequestMapping("/flight")
public class FlightQueryController {

	@Resource
	FlightService flightService;
	
	@Resource
	AirportService airportService;
	
	final private Logger logger = LoggerFactory.getLogger(FlightQueryController.class);
	
	@RequestMapping(value="/domesticFlight.query", method=RequestMethod.GET)
	public ModelAndView queryFlight(
			DomesticFlightQueryInfo queryInfo
			, ModelAndView modelAndView
			) {
		
		logger.info("queryinfo:"+queryInfo.toString());
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/query", method={RequestMethod.GET})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<Flight> get(@RequestParam String depAirportCode
			, @RequestParam String arrAirportCode
			, @RequestParam String depDate
			, @RequestParam(required=false) String carrierCode
			, @RequestParam(required=false) Character nonStop
			, @RequestParam(required=false) String depTimeRange
			, @RequestParam(required=false) String classOfServices
			, @RequestParam(required=false) Integer mixCountOfSpareSeats
			) {
		
		carrierCode = null == carrierCode ? "/-/-" : carrierCode ;
		nonStop = null == nonStop ? 'n' : 'y' ;
		depTimeRange = null == depTimeRange ? "/-/-/-/-/-/-/-/-" : depTimeRange ;
		classOfServices = null == classOfServices ? "first,business,economic,special" : classOfServices;
		mixCountOfSpareSeats = null == mixCountOfSpareSeats ? 0 : mixCountOfSpareSeats;
		return  (List<Flight>) flightService.get(depAirportCode,arrAirportCode,depDate,carrierCode,nonStop,depTimeRange,classOfServices,mixCountOfSpareSeats);
		
	}
}
