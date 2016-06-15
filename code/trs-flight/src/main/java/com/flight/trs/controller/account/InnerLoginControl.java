package com.flight.trs.controller.account;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.flight.trs.model.InnerLoginInfo;
import com.flight.trs.model.entity.Employee;
import com.flight.trs.security.permission.Role;
import com.flight.trs.service.basic.impl.EmployeeService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月28日 下午9:15:37
 * @version V1.0
 */
@Controller
@RequestMapping("/inner")
@SessionAttributes({"empNo", "role"})
public class InnerLoginControl {

	private static final Logger logger = LoggerFactory.getLogger(InnerLoginControl.class);
	
	@Resource
	private EmployeeService employeeService;
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView){
		modelAndView.setViewName("employee/login");
		return modelAndView;
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView login(
			@RequestParam("empNo") String _empNo, 
            @RequestParam("password") String _password,
			ModelAndView modelAndView){
		if (null == _empNo || null == _password) {
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		Employee emp = employeeService.verify(Integer.valueOf(_empNo), _password);
		if( emp != null ){
			Integer empNo = emp.getNo();
			String role = emp.getRole();
			if( role != null ) {
				modelAndView.addObject("empNo", empNo);
				modelAndView.addObject("role", role);
				if ( emp.getRole().equals(Role.EmployeeRole.ADMINISTRATOR) ) {
					modelAndView.setViewName("employee/administrator/index");
				}
				else {
					modelAndView.setViewName("employee/salesman/index");
				}
			}
			else {
				//handle exception
				modelAndView.setViewName("error");
			}
			
		}else{
			//员工工号或密码错误，待处理
			modelAndView.setViewName("employee/login");
			modelAndView.addObject("status", "failed");
		}
		return modelAndView;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView modelAndView, SessionStatus sessionStatus){
		sessionStatus.setComplete();
		modelAndView.setViewName("employee/login");
		return modelAndView;
	}

}
