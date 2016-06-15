package com.flight.trs.service.basic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flight.trs.model.entity.Employee;
import com.flight.trs.service.basic.IEmployeeService;
import com.flight.trs.dao.impl.EmployeeDAO;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月2日 下午7:28:27
 * @version V1.0
 */
@Repository("employeeService")
public class EmployeeService 
	implements IEmployeeService {

	@Resource(name="employeeDAO")
	EmployeeDAO employeeDAO;
	
	public EmployeeService() {
		// TODO 
	}
	
	//--------------------------------------------------
	//------------------- login ------------------------
	//--------------------------------------------------

	@Override
	public Employee verify(int empNO){
		return employeeDAO.get((long)empNO);
	}
	
	@Override
	public Employee verify(int empNO, String password) {
		// TODO
		return employeeDAO.findByNOAndPassword(empNO, password);
	}
	
	//-----------------------------------------------------------
	//---------------------- Employee Manage --------------------
	//-----------------------------------------------------------
 	
	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public Employee add(String role, String password, String staticSalt, String dynamicSalt) {
		// TODO
		Employee emp = new Employee(role, password, staticSalt, dynamicSalt);
		employeeDAO.save(emp);
		return emp;
	}
	
	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public Long countAll(){
		return employeeDAO.getRowCount();
	}

	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public List<Employee> findAll() {
		// TODO
		return null;
	}
	
	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public List<Employee> findWithPaging(int fromIndex, int recordAmount){
		return employeeDAO.findForPaging(fromIndex, recordAmount);
	}
	
	
}
