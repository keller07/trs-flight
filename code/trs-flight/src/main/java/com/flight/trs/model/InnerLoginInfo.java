package com.flight.trs.model;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月2日 下午9:00:16
 * @version V1.0
 */
public class InnerLoginInfo {

	Integer empNo;
	String password;
	
	public InnerLoginInfo() {
		// TODO 
	}


	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName())
			.append("@")
			.append(Integer.toHexString(hashCode()))
			.append("{ ")
			.append("empNo=").append(getEmpNo())
			.append(", ")
			.append("password=").append(getPassword())
			.append(" }");
		return sb.toString();
	}
}
