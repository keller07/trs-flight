package com.flight.trs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flight.trs.model.entity.Customer;
import com.flight.trs.service.basic.impl.CustomerService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月21日 下午8:30:34
 * @version V1.0
 */
@Controller("customerManageController")
@RequestMapping("/customerManage")
public class CustomerManageController {

	@Resource(name="customerService")
	private CustomerService customerService;
	
	@RequestMapping(value="/loadWithPaging",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> loadWithPaging(
			@RequestParam("fromIndex") int fromIndex
			, @RequestParam("recordAmount") int recordAmount){
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Customer> cstmList = customerService.findWithPaging(fromIndex, recordAmount);
		result.put("data", cstmList);
		return result;
	}
	
}
