package com.flight.trs.model.info;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午12:11:01
 * @version V1.0
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class Pax {

	private String paxName;
	private String paxType;
	private String paxIdType;
	private String paxIdNo;
	private String paxBirthday;
	
	public Pax() {
		// TODO 
	}
	
	public Pax(String paxName, String paxType, String paxIdType, String paxIdNo, String paxBirthday) {
		super();
		this.paxName = paxName;
		this.paxType = paxType;
		this.paxIdType = paxIdType;
		this.paxIdNo = paxIdNo;
		this.paxBirthday = paxBirthday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paxBirthday == null) ? 0 : paxBirthday.hashCode());
		result = prime * result + ((paxIdNo == null) ? 0 : paxIdNo.hashCode());
		result = prime * result + ((paxIdType == null) ? 0 : paxIdType.hashCode());
		result = prime * result + ((paxName == null) ? 0 : paxName.hashCode());
		result = prime * result + ((paxType == null) ? 0 : paxType.hashCode());
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
		Pax other = (Pax) obj;
		if (paxBirthday == null) {
			if (other.paxBirthday != null)
				return false;
		} else if (!paxBirthday.equals(other.paxBirthday))
			return false;
		if (paxIdNo == null) {
			if (other.paxIdNo != null)
				return false;
		} else if (!paxIdNo.equals(other.paxIdNo))
			return false;
		if (paxIdType == null) {
			if (other.paxIdType != null)
				return false;
		} else if (!paxIdType.equals(other.paxIdType))
			return false;
		if (paxName == null) {
			if (other.paxName != null)
				return false;
		} else if (!paxName.equals(other.paxName))
			return false;
		if (paxType == null) {
			if (other.paxType != null)
				return false;
		} else if (!paxType.equals(other.paxType))
			return false;
		return true;
	}

	public String getPaxName() {
		return paxName;
	}

	public void setPaxName(String paxName) {
		this.paxName = paxName;
	}

	public String getPaxType() {
		return paxType;
	}

	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}

	public String getPaxIdType() {
		return paxIdType;
	}

	public void setPaxIdType(String paxIdType) {
		this.paxIdType = paxIdType;
	}

	public String getPaxIdNo() {
		return paxIdNo;
	}

	public void setPaxIdNo(String paxIdNo) {
		this.paxIdNo = paxIdNo;
	}

	public String getPaxBirthday() {
		return paxBirthday;
	}

	public void setPaxBirthday(String paxBirthday) {
		this.paxBirthday = paxBirthday;
	}

}
