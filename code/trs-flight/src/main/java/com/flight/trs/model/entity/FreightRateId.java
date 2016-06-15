package com.flight.trs.model.entity;

import java.io.Serializable;

import javax.persistence.Column;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月13日 上午1:31:43
 * @version V1.0
 */
public class FreightRateId implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1975751619729919836L;

	@Column(name = "FLIGHT_NO", nullable = false, length = 6)
	private String flightNo;
	
	@Column(name = "DEP_AIRPORT_CODE", nullable = false, length = 3)
	private String depAirportCode;
	
	@Column(name = "ARR_AIRPORT_CODE", nullable = false, length = 3)
	private String arrAirportCode;
	
	@Column(name = "DEP_DATE", nullable = false, length = 8)
	private String depDate;
	
	public FreightRateId() {
		// TODO 
	}
	
	public FreightRateId(String flightNo, String depAirportCode, String arrAirportCode, String depDate) {
		super();
		this.flightNo = flightNo;
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depDate = depDate;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrAirportCode == null) ? 0 : arrAirportCode.hashCode());
		result = prime * result + ((depAirportCode == null) ? 0 : depAirportCode.hashCode());
		result = prime * result + ((depDate == null) ? 0 : depDate.hashCode());
		result = prime * result + ((flightNo == null) ? 0 : flightNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FreightRateId other = (FreightRateId) obj;
		if (arrAirportCode == null) {
			if (other.arrAirportCode != null)
				return false;
		} else if (!arrAirportCode.equals(other.arrAirportCode))
			return false;
		if (depAirportCode == null) {
			if (other.depAirportCode != null)
				return false;
		} else if (!depAirportCode.equals(other.depAirportCode))
			return false;
		if (depDate == null) {
			if (other.depDate != null)
				return false;
		} else if (!depDate.equals(other.depDate))
			return false;
		if (flightNo == null) {
			if (other.flightNo != null)
				return false;
		} else if (!flightNo.equals(other.flightNo))
			return false;
		return true;
	}
	
}
