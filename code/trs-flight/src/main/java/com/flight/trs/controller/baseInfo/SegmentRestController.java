package com.flight.trs.controller.baseInfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.flight.trs.model.entity.Segment;
import com.flight.trs.model.entity.simulated.TksRemainingCase;
import com.flight.trs.model.entity.simulated.TksRemainingCaseDAO;
import com.flight.trs.service.basic.ISegmentService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月13日 下午9:53:23
 * @version V1.0
 */
@RestController
@RequestMapping("/segment")
public class SegmentRestController {

	@Resource
	ISegmentService SegmentService;
	
	@RequestMapping(value="", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	public List<Segment> getAll() {
		return SegmentService.get();
	}

	@Resource
	TksRemainingCaseDAO dao;
	@RequestMapping(value="/test", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	public List<TksRemainingCase> test() {
		return dao.get("G88560", "BJS", "CAN", "0942", "20160615");
	}
	
	
	@RequestMapping(value="/", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	public List<Segment> get(@RequestParam String depAirportCode
			, @RequestParam String arrAirportCode
			, @RequestParam String depDate
			, @RequestParam(required=false) String carrierCode
			, @RequestParam(required=false) Character nonStop
			, @RequestParam(required=false) String earliest
			, @RequestParam(required=false) String latest
			) {
		carrierCode = null == carrierCode ? "/-/-" : carrierCode ;
		nonStop = null == nonStop ? 'n' : 'y' ;
		earliest = null == earliest ? "/-/-/-/-" : earliest ;
		latest = null == latest ? "/-/-/-/-" : latest ;
		return (List<Segment>) SegmentService.get(depAirportCode,arrAirportCode,depDate,carrierCode,nonStop,earliest,latest);
	}
	
}
