package com.flight.trs.model.info;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午12:27:11
 * @version V1.0
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class OrderInfo {

	String contactName;
	String contactPhone;
	String remark;
	List<Pax> paxes;
	
	public OrderInfo() {
		// TODO 
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Pax> getPaxes() {
		return paxes;
	}

	public void setPaxes(List<Pax> paxes) {
		this.paxes = paxes;
	}

	public OrderInfo(String contactName, String contactPhone, String remark, List<Pax> paxes) {
		super();
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.remark = remark;
		this.paxes = paxes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result + ((paxes == null) ? 0 : paxes.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		OrderInfo other = (OrderInfo) obj;
		if (contactName == null) {
			if (other.contactName != null)
				return false;
		} else if (!contactName.equals(other.contactName))
			return false;
		if (contactPhone == null) {
			if (other.contactPhone != null)
				return false;
		} else if (!contactPhone.equals(other.contactPhone))
			return false;
		if (paxes == null) {
			if (other.paxes != null)
				return false;
		} else if (!paxes.equals(other.paxes))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}

}
