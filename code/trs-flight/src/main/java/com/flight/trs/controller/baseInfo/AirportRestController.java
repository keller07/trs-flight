package com.flight.trs.controller.baseInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flight.trs.model.AirportsJson;
import com.flight.trs.model.entity.Airport;
import com.flight.trs.service.basic.IAirportService;

/**
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月11日 上午12:41:36
 * @version V1.0
 */
@RestController
@RequestMapping("/airport")
public class AirportRestController {
	
	@Resource
	IAirportService airportService;
	
	private static final Logger logger = LoggerFactory.getLogger(AirportRestController.class);
	
	@RequestMapping(value="/", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	public List<Airport> getAll() {
		return airportService.findAll();
	}
	
	@RequestMapping(value="/sorted", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	public AirportsJson getAllSorted() {
		return airportService.sortAirports();
	}
	
	@RequestMapping(value="/{code}", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	public Airport get(
			@PathVariable String code) throws java.sql.SQLIntegrityConstraintViolationException {
		return airportService.get(code);
	}
	
	@RequestMapping(value="/", method={RequestMethod.POST})
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> post(
			@RequestParam String code
			,@RequestParam String cityName
			,@RequestParam String cityNameInitial
			,@RequestParam String airportName
			,@RequestParam String dist
			,@RequestParam Character isHot) throws java.sql.SQLIntegrityConstraintViolationException {
		Map<String, String> result = new HashMap<>();
		Airport airport = airportService.get(code);
		if (airport!=null) {
			logger.info("机场已存在...");
			result.put("status", "existing");
			return result;
		}
		airport = new Airport(code, cityName, cityNameInitial, airportName, dist, isHot);
		try {
			airportService.post(airport);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("status", "failed");
			return result;
		}
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping(value="/", method={RequestMethod.PUT})//
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> put(
			@RequestBody Airport airport
			//@RequestParam String code
			//,@RequestParam String cityName
			//,@RequestParam String cityNameInitial
			//,@RequestParam String airportName
			//,@RequestParam String dist
			//,@RequestParam Character isHot)
			){
		Map<String, String> result = new HashMap<>();
		Airport targetAirport = airportService.get(airport.getCode());
		//如果机场不存在或已被删除
		if (targetAirport==null || targetAirport.isDeleted()) {
			result.put("status", "nonexistent");
			return result;
		}
		try {
			targetAirport.setCityName(airport.getCityName());
			targetAirport.setCityNameInitial(airport.getCityNameInitial());
			targetAirport.setAirportName(airport.getAirportName());
			targetAirport.setDist(airport.getDist());
			targetAirport.setIsHot(airport.getIsHot());
			airportService.update(targetAirport);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("status", "failed");
			return result;
		}
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping(value="/{code}", method={RequestMethod.DELETE})//
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> delete(
			@PathVariable String code) throws java.sql.SQLIntegrityConstraintViolationException {
		Map<String, String> result = new HashMap<>();
		Airport airport = airportService.get(code);
		if (airport==null) {
			result.put("status", "nonexistent");
			return result;
		}
		try {
			airportService.delete(code);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("status", "failed");
			return result;
		}
		result.put("status", "success");
		return result;
	}
	
}
