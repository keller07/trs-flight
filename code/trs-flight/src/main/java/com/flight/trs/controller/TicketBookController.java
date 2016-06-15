package com.flight.trs.controller;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flight.trs.model.entity.AircraftType;
import com.flight.trs.model.entity.Airport;
import com.flight.trs.model.entity.Carrier;
import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.FreightRateId;
import com.flight.trs.model.entity.PassengerGauge;
import com.flight.trs.model.entity.PassengerGaugeId;
import com.flight.trs.model.entity.Segment;
import com.flight.trs.model.entity.SegmentId;
import com.flight.trs.model.entity.simulated.ITksRemainingCaseDAO;
import com.flight.trs.service.basic.IFreightRateService;
import com.flight.trs.service.basic.IPassengerGaugeService;
import com.flight.trs.service.basic.ISegmentService;
import com.flight.trs.util.DateUtil;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月21日 上午12:15:45
 * @version V1.0
 */
@Controller
@RequestMapping("")
public class TicketBookController extends BaseController {

	@Resource
	ISegmentService segmentService;
	@Resource
	ITksRemainingCaseDAO tksDAO;
	@Resource
	IFreightRateService freightRateService;
	@Resource
	IPassengerGaugeService paxGaugeSerivce;
	
	
	Logger logger = LoggerFactory.getLogger(TicketBookController.class);
	
	@RequestMapping(value="/ticket/book", method={RequestMethod.POST})
	public ModelAndView queryFlight(
			@RequestParam String flightNo
			, @RequestParam String depAirportCode
			, @RequestParam String arrAirportCode
			, @RequestParam String depTime
			, @RequestParam String depDate 
			, @RequestParam Character clCode
			, ModelAndView modelAndView){
		
		SegmentId sid = new SegmentId(flightNo.substring(0, 2), flightNo.substring(2), depAirportCode, arrAirportCode, depTime);
		Segment segment = segmentService.get(sid);
		//TksRemainingCaseId tksId = new TksRemainingCaseId(flightNo, depAirportCode, arrAirportCode, depTime, depDate, clCode);
		//TksRemainingCase tks = tksDAO.get(tksId);
		FreightRateId fId = new FreightRateId(flightNo, depAirportCode, arrAirportCode, depDate);
		FreightRate freightRate = freightRateService.get(fId);
		
		Carrier carrier = segment.getCarrier();
		Airport depAirport = segment.getDepAirport();
		Airport arrAirport = segment.getArrAirport();
		AircraftType aircraftType = segment.getAircraftType();
		PassengerGaugeId paxGaugeId = new PassengerGaugeId(carrier.getCode(), clCode);
		PassengerGauge paxGauge = paxGaugeSerivce.get(paxGaugeId);
		
		String depCityName = depAirport.getCityName();
		String arrCityName = arrAirport.getCityName();
		
		String carrierName = carrier.getName();
		//String acCode = aircraftType.getCode();
		String acName = aircraftType.getName();
		String mealService = segment.getMealService();
		String meals = "n".equals(mealService)?"无":"有";
		
		String depAirportName = depAirport.getAirportName();
		String depTerminal = segment.getDepTerminal();
		String arrAirportName = arrAirport.getAirportName();
		String arrTerminal = segment.getArrTerminal();
		String arrTime = segment.getArrTime();
		String arrDate = "";
		
		try {
			String depDateTime = DateUtil.getDateTimeStr(depDate,depTime);
			String arrDateTime = DateUtil.getDateTimeStr(depDate,arrTime);
			depDate = depDateTime.substring(0, 10);
			depTime = depDateTime.substring(11);
			arrDate = arrDateTime.substring(0, 10);
			arrTime = arrDateTime.substring(11);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		String classOfService = paxGauge.getClassOfService();
		float discount = paxGauge.getDiscount();
		int ticketFee = (int) (discount * 0.1 * freightRate.getTicketFee());
		
		int airportTax = (int) freightRate.getAirportTax();
		int baf = (int)freightRate.getBaf();
		int price = (int)(ticketFee + airportTax + baf);
		
		modelAndView.addObject("depCityName",depCityName);
		modelAndView.addObject("arrCityName",arrCityName);
		
		modelAndView.addObject("carrierName",carrierName);
		modelAndView.addObject("flightNo",flightNo);
		modelAndView.addObject("acName",acName);
		modelAndView.addObject("meals",meals);
		
		modelAndView.addObject("depDate",depDate);
		modelAndView.addObject("depTime",depTime);
		modelAndView.addObject("depAirportName",depAirportName);
		modelAndView.addObject("depTerminal",null == depTerminal ? "" :"T"+depTerminal);
		modelAndView.addObject("arrDate",arrDate);
		modelAndView.addObject("arrTime",arrTime);
		modelAndView.addObject("arrAirportName",arrAirportName);
		modelAndView.addObject("arrTerminal",null == arrTerminal ? "" :"T"+arrTerminal);
		
		modelAndView.addObject("classOfService",getClassOfServiceName(classOfService));
		modelAndView.addObject("discount",discount);
		modelAndView.addObject("ticketFee",ticketFee);
		
		modelAndView.addObject("airportTax",airportTax);
		modelAndView.addObject("baf",baf);
		modelAndView.addObject("price",price);
		
		//存放到session里
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("flightNo",flightNo);
		session.setAttribute("depAirportCode",depAirportCode);
		session.setAttribute("arrAirportCode",arrAirportCode);
		session.setAttribute("depTime",depTime);
		session.setAttribute("depDate",depDate);
		session.setAttribute("clCode",clCode);
		
		session.setAttribute("segment", segment);
		
		session.setAttribute("freightRate", freightRate);
		
		session.setAttribute("depCityName",depCityName);
		session.setAttribute("arrCityName",arrCityName);
		session.setAttribute("carrierName",carrierName);
		session.setAttribute("flightNo",flightNo);
		session.setAttribute("acName",acName);
		session.setAttribute("meals",meals);
		session.setAttribute("depDate",depDate);
		session.setAttribute("depTime",depTime);
		session.setAttribute("depAirportName",depAirportName);
		session.setAttribute("depTerminal",null == depTerminal ? "" :"T"+depTerminal);
		session.setAttribute("arrDate",arrDate);
		session.setAttribute("arrTime",arrTime);
		session.setAttribute("arrAirportName",arrAirportName);
		session.setAttribute("arrTerminal",null == arrTerminal ? "" :"T"+arrTerminal);
		session.setAttribute("classOfService",getClassOfServiceName(classOfService));
		session.setAttribute("discount",discount);
		session.setAttribute("ticketFee",ticketFee);
		session.setAttribute("airportTax",airportTax);
		session.setAttribute("baf",baf);
		session.setAttribute("price",price);
		
		modelAndView.setViewName("ticket.book");
		
		return modelAndView;
	}

	//查询价格
	
	public String getClassOfServiceName(String classOfService){
		if ("first".equals(classOfService)) {
			return "头等舱";
		}else if ("business".equals(classOfService)) {
			return "商务舱";
		}
		else {
			return "经济舱";
		}
	}
	
	
}
