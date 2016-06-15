package com.flight.trs.controller.baseInfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flight.trs.model.entity.Carrier;
import com.flight.trs.service.basic.ICarrierService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月11日 上午12:50:48
 * @version V1.0
 */
@RestController
@RequestMapping("/carrier")
public class CarrierRestController {

	@Resource
	ICarrierService carrierService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Carrier> all() {
		return carrierService.findAll();
	}
	
	@RequestMapping(value="/get/{code}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Carrier get(@PathVariable String code) {
		return carrierService.findByCode(code);
	}

}
