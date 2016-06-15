package com.flight.trs.service.basic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flight.trs.dao.impl.AirportDAO;
import com.flight.trs.model.AirportsJson;
import com.flight.trs.model.entity.Airport;
import com.flight.trs.service.basic.IAirportService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月10日 下午5:01:56
 * @version V1.0
 */
@Repository("airportService")
public class AirportService 
	implements IAirportService {

	@Resource(name="airportDAO")
	AirportDAO airportDAO;
	
	public AirportService() {
		// TODO 
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public Airport add(String code, String cityName, String airportName) {
		// TODO
		Airport airport = new Airport(code, cityName, airportName);
		airportDAO.save(airport);
		return airport;
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void delete(String code) {
		// TODO
		Airport airport = airportDAO.load(code);
		airportDAO.delete(airport);
		//airportDAO.deleteNotPhysically(airport);
	}
	
	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void delete(Airport airport) {
		// TODO
		airportDAO.delete(airport);
		//airportDAO.deleteNotPhysically(airport);
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void deleteInBatch(Collection<String> codes) {
		airportDAO.deleteByIDInBatch(codes);
	}
	
	@Transactional
	@Override
	public void update(Airport airport) {
		airportDAO.update(airport);
	}

	@Transactional
	@Override
	public void updateInBatch(Collection<Airport> airports) {
		airportDAO.updateInBatch(airports);
	}

	@Override
	public Long countAll() {
		return airportDAO.getRowCount();
	}

	@Override
	public List<Airport> findAll() {
		return airportDAO.loadAll();
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public List<Airport> findWithPaging(int firstIndex, int recordAmount) {
		// TODO
		return airportDAO.findForPaging(firstIndex, firstIndex);
	}

	@Override
	public AirportsJson sortAirports() {
		
		List<Airport> airports = findAll();
		
		AirportsJson airportsJson = new AirportsJson();
		//国内热门
		List<Airport> domesAirportHotList = airportsJson.getDomesAirportHotList();
		//所有国内机场，按大写首字母分组
		Map<String, List<Airport>> domesAirportListMap = airportsJson.getDomesAirportListMap();
		//港澳台*国际热门
		List<Airport> interHotAirportList = airportsJson.getInterHotAirportList();
		//所有国际机场，按所在大洲分组
		Map<String, List<Airport>> interAirportListMap = airportsJson.getInterAirportListMap();

		for (Airport airport : airports) {
			//如果是国内机场先判断取出热门的后根据根据首字母分组
			if (!airport.isInternational()) {
				if (airport.isHot()) {
					domesAirportHotList.add(airport);
				}
				String cityNameInitial = airport.getCityNameInitial();
				if (!domesAirportListMap.containsKey(cityNameInitial)) {
					List<Airport> airportGroup = new ArrayList<Airport>();
					airportGroup.add(airport);
					domesAirportListMap.put(cityNameInitial, airportGroup);
				}
				else {
					domesAirportListMap.get(cityNameInitial).add(airport);
				}
			}
			//如果是港澳台/国际机场则判断取出热门的后根据所在大洲分组
			else { 
				if (airport.isHot()) {
					interHotAirportList.add(airport);
				}
				String distStr = airport.getDist();
				if (!interAirportListMap.containsKey(distStr)) {
					List<Airport> airportGroup = new ArrayList<Airport>();
					airportGroup.add(airport);
					interAirportListMap.put(distStr, airportGroup);
				}
				else {
					interAirportListMap.get(distStr).add(airport);
				}
			}
		}
		return airportsJson;
	}

	@Override
	public Airport get(String code) {
		// TODO
		return airportDAO.get(code);
	}

	@Transactional
	@Override
	public void post(Airport airport) {
		// TODO
		airportDAO.save(airport);
	}

}
