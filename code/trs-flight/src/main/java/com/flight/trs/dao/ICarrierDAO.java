package com.flight.trs.dao;


/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月22日 下午3:52:07
 * @version V1.0
 */
public interface ICarrierDAO {
	
	//查看承运人代码为指定值的承运人记录数
	public Long getRowCountWithCode(String code);
	
	//查看承运人名称为指定值的承运人记录数
	public Long getRowCountWithName(String name);
	
}
