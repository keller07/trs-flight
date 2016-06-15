package com.flight.trs.dao;


/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午10:05:08
 * @version V1.0
 */
public interface IAircraftTypeDAO {

	//查看承运人代码为指定值的承运人记录数
	public Long getRowCountWithCode(String code);
	
}
