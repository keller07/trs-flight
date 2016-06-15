package com.flight.trs.model;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月19日 下午9:55:44
 * @version V1.0
 */
public class VoyageInfo {

	private int segment_id;
	private String carrierCode;
	private String flightNo;
	
	private String depAirportCode;
	private String depDatetime;
	private String depTerminal;
	
	private String arrAirportCode;
	private String arrDatetime;
	private String arrTerminal;
	private String clCode;

	private int voyageOrder;
	
	public VoyageInfo() {
		// TODO 
	}

	public int getSegment_id() {
		return segment_id;
	}

	public void setSegment_id(int segment_id) {
		this.segment_id = segment_id;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDepAirportCode() {
		return depAirportCode;
	}

	public void setDepAirportCode(String depAirportCode) {
		this.depAirportCode = depAirportCode;
	}

	public String getDepDatetime() {
		return depDatetime;
	}

	public void setDepDatetime(String depDatetime) {
		this.depDatetime = depDatetime;
	}

	public String getDepTerminal() {
		return depTerminal;
	}

	public void setDepTerminal(String depTerminal) {
		this.depTerminal = depTerminal;
	}

	public String getArrAirportCode() {
		return arrAirportCode;
	}

	public void setArrAirportCode(String arrAirportCode) {
		this.arrAirportCode = arrAirportCode;
	}

	public String getArrDatetime() {
		return arrDatetime;
	}

	public void setArrDatetime(String arrDatetime) {
		this.arrDatetime = arrDatetime;
	}

	public String getArrTerminal() {
		return arrTerminal;
	}

	public void setArrTerminal(String arrTerminal) {
		this.arrTerminal = arrTerminal;
	}

	public String getClCode() {
		return clCode;
	}

	public void setClCode(String clCode) {
		this.clCode = clCode;
	}

	public int getVoyageOrder() {
		return voyageOrder;
	}

	public void setVoyageOrder(int voyageOrder) {
		this.voyageOrder = voyageOrder;
	}

}
