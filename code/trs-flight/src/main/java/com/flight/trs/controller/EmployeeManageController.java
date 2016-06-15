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

import com.flight.trs.model.entity.Employee;
import com.flight.trs.service.basic.impl.EmployeeService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月6日 上午11:48:40
 * @version V1.0
 */
@Controller("employeeManageController")
@RequestMapping("/employeeManage")
public class EmployeeManageController {

	@Resource(name="employeeService")
	private EmployeeService employeeService;
	
	@RequestMapping(value="/loadAll",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadEmployees(
			@RequestParam("fromIndex") int fromIndex
			, @RequestParam("recordAmount") int recordAmount) {
		
		Map<String, Object> resultMap = new HashMap<>();
		List<Employee> empList = employeeService.findWithPaging(fromIndex, recordAmount);
		resultMap.put("data", empList);
		return resultMap;
	}

}
