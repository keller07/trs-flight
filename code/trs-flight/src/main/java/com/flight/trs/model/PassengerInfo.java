package com.flight.trs.model;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月19日 下午9:28:53
 * @version V1.0
 */
public class PassengerInfo {

	private String paxName;
	private String paxType;
	private String paxBirthday;
	private String paxIdType;
	private String paxIdNo;
	
	public PassengerInfo() {
		// TODO 
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

	public String getPaxBirthday() {
		return paxBirthday;
	}

	public void setPaxBirthday(String paxBirthday) {
		this.paxBirthday = paxBirthday;
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

}
