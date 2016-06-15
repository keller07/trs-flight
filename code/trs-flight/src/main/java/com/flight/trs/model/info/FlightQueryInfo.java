package com.flight.trs.model.info;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月18日 下午4:58:49
 * @version V1.0
 */
public class FlightQueryInfo {

	//必选
	private String depAirportCode;
	private String arrAirportCode;
	private String depDate;
	//可选
	private String carrierCode;
	private Character nonStop;
	private String earliest;
	private String latest;
	
	private Integer count;
	private String classOfService;
	
	public FlightQueryInfo() {
		// TODO 
	}
	
	public String getDepAirportCode() {
		return depAirportCode;
	}

	public void setDepAirportCode(String depAirportCode) {
		this.depAirportCode = depAirportCode;
	}

	public String getArrAirportCode() {
		return arrAirportCode;
	}

	public void setArrAirportCode(String arrAirportCode) {
		this.arrAirportCode = arrAirportCode;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public Character getNonStop() {
		return nonStop;
	}

	public void setNonStop(Character nonStop) {
		this.nonStop = nonStop;
	}

	public String getEarliest() {
		return earliest;
	}

	public void setEarliest(String earliest) {
		this.earliest = earliest;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getClassOfService() {
		return classOfService;
	}

	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}


}
