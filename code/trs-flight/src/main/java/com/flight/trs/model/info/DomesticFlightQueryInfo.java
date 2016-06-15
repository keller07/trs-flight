package com.flight.trs.model.info;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月11日 下午5:05:18
 * @version V1.0
 */
public class DomesticFlightQueryInfo {
	
	private String domesticFlightType;
	private String depAirportCode;
	private String arrAirportCode;
	private String traAirportCode;
	private String depDate;
	private String retDate;
	
	public DomesticFlightQueryInfo(){
		
	}
	
	public String getDomesticFlightType() {
		return domesticFlightType;
	}
	public void setDomesticFlightType(String domesticFlightType) {
		this.domesticFlightType = domesticFlightType;
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
	public String getTraAirportCode() {
		return traAirportCode;
	}
	public void setTraAirportCode(String traAirportCode) {
		this.traAirportCode = traAirportCode;
	}
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public String getRetDate() {
		return retDate;
	}
	public void setRetDate(String retDate) {
		this.retDate = retDate;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(getClass().getName())
			.append("@")
			.append(Integer.toHexString(hashCode()))
			.append("{ ")
			.append("domesticFlightType:").append(domesticFlightType)
			.append("depAirportCode:").append(depAirportCode)
			.append("arrAirportCode:").append(arrAirportCode)
			.append("traAirportCode:").append(traAirportCode)
			.append("depDate:").append(depDate)
			.append("retDate:").append(retDate)
			.append("}");
		return sb.toString();
	}
	
}
