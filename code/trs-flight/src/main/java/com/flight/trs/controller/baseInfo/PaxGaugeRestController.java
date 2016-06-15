package com.flight.trs.controller.baseInfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flight.trs.model.entity.PassengerGauge;
import com.flight.trs.model.entity.PassengerGaugeId;
import com.flight.trs.service.basic.IPassengerGaugeService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月11日 上午12:52:52
 * @version V1.0
 */
@RestController
@RequestMapping("/paxGauge")
public class PaxGaugeRestController {

	@Resource
	IPassengerGaugeService passengerGaugeService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<PassengerGauge> get() {
		return passengerGaugeService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PassengerGauge get(
			@RequestParam String carrierCode
			, @RequestParam Character clCode) {
		PassengerGaugeId id = new PassengerGaugeId(carrierCode, clCode);
		return passengerGaugeService.get(id);
	}

}
