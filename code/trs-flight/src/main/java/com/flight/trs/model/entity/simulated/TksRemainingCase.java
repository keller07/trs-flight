package com.flight.trs.model.entity.simulated;
//Generated 2016-1-16 17:01:14 by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
* AircraftType generated by hbm2java
*/

@Entity
@Table(name = "TRS_TKS_REMAINING_CASE")
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"serialVersionUID"})
public class TksRemainingCase implements java.io.Serializable {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 2352448729621902387L;
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "flightNo", column = @Column(name = "FLIGHT_NO", nullable = false, length = 6) ),
			@AttributeOverride(name = "depAirportCode", column = @Column(name = "DEP_AIRPORT_CODE", nullable = false, length = 3) ),
			@AttributeOverride(name = "arrAirportCode", column = @Column(name = "ARR_AIRPORT_CODE", nullable = false, length = 3) ),
			@AttributeOverride(name = "depTime", column = @Column(name = "DEP_TIME", nullable = false, length = 3) ),
			@AttributeOverride(name = "depDate", column = @Column(name = "DEP_DATE", nullable = false, length = 3) ),
			@AttributeOverride(name = "clCode", column = @Column(name = "CL_CODE", nullable = false, length = 1) ) })
	@JsonUnwrapped
	private TksRemainingCaseId id;
	
	@Column(name = "COUNT_OF_SPARE_SEATS", nullable = false, precision = 3, scale = 0)
	private int countOfSpareSeats;
	
	public TksRemainingCase() {
	}

	public TksRemainingCase(TksRemainingCaseId id, int countOfSpareSeats) {
		super();
		this.id = id;
		this.countOfSpareSeats = countOfSpareSeats;
	}

	public TksRemainingCaseId getId() {
		return id;
	}

	public void setId(TksRemainingCaseId id) {
		this.id = id;
	}

	public int getCountOfSpareSeats() {
		return countOfSpareSeats;
	}

	public void setCountOfSpareSeats(int countOfSpareSeats) {
		this.countOfSpareSeats = countOfSpareSeats;
	}
	
}
