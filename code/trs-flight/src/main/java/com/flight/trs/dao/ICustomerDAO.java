package com.flight.trs.dao;

import com.flight.trs.model.entity.Customer;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月16日 下午7:40:10
 * @version V1.0
 */
public interface ICustomerDAO {
	
	//查看手机号码为指定值的客户记录数
	public Long getRowCountWithPhone(String phone);
	
	//查看邮箱为指定值的客户记录数
	public Long getRowCountWithEmail(String email);
	
	//根据指定手机号码和密码查询客户
	public Customer findCstmByPhoneAndPwd(String phone, String password);

	//根据指定Email和密码查询客户
	public Customer findCstmByEmailAndPwd(String email, String password);
	
	//根据指定手机号码查询客户
	public Customer findCstmByPhone(String phone);
	
	//根据指定Email查询客户
	public Customer findCstmByEmail(String email);
	
}
