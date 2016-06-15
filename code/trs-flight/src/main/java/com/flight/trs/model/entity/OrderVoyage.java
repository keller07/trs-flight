package com.flight.trs.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月14日 下午5:35:35
 * @version V1.0
 */
@Entity
@Table(name = "TRS_ORDER_VOYAGE")
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"serialVersionUID"})
public class OrderVoyage implements java.io.Serializable {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -9013834644467368638L;

	@JsonUnwrapped
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "orderNo", column = @Column(name = "ORDER_NO", nullable = false, precision = 20, scale = 0))
			, @AttributeOverride(name = "voyageId", column = @Column(name = "VOYAGE_ID", nullable = false, precision = 20, scale = 0))
			, @AttributeOverride(name = "voyageIndex", column = @Column(name = "VOYAGE_INDEX", nullable = false, precision = 1, scale = 0))
			})
	private OrderVoyageId id;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_NO", nullable = false, insertable = false, updatable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "VOYAGE_ID", nullable = false, insertable = false, updatable = false)
	private Voyage voyage;
	
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	@Column(name = "VOYAGE_TYPE", nullable = false, length = 24)
	private String voyageType;
	
	public OrderVoyage() {
		// TODO 
	}

	public OrderVoyage(OrderVoyageId id, String voyageType) {
		super();
		this.id = id;
		this.voyageType = voyageType;
	}

	public OrderVoyageId getId() {
		return id;
	}

	public void setId(OrderVoyageId id) {
		this.id = id;
	}

	public String getVoyageType() {
		return voyageType;
	}

	public void setVoyageType(String voyageType) {
		this.voyageType = voyageType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((voyageType == null) ? 0 : voyageType.hashCode());
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
		OrderVoyage other = (OrderVoyage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (voyageType == null) {
			if (other.voyageType != null)
				return false;
		} else if (!voyageType.equals(other.voyageType))
			return false;
		return true;
	}
	
}
