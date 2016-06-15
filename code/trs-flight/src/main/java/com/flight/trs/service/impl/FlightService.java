package com.flight.trs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flight.trs.dao.impl.AircraftTypeDAO;
import com.flight.trs.dao.impl.AirportDAO;
import com.flight.trs.dao.impl.CarrierDAO;
import com.flight.trs.dao.impl.PassengerGaugeDAO;
import com.flight.trs.dao.impl.SegmentDAO;
import com.flight.trs.model.entity.AircraftType;
import com.flight.trs.model.entity.Airport;
import com.flight.trs.model.entity.Carrier;
import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.FreightRateId;
import com.flight.trs.model.entity.PassengerGauge;
import com.flight.trs.model.entity.PassengerGaugeId;
import com.flight.trs.model.entity.Segment;
import com.flight.trs.model.entity.SegmentId;
import com.flight.trs.model.entity.simulated.TksRemainingCase;
import com.flight.trs.model.entity.simulated.TksRemainingCaseDAO;
import com.flight.trs.model.Flight;
import com.flight.trs.model.Flight.ClassSection;
import com.flight.trs.service.IFlightService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月17日 下午5:15:52
 * @version V1.0
 */
@Service
public class FlightService implements IFlightService {

	//static final private Logger logger = LoggerFactory.getLogger(FlightService.class);
	static private long outOfTime= 1000;
	
	@Resource
	SegmentDAO segmentDAO;
	
	@Resource
	TksRemainingCaseDAO tksRemainingCaseDAO;
	
	@Resource
	CarrierDAO carrierDAO;
	
	@Resource
	AirportDAO airportDAO;
	
	@Resource
	AircraftTypeDAO aircraftTypeDAO;
	
	@Resource
	PassengerGaugeDAO passengerGaugeDAO;
	
	@Resource(name="realFreightRateService")
	FreightRateService freightRateService;
	
	//private static final Logger logger = LoggerFactory.getLogger(FlightService.class);
	
	public FlightService() {
		// TODO 
	}

	@Override
	public Iterable<Flight> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			char nonStop, String depTimeRange, String classOfServicesRequested, int mixCountOfSpareSeats) {
		List<Flight> flights = new ArrayList<>();
		
		String earliest = "";
		String lastest = "";
		if ("/-/-/-/-/-/-/-/-".equals(depTimeRange)) {
			earliest = "/-/-/-/-";
			lastest = "/-/-/-/-";
		}
		else{
			earliest = depTimeRange.substring(0, 4);
			lastest = depTimeRange.substring(4, 8);
		}

		//如果最少剩余票数大于0则需要检查剩余票数，默认为0不要求检查
		boolean checkCountOfSpareSeats = mixCountOfSpareSeats > 0;
		
		List<Segment> segments = segmentDAO.get(depAirportCode, arrAirportCode, depDate, carrierCode, nonStop,earliest, lastest);
		
		for (int i = 0; i < segments.size(); i++) {
			Segment segment = segments.get(i);
			SegmentId segmentId = segment.getId();
			
			Flight flight = new Flight();
			
			List<TksRemainingCase> tksRemainingCases = tksRemainingCaseDAO.get(
					segmentId.getTotalFlightNo()
					, segmentId.getDepAirportCode()
					, segmentId.getArrAirportCode()
					, segmentId.getDepTime()
					, depDate);
			for (int j = 0; j < tksRemainingCases.size(); j++) {
				TksRemainingCase tksRemainingCase = tksRemainingCases.get(j);
				//如果剩余票数低于最少剩余票数，则不做任何处理
				if (checkCountOfSpareSeats && tksRemainingCase.getCountOfSpareSeats() < mixCountOfSpareSeats) {
					//logger.info(tksRemainingCase.getCountOfSpareSeats()+"--"+mixCountOfSpareSeats);
					continue;
				}
				
				PassengerGaugeId passengerGaugeId = new PassengerGaugeId(segmentId.getCarrierCode(), tksRemainingCase.getId().getClCode());
				PassengerGauge passengerGauge = passengerGaugeDAO.get(passengerGaugeId);
				if (null == passengerGauge) {
					//logger.info(segmentId.getCarrierCode()+"--"+tksRemainingCase.getId().getClCode());
					continue;
				}
				String classOfService = passengerGauge.getClassOfService();
				//如果舱位服务等级在请求的服务等级之内
				if (checkClassOfService(classOfService, classOfServicesRequested)) {
					ClassSection classSection = flight.new ClassSection();
					classSection.setClCode(tksRemainingCase.getId().getClCode());
					classSection.setClassOfService(classOfService);
					classSection.setDiscount(passengerGauge.getDiscount());
					classSection.setCountOfSpareSeats(tksRemainingCase.getCountOfSpareSeats());
					flight.getCls().add(classSection);
				}else{
					//logger.info("classOfService:"+classOfService+";  classOfServicesRequested"+classOfServicesRequested);
					//logger.info("如果舱位服务等级不在请求的服务等级之内！");
				}
			}
			if (!flight.getCls().isEmpty()) {
				Carrier carrier = segment.getCarrier();
				flight.setCarrierCode(carrier.getCode());
				flight.setCarrierName(carrier.getName());
				flight.setTotalFlightNo(segment.getId().getTotalFlightNo());
				
				Airport depAirport = segment.getDepAirport();
				flight.setDepAirportCode(depAirport.getCode());
				flight.setDepAirportName(depAirport.getAirportName());
				flight.setDepTerminal(segment.getDepTerminal());
				flight.setDepTime(segment.getId().getDepTime());
				
				Airport arrAirport = segment.getArrAirport();
				flight.setArrAirportCode(arrAirport.getCode());
				flight.setArrAirportName(arrAirport.getAirportName());
				flight.setArrTerminal(segment.getArrTerminal());
				flight.setArrTime(segment.getArrTime());
				
				flight.setDepDate(depDate);
				
				AircraftType aircraftType = segment.getAircraftType();
				flight.setAcCode(aircraftType.getCode());
				flight.setAcName(aircraftType.getName());
				
				flight.setStopCodes(segment.getStops());
				flight.setStopCityNames(getStops(segment.getStops()));
				flight.setMealService(segment.getMealService());
				//机建燃油
				flight.setDuration(segment.getDuration());
				flight.setFlightShort(segment.getFlightShort());
				
				flight.setTraFlightNo(segment.getTraFlightNo());
				//如果该航段包括中转航段，则还需进入相应的中转航段信息
				Airport traAirport = segment.getTraAirport();
				if (null != traAirport) {
					flight.setTraAirportCode(traAirport.getCode());
					flight.setTraCityName(traAirport.getCityName());
					flight.setTraAirportName(traAirport.getAirportName());
					
					flight.setTraArrTime(segment.getTraArrTime());
					flight.setTraArrTerminal(segment.getTraArrTerminal());
					flight.setTraDepTime(segment.getTraDepTime());
					flight.setTraDepTerminal(segment.getTraDepTerminal());
					
					AircraftType traAircraftType = segment.getTraAircraftType();
					flight.setTraAcCode(traAircraftType.getCode());
					flight.setTraAcName(traAircraftType.getName());
				}
				FreightRateId freightRateId = new FreightRateId(flight.getTotalFlightNo(), depAirportCode, arrAirportCode, depDate);
				
				
				FreightRate freightRate = getFreightRate(freightRateId);
				if (null == freightRate) {
					//如果查不到该航班对应的运价缓存则直接跳过该航班进行下一个航班的检查
					continue;
				}
				flight.setAirportTax(freightRate.getAirportTax());
				flight.setBaf(freightRate.getBaf());
				float ticketFee = freightRate.getTicketFee();
				flight.setTicketFee(ticketFee);
				for (ClassSection cs : flight.getCls()) {
					
					float discount = cs.getDiscount();
					cs.setTicketFee((int) (ticketFee * discount * 0.1));
				}
				
				flights.add(flight);
			}
			tksRemainingCases.clear();
			
		}
		segments.clear();
		
		Comparator<Flight> flightComparator = Comparator.comparing(Flight::getTicketFee);
		//按100%全价从低到高对航班进行排序
		Collections.sort(flights, flightComparator);
		
		return flights;
	}
	
	public boolean checkClassOfService(String classOfService, String classOfServices){
		return classOfServices.contains(classOfService);
	}
	
	public String getStops(String stops){
		String stopCityNames = "";
		int stopAmount = Integer.valueOf(stops.substring(0, 1));
		if ( stopAmount <= 0 ) {
			stopCityNames = "无经停";
			return stopCityNames;
		} 
		
		for (int i = 1; i <= stopAmount; i++) {
			String airportCode = stops.substring(2+(i-1)*4,5+(i-1)*4);
			Airport airport = airportDAO.get(airportCode);
			if (airport != null) {
				if (i == 1) {
					stopCityNames += airport.getCityName();
				}
				else {
					stopCityNames += "," + airport.getCityName();
				}
			}
		}
		return stopCityNames;
	}
	
	//获取运价缓存信息
	public FreightRate getFreightRate(FreightRateId id){
		FreightRate freightRate = freightRateService.find(id);
		if (null == freightRate) {
			return null;
		}
		if (isFreightOutOfDate(freightRate)) {
			freightRateService.remove(id);				//从缓存清除对应的运价信息
			freightRate = freightRateService.find(id);	//重新获取运价信息并缓存
		}
		return freightRate;
	}
	
	//判断运价信息是否过期
	boolean isFreightOutOfDate(FreightRate freightRate){
		long time = freightRate.getUpdateTime().getTime();
		//logger.info("."+time);
		Date now = new Date();
		//logger.info("now."+now.getTime());
		return (now.getTime()-time) > outOfTime;
	}

}
