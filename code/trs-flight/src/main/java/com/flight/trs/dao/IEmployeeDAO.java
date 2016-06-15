package com.flight.trs.dao;

import java.util.List;

import com.flight.trs.model.entity.Employee;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月29日 上午1:01:19
 * @version V1.0
 */
public interface IEmployeeDAO {

	//根据角色查找
	public List<Employee> findEmployeesByRole(String role);
	
	//通过员工工号和密码查找
	public Employee findByNOAndPassword(Integer no, String password);
	
}
