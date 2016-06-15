package com.flight.trs.controller.baseInfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flight.trs.model.entity.AircraftType;
import com.flight.trs.service.basic.IAircraftTypeService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月11日 上午12:47:56
 * @version V1.0
 */
@RestController
@RequestMapping("/aircraftType")
public class AircraftTypeRestController {

	@Resource
	IAircraftTypeService aircraftTypeService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<AircraftType> loadAll() {
		return aircraftTypeService.findAll();
//		Map<String, Object> result = new HashMap<>();
//		JsonTable<AircraftType> table = new AircraftTypeTable();
//		table.setEntities(aircraftTypes);
//		result.put("tableData", table);
//		return result;
	}
}
