package com.flight.trs.model.entity.simulated;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月15日 上午2:39:55
 * @version V1.0
 */
@Embeddable
public class TksRemainingCaseId implements Serializable {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -6509375750426767739L;

	@Column(name = "FLIGHT_NO", nullable = false, length = 6)
	private String flightNo;
	@Column(name = "DEP_AIRPORT_CODE", nullable = false, length = 3)
	private String depAirportCode;
	@Column(name = "ARR_AIRPORT_CODE", nullable = false, length = 3)
	private String arrAirportCode;
	@Column(name = "DEP_TIME", nullable = false, length = 3)
	private String depTime;
	@Column(name = "DEP_DATE", nullable = false, length = 3)
	private String depDate;
	@Column(name = "CL_CODE", nullable = false)
	private Character clCode;
	
	public TksRemainingCaseId() {
		// TODO 
	}

	public TksRemainingCaseId(String flightNo, String depAirportCode, String arrAirportCode, String depTime,
			String depDate, Character clCode) {
		super();
		this.flightNo = flightNo;
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.depDate = depDate;
		this.clCode = clCode;
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

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public Character getClCode() {
		return clCode;
	}

	public void setClCode(Character clCode) {
		this.clCode = clCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrAirportCode == null) ? 0 : arrAirportCode.hashCode());
		result = prime * result + ((clCode == null) ? 0 : clCode.hashCode());
		result = prime * result + ((depAirportCode == null) ? 0 : depAirportCode.hashCode());
		result = prime * result + ((depDate == null) ? 0 : depDate.hashCode());
		result = prime * result + ((depTime == null) ? 0 : depTime.hashCode());
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
		TksRemainingCaseId other = (TksRemainingCaseId) obj;
		if (arrAirportCode == null) {
			if (other.arrAirportCode != null)
				return false;
		} else if (!arrAirportCode.equals(other.arrAirportCode))
			return false;
		if (clCode == null) {
			if (other.clCode != null)
				return false;
		} else if (!clCode.equals(other.clCode))
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
		if (depTime == null) {
			if (other.depTime != null)
				return false;
		} else if (!depTime.equals(other.depTime))
			return false;
		if (flightNo == null) {
			if (other.flightNo != null)
				return false;
		} else if (!flightNo.equals(other.flightNo))
			return false;
		return true;
	}

}
