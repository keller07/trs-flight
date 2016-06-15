package com.flight.trs.model.entity;
// Generated 2016-1-16 17:01:14 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flight.trs.model.IDeleteNotPhysically;

/**
 * Voyage generated by hbm2java
 */
@Entity
@Table(name = "TRS_VOYAGE"
	, uniqueConstraints = @UniqueConstraint(
		columnNames = {"FLIGHT_NO", "DEP_AIRPORT_CODE", "ARR_AIRPORT_CODE", "DEP_DATETIME"}) 
)
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"serialVersionUID","isDeleted"})
public class Voyage implements java.io.Serializable 
	, IDeleteNotPhysically{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1773280697380090077L;
	
	@Id
	@SequenceGenerator(name = "seqGeneratorVOYAGE"
		, sequenceName = "SEQ_VOYAGE"
		, allocationSize=1
		)
	@GeneratedValue(generator = "seqGeneratorVOYAGE")
	@Column(name = "ID", insertable = false, updatable = false, unique = true
		, nullable = false, precision = 20, scale = 0
		)
	private BigDecimal id;
	
	@Column(name = "FLIGHT_NO", nullable = false, length = 6)
	private String flightNo;
	
	@ManyToOne
	@JoinColumn(name = "DEP_AIRPORT_CODE", nullable = false)
	private Airport depAirport;
	
	@Column(name = "DEP_DATETIME", nullable = false, length = 12)
	private String depDatetime;
	
	@Column(name = "DEP_TERMINAL", nullable = true, length = 3)
	private String depTerminal;
	
	@ManyToOne
	@JoinColumn(name = "ARR_AIRPORT_CODE", nullable = false)
	private Airport arrAirport;
	
	@Column(name = "ARR_DATETIME", nullable = false, length = 12)
	private String arrDatetime;
	
	@Column(name = "ARR_TERMINAL", nullable = true, length = 3)
	private String arrTerminal;
	
	@ManyToOne
	@JoinColumn(name = "AC_CODE")
	private AircraftType aircraftType;
	
	@Column(name = "MEAL_SERVICE", nullable = true, length = 1)
	private String mealService = "n";
	
	@Column(name = "IS_DELETED", nullable = false, length = 1)
	private char isDeleted = 'n';
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "voyage")
//	@OrderBy("id.orderNo asc")
//	private List<OrderVoyage> orderVoyages = new ArrayList<OrderVoyage>();

	public Voyage() {
	}

	public Voyage(String flightNo, String depDatetime, Airport depAirport, String arrDatetime, Airport arrAirport) {
		
		this.flightNo = flightNo;
		this.depAirport = depAirport;
		this.depDatetime = depDatetime;
		this.arrAirport = arrAirport;
		this.arrDatetime = arrDatetime;
	}

	public Voyage(String flightNo, Airport depAirport, String depDatetime, String depTerminal, Airport arrAirport,
			String arrDatetime, String arrTerminal, AircraftType aircraftType, String mealService) {
		super();
		this.flightNo = flightNo;
		this.depAirport = depAirport;
		this.depDatetime = depDatetime;
		this.depTerminal = depTerminal;
		this.arrAirport = arrAirport;
		this.arrDatetime = arrDatetime;
		this.arrTerminal = arrTerminal;
		this.aircraftType = aircraftType;
		this.mealService = mealService;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Airport getArrAirport() {
		return this.arrAirport;
	}

	public void setArrAirport(Airport arrAirport) {
		this.arrAirport = arrAirport;
	}

	
	public Airport getDepAirport() {
		return this.depAirport;
	}

	public void setDepAirport(Airport depAirport) {
		this.depAirport = depAirport;
	}

//	public List<OrderVoyage> getOrderVoyages() {
//		return this.orderVoyages;
//	}
//
//	public void OrderVoyages(List<OrderVoyage> orderVoyages) {
//		this.orderVoyages = orderVoyages;
//	}

	public String getFlightNo() {
		return this.flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDepDatetime() {
		return this.depDatetime;
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

	public String getArrDatetime() {
		return this.arrDatetime;
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

	public AircraftType getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	public String getMealService() {
		return mealService;
	}

	public void setMealService(String mealService) {
		this.mealService = mealService;
	}

	public char getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(char isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Transient
	@Override
	public void deleteNotPhysically() {
		// TODO
		setIsDeleted('y');
	}
}
