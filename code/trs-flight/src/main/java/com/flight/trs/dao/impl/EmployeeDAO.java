package com.flight.trs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.IDeleteNotPhysicallyDAO;
import com.flight.trs.dao.IEmployeeDAO;
import com.flight.trs.model.entity.Employee;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月29日 上午1:09:27
 * @version V1.0
 */
@Repository
public class EmployeeDAO 
	extends BaseDAO<Employee, Long> 
	implements IEmployeeDAO, IDeleteNotPhysicallyDAO<Employee, Long> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findEmployeesByRole(String role) {
		// TODO
		final String hql = "from Employee e where e.role = :role";
		return (List<Employee>) findByNamedParam(hql, "role", role);
	}
	
	@Override
	public Employee findByNOAndPassword(Integer no, String password) {
		// TODO
		Employee emp = null;
		final String hql = "from Employee e where e.no = :no and e.password = :password";
		try {
			List<?> empList = findByNamedParams(
					hql
					,new String[]{"no","password"}
					,new Object[]{no,password}
					);
			if(empList != null && !empList.isEmpty())
				emp = (Employee) empList.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return emp;
	}
	
}
