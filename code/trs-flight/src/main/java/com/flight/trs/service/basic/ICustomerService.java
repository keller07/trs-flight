package com.flight.trs.service.basic;

import java.util.Collection;
import java.util.List;

import com.flight.trs.model.entity.Customer;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月18日 上午1:24:13
 * @version V1.0
 */
public interface ICustomerService {
	
	///---------------------------------------------
	//------------ login and register --------------
	///---------------------------------------------
	
	//验证注册手机号码是否可用
	public boolean isPhoneAvailable(String phone);
	
	//验证注册email是否可用
	public boolean isEmailAvailable(String email);
	
	//注册客户账号
	public Customer register(String phone, String password, String email, String staticSalt, String dynamicSalt);

	//验证登录信息
	public Customer verify(String phoneOrEmail,String password);
	
	//验证登录名
	public Customer verify(String phoneOrEmail);
	
	///---------------------------------------------
	//------------ customer manager
	///---------------------------------------------
	
	//根据ID删除客户
	public void deleteByID(Long id);
	
	//根据ID批量删除客户
	public void deleteByIDInBatch(Collection<Long> ids);
	
	//修改客户
	public void update(Customer customer);
	
	//查询所有客户信息
	public List<Customer> findAll();
	
	//根据分页属性获取客户集合
	List<Customer> findWithPaging(int fromIndex, int recordAmount);
	
	//根据ID查询客户
	public Customer findByID(Long id);
	
	//根据手机号码查询客户
	public Customer findByPhone(String phone);
	
	//根据Email查询客户
	public Customer findByEmail(String email);
	
}
