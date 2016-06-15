package com.flight.trs.service.basic;

import java.util.List;

import com.flight.trs.model.entity.Employee;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午9:18:17
 * @version V1.0
 */
public interface IEmployeeService {
	
	//添加员工
	public Employee add(String role, String password, String staticSalt, String dynamicSalt);
	
	//验证登录名
	public Employee verify(int empNO);
	
	//验证登录信息
	public Employee verify(int empNO,String password);

	//统计所有员工数量
	Long countAll();
	
	//查找所有员工
	List<Employee> findAll();
	
	//根据分页属性获取员工集合
	List<Employee> findWithPaging(int fromIndex, int recordAmount);

}
