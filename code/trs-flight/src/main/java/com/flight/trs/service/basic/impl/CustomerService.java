package com.flight.trs.service.basic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flight.trs.dao.callable.IReflectSetter;
import com.flight.trs.dao.impl.CustomerDAO;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.service.basic.ICustomerService;
import com.flight.trs.util.RegexpValidator;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月18日 上午1:30:58
 * @version V1.0
 */
@Repository
public class CustomerService 
	implements ICustomerService {

	@Resource(name="customerDAO")
	CustomerDAO customerDAO;
	
	public CustomerService() {
		// TODO 
	}
	
	///---------------------------------------------
	//------------ login and register --------------
	///---------------------------------------------
	
	@Override
	public boolean isPhoneAvailable(String phone) {
		// TODO
		return customerDAO.getRowCountWithPhone(phone) == 0;
	}
	
	@Override
	public boolean isEmailAvailable(String email){
		return customerDAO.getRowCountWithEmail(email) == 0;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW
			,isolation = Isolation.DEFAULT
			)
	@Override
	public Customer register(String phone, String email, String password, String staticSalt, String dynamicSalt) {
		// TODO
		Customer cstm = new Customer(phone, email, password, staticSalt, dynamicSalt);
		customerDAO.save(cstm);
		return cstm;
	}

	@Override
	public Customer verify(String phoneOrEmail, String password) {
		// TODO
		Customer customer = null;
		if (RegexpValidator.isPhone(phoneOrEmail)) {
			customer = customerDAO.findCstmByPhoneAndPwd(phoneOrEmail, password);
		}
		else if (RegexpValidator.isEmail(phoneOrEmail)) {
			customer = customerDAO.findCstmByEmailAndPwd(phoneOrEmail, password);
		}
		else {
			//throws Exception
			throw new IllegalArgumentException("\""+phoneOrEmail+"\" is not a phone or an email!");
		}
		return customer;
	}

	///---------------------------------------------
	//------------ customer manager ----------------
	///---------------------------------------------
	
	
	@Resource(name="customerSetter")
	private IReflectSetter<Customer> reflectSetter;
	
	@Transactional
	@Override
	public void deleteByID(Long id) {
		// TODO
		Customer cstm = customerDAO.load(id);
		customerDAO.delete(cstm);
	}

	@Transactional
	@Override
	public void deleteByIDInBatch(Collection<Long> ids) {
		// TODO
		List<Customer> cstmList = new ArrayList<Customer>();
		Customer cstm = null;
		for (Long id : ids) {
			cstm = customerDAO.load(id);
			if (cstm == null) {
				//handle exception
				return;
			}
			cstmList.add(cstm);
		}
		customerDAO.deleteInBatch(cstmList);
	}
	
	@Transactional(propagation = Propagation.NEVER
			,isolation = Isolation.DEFAULT
			,readOnly = false
			)
	@Override
	public void update(Customer customer){
		customerDAO.update(customer);
	}
	
	@Override
	public List<Customer> findAll() {
		// TODO
		return customerDAO.loadAll();
	}

	@Override
	public List<Customer> findWithPaging(int fromIndex, int recordAmount){
		return customerDAO.findForPaging(fromIndex, recordAmount);
	}
	
	@Override
	public Customer findByID(Long id) {
		// TODO
		return customerDAO.get(id);
	}

	@Override
	public Customer findByPhone(String phone) {
		// TODO
		return customerDAO.findCstmByPhone(phone);
	}
	
	@Override
	public Customer findByEmail(String email) {
		// TODO
		return customerDAO.findCstmByEmail(email);
	}
	
	@Override
	public Customer verify(String phoneOrEmail) {
		// TODO
		if (RegexpValidator.isPhone(phoneOrEmail)) {
			return customerDAO.findCstmByPhone(phoneOrEmail);
		}
		if (RegexpValidator.isEmail(phoneOrEmail)) {
			return customerDAO.findCstmByEmail(phoneOrEmail);
		}else{
			throw new IllegalArgumentException("\""+phoneOrEmail+"\" is not a phone or an email!");
		}
	}

}
