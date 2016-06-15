package com.flight.trs.model.entity;
// Generated 2016-1-16 17:01:14 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * OperatingLog generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "seqGeneratorOPERATING_LOG", sequenceName = "SEQ_OPERATING_LOG", allocationSize=1)
@Table(name = "TRS_OPERATING_LOG")
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties("serialVersionUID")
public class OperatingLog implements java.io.Serializable {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 348880690629258182L;
	private BigDecimal id;
	private long operatorId;
	private char operatorType;
	private Date operatingTime;
	private String tableName;
	private String tablePkValue;
	private char type;
	private String details;

	public OperatingLog() {
	}

	public OperatingLog(long operatorId, char operatorType, Date operatingTime, String tableName,
			String tablePkValue, char type) {
		this.operatorId = operatorId;
		this.operatorType = operatorType;
		this.operatingTime = operatingTime;
		this.tableName = tableName;
		this.tablePkValue = tablePkValue;
		this.type = type;
	}

	public OperatingLog(long operatorId, char operatorType, Date operatingTime, String tableName,
			String tablePkValue, char type, String details) {
		this.operatorId = operatorId;
		this.operatorType = operatorType;
		this.operatingTime = operatingTime;
		this.tableName = tableName;
		this.tablePkValue = tablePkValue;
		this.type = type;
		this.details = details;
	}

	@Id
	@GeneratedValue(generator = "seqGeneratorOPERATING_LOG")
	@Column(name = "ID", insertable = false, updatable = false, unique = true, nullable = false, precision = 38, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	@Column(name = "OPERATOR_ID", nullable = false, precision = 11, scale = 0)
	public long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "OPERATOR_TYPE", nullable = false, length = 1)
	public char getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(char operatorType) {
		this.operatorType = operatorType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATING_TIME", nullable = false)
	public Date getOperatingTime() {
		return this.operatingTime;
	}

	public void setOperatingTime(Date operatingTime) {
		this.operatingTime = operatingTime;
	}

	@Column(name = "TABLE_NAME", nullable = false, length = 32)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "TABLE_PK_VALUE", nullable = false, length = 200)
	public String getTablePkValue() {
		return this.tablePkValue;
	}

	public void setTablePkValue(String tablePkValue) {
		this.tablePkValue = tablePkValue;
	}

	@Column(name = "TYPE", nullable = false, length = 6)
	public char getType() {
		return this.type;
	}

	public void setType(char type) {
		this.type = type;
	}

	@Column(name = "DETAILS", nullable = false, length = 4000)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
