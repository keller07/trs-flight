package com.flight.trs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flight.trs.model.entity.Airport;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月2日 上午1:01:52
 * @version V1.0
 */
public class AirportsJson {

	//国内热门
	private List<Airport> domesAirportHotList = new ArrayList<>();
	//港澳台*国际热门
	private List<Airport> interHotAirportList = new ArrayList<>();
	//国内机场，按大写首字母分组
	private Map<String, List<Airport>> domesAirportListMap = new HashMap<>();
	//国际机场，按所在大洲分组
	private Map<String, List<Airport>> interAirportListMap = new HashMap<>();
	
	public AirportsJson() {
		// TODO 
	}
	
	public List<Airport> getInterHotAirportList() {
		return interHotAirportList;
	}

	public void setInterHotAirportList(List<Airport> interHotAirportList) {
		this.interHotAirportList = interHotAirportList;
	}

	public Map<String, List<Airport>> getDomesAirportListMap() {
		return domesAirportListMap;
	}

	public void setDomesAirportListMap(Map<String, List<Airport>> domesAirportListMap) {
		this.domesAirportListMap = domesAirportListMap;
	}

	public Map<String, List<Airport>> getInterAirportListMap() {
		return interAirportListMap;
	}

	public void setInterAirportListMap(Map<String, List<Airport>> interAirportListMap) {
		this.interAirportListMap = interAirportListMap;
	}

	public List<Airport> getDomesAirportHotList() {
		return domesAirportHotList;
	}

	public void setDomesAirportHotList(List<Airport> domesAirportHotList) {
		this.domesAirportHotList = domesAirportHotList;
	}

}
