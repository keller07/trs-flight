package com.flight.trs.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月14日 下午10:24:04
 * @version V1.0
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class Flight {

	private String carrierCode;		//航空公司代号和名称，以及本航班航班号
	private String carrierName;		
	private String totalFlightNo;		

	private String depAirportCode;	//出发机场三字码和名称、登机航站楼以及起飞时间
	private String depAirportName;
	private String depTerminal;
	private String depTime;
	
	private String arrAirportCode;	//到达机场三字码和名称、接机航站楼以及降落时间
	private String arrAirportName;
	private String arrTerminal;
	private String arrTime;
	
	private String depDate;			//出发日期
	
	private String acCode;			//机型代号和名称
	private String acName;
	
	private String stopCodes;		//经停机场代号用'2(SHA,PEK)',及其对应名称'上海，北京'
	private String stopCityNames;	//
	private String mealService;		//餐食有无
	private float ticketFee;		//机票全价100%
	private float airportTax;		//机建费
	private float baf;				//燃油费
	private String duration;		//总飞行时长
	private float flightShort;		//飞行里程
	
	private String traFlightNo;		//中转航班航班号
	
	private String traAirportCode;	//中转机场代码、城市名、机场名
	private String traCityName;
	private String traAirportName;
	private String traArrTime;		//中转航班降落时间和接机航站楼
	private String traArrTerminal;	
	private String traDepTime;		//中转航班起飞时间和登机航站楼
	private String traDepTerminal;	
	
	private String traAcCode;		//中转航班乘坐飞机机型代号和名称
	private String traAcName;
	
	List<ClassSection> cls = new ArrayList<Flight.ClassSection>();			//舱位明细
	
	public Flight() {
		// TODO 
	}
	
	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getTotalFlightNo() {
		return totalFlightNo;
	}

	public void setTotalFlightNo(String totalFlightNo) {
		this.totalFlightNo = totalFlightNo;
	}

	public String getDepAirportCode() {
		return depAirportCode;
	}

	public void setDepAirportCode(String depAirportCode) {
		this.depAirportCode = depAirportCode;
	}

	public String getDepAirportName() {
		return depAirportName;
	}

	public void setDepAirportName(String depAirportName) {
		this.depAirportName = depAirportName;
	}

	public String getDepTerminal() {
		return depTerminal;
	}

	public void setDepTerminal(String depTerminal) {
		this.depTerminal = depTerminal;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getArrAirportCode() {
		return arrAirportCode;
	}

	public void setArrAirportCode(String arrAirportCode) {
		this.arrAirportCode = arrAirportCode;
	}

	public String getArrAirportName() {
		return arrAirportName;
	}

	public void setArrAirportName(String arrAirportName) {
		this.arrAirportName = arrAirportName;
	}
	
	public String getArrTerminal() {
		return arrTerminal;
	}

	public void setArrTerminal(String arrTerminal) {
		this.arrTerminal = arrTerminal;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getDepDate() {
		return depDate;
	}
	
	public String getAcCode() {
		return acCode;
	}

	public void setAcCode(String acCode) {
		this.acCode = acCode;
	}

	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public float getFlightShort() {
		return flightShort;
	}

	public void setFlightShort(float flightShort) {
		this.flightShort = flightShort;
	}

	public String getStopCodes() {
		return stopCodes;
	}

	public void setStopCodes(String stopCodes) {
		this.stopCodes = stopCodes;
	}

	public String getStopCityNames() {
		return stopCityNames;
	}

	public void setStopCityNames(String stopCityNames) {
		this.stopCityNames = stopCityNames;
	}

	public String getMealService() {
		return mealService;
	}

	public void setMealService(String mealService) {
		this.mealService = mealService;
	}
	
	public float getTicketFee() {
		return ticketFee;
	}

	public void setTicketFee(float ticketFee) {
		this.ticketFee = ticketFee;
	}

	public float getAirportTax() {
		return airportTax;
	}

	public void setAirportTax(float airportTax) {
		this.airportTax = airportTax;
	}

	public float getBaf() {
		return baf;
	}

	public void setBaf(float baf) {
		this.baf = baf;
	}

	public String getTraFlightNo() {
		return traFlightNo;
	}

	public void setTraFlightNo(String traFlightNo) {
		this.traFlightNo = traFlightNo;
	}

	public String getTraAirportCode() {
		return traAirportCode;
	}

	public void setTraAirportCode(String traAirportCode) {
		this.traAirportCode = traAirportCode;
	}

	public String getTraCityName() {
		return traCityName;
	}

	public void setTraCityName(String traCityName) {
		this.traCityName = traCityName;
	}
	
	public String getTraAirportName() {
		return traAirportName;
	}

	public void setTraAirportName(String traAirportName) {
		this.traAirportName = traAirportName;
	}

	public String getTraArrTime() {
		return traArrTime;
	}

	public void setTraArrTime(String traArrTime) {
		this.traArrTime = traArrTime;
	}

	public String getTraArrTerminal() {
		return traArrTerminal;
	}

	public void setTraArrTerminal(String traArrTerminal) {
		this.traArrTerminal = traArrTerminal;
	}

	public String getTraDepTime() {
		return traDepTime;
	}

	public void setTraDepTime(String traDepTime) {
		this.traDepTime = traDepTime;
	}

	public String getTraDepTerminal() {
		return traDepTerminal;
	}

	public void setTraDepTerminal(String traDepTerminal) {
		this.traDepTerminal = traDepTerminal;
	}

	public String getTraAcCode() {
		return traAcCode;
	}

	public void setTraAcCode(String traAcCode) {
		this.traAcCode = traAcCode;
	}

	public String getTraAcName() {
		return traAcName;
	}

	public void setTraAcName(String traAcName) {
		this.traAcName = traAcName;
	}

	public List<ClassSection> getCls() {
		return cls;
	}

	public void setCls(List<ClassSection> cls) {
		this.cls = cls;
	}

	@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
	public class ClassSection{
		private char clCode;			//舱位代码
		private String classOfService;	//舱位服务等级
		private float discount;			//享受折扣
		private int ticketFee;			//机票票面价	
		private int countOfSpareSeats;	//剩余票数
		
		public ClassSection() {
			super();
		}

		public ClassSection(char clCode, String classOfService, float discount, int ticketFee,
				int countOfSpareSeats) {
			super();
			this.clCode = clCode;
			this.classOfService = classOfService;
			this.discount = discount;
			this.ticketFee = ticketFee;
			this.countOfSpareSeats = countOfSpareSeats;
		}

		public char getClCode() {
			return clCode;
		}

		public void setClCode(char clCode) {
			this.clCode = clCode;
		}

		public String getClassOfService() {
			return classOfService;
		}

		public void setClassOfService(String classOfService) {
			this.classOfService = classOfService;
		}

		public float getDiscount() {
			return discount;
		}

		public void setDiscount(float discount) {
			this.discount = discount;
		}

		public int getTicketFee() {
			return ticketFee;
		}

		public void setTicketFee(int ticketFee) {
			this.ticketFee = ticketFee;
		}

		public int getCountOfSpareSeats() {
			return countOfSpareSeats;
		}

		public void setCountOfSpareSeats(int countOfSpareSeats) {
			this.countOfSpareSeats = countOfSpareSeats;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + clCode;
			result = prime * result + ((classOfService == null) ? 0 : classOfService.hashCode());
			result = prime * result + countOfSpareSeats;
			result = prime * result + Float.floatToIntBits(discount);
			result = prime * result + Float.floatToIntBits(ticketFee);
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
			ClassSection other = (ClassSection) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (clCode != other.clCode)
				return false;
			if (classOfService == null) {
				if (other.classOfService != null)
					return false;
			} else if (!classOfService.equals(other.classOfService))
				return false;
			if (countOfSpareSeats != other.countOfSpareSeats)
				return false;
			if (Float.floatToIntBits(discount) != Float.floatToIntBits(other.discount))
				return false;
			if (Float.floatToIntBits(ticketFee) != Float.floatToIntBits(other.ticketFee))
				return false;
			return true;
		}

		private Flight getOuterType() {
			return Flight.this;
		}
		
	}

}

