package com.flight.trs.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flight.trs.model.entity.Airport;
import com.flight.trs.model.entity.Carrier;
import com.flight.trs.model.enums.FlightType;
import com.flight.trs.service.basic.impl.AirportService;
import com.flight.trs.service.basic.impl.CarrierService;
import com.flight.trs.service.impl.FreightRateService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月4日 下午3:26:43
 * @version V1.0
 */
@Controller
@RequestMapping("")
public class HomeController extends BaseController {
	
	static final private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource
	AirportService airportService;
	@Resource
	CarrierService carrierService;
	
	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView){
		
		List<Carrier> carriers = carrierService.findAll();
		logger.info(""+carriers.size());
		
		modelAndView.addObject("carriers",carriers);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value="/flight.query", method={RequestMethod.GET})
	public ModelAndView queryFlight(
			@RequestParam String domesticFlightType
			, @RequestParam String depAirportCode
			, @RequestParam String arrAirportCode
			, @RequestParam String depDate
			, @RequestParam(required=false) String retDate
			, @RequestParam(required=false) String traAirportCode
			, @RequestParam(required=false) String traDate
			, @RequestParam(required=false) String carrierCode
			, @RequestParam(required=false) Character nonStop
			, @RequestParam(required=false) String depTimeRange
			, @RequestParam(required=false) String classOfServices
			, @RequestParam(required=false) Integer mixCountOfSpareSeats
			, ModelAndView modelAndView){
		
		Airport depAirport = airportService.get(depAirportCode);
		Airport arrAirport = airportService.get(arrAirportCode);
		
		if ( null == depAirport || null == arrAirport ) {
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		modelAndView.addObject("domesticFlightType", domesticFlightType);
		modelAndView.addObject("depAirportCode", depAirportCode);
		modelAndView.addObject("depCityName", depAirport.getCityName());
		modelAndView.addObject("depAirportName", depAirport.getAirportName());
		modelAndView.addObject("arrAirportCode", arrAirportCode);
		modelAndView.addObject("arrCityName", arrAirport.getCityName());
		modelAndView.addObject("arrAirportName", arrAirport.getAirportName());
		modelAndView.addObject("depDate", depDate);
		
		if (FlightType.ROUND_TRIP.equals(domesticFlightType)) {
			if (null == retDate) {
				modelAndView.setViewName("error");
				return modelAndView;
			}
			else {
				modelAndView.addObject("retDate", retDate);
			}
		}
		else if (FlightType.CONNECTING_FLIGHT.equals(domesticFlightType)) {
			Airport traAirport = airportService.get(traAirportCode);
			if (null == traAirportCode || null == traDate || null == traAirport) {
				modelAndView.setViewName("error");
				return modelAndView;
			}
			else {
				modelAndView.addObject("traAirportCode", traAirportCode);
				modelAndView.addObject("traCityName", traAirport.getCityName());
				modelAndView.addObject("traDate", traDate);
			}
		}

		if (null != carrierCode) {
			modelAndView.addObject("carrierCode", carrierCode);
		}
		if (null != nonStop) {
			modelAndView.addObject("nonStop", nonStop);
		}
		if (null != depTimeRange) {
			modelAndView.addObject("depTimeRange", depTimeRange);
		}
		if (null != classOfServices) {
			modelAndView.addObject("classOfServices", classOfServices);
		}
		if (null != mixCountOfSpareSeats) {
			modelAndView.addObject("mixCountOfSpareSeats", mixCountOfSpareSeats);
		}
		
		modelAndView.setViewName("flight.query");
		return modelAndView;
	}
	
	@RequestMapping("/orderform.query")
	public ModelAndView queryOrder(ModelAndView modelAndView){
		modelAndView.setViewName("order.query");
		return modelAndView;
	}
	
	@RequestMapping("/ticket.query")
	public ModelAndView queryTicket(ModelAndView modelAndView){
		modelAndView.setViewName("ticket.query");
		return modelAndView;
	}
	
	@RequestMapping("/error")
	public ModelAndView error(ModelAndView modelAndView){
		modelAndView.setViewName("error");
		return modelAndView;
	}
	
	@Resource(name="realFreightRateService")
	FreightRateService freightRateService;
	
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView modelAndView){
		
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
//	@RequestMapping("/ticket/book")
//	public ModelAndView add(ModelAndView modelAndView){
//		modelAndView.setViewName("ticket.book");
//		return modelAndView;
//	}
	@RequestMapping("/affirm")
	public ModelAndView affirm(ModelAndView modelAndView){
		modelAndView.setViewName("affirm");
		return modelAndView;
	}
	@RequestMapping("/succeed")
	public ModelAndView succeed(ModelAndView modelAndView){
		modelAndView.setViewName("succeed");
		return modelAndView;
	}
}
