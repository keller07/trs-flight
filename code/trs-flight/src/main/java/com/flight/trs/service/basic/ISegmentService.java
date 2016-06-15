package com.flight.trs.service.basic;

import java.util.List;

import com.flight.trs.model.entity.Segment;
import com.flight.trs.model.entity.SegmentId;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月13日 下午9:57:11
 * @version V1.0
 */
public interface ISegmentService {

	//查找所有航段
	List<Segment> get();
	//根据ID查询航段
	Segment get(SegmentId id);
	//按出发城市、到达城市和出发日期查找航段
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate);
	//查找航段、指明航空公司
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode);
	//查找航段、是否直达
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, char nonStop);
	//查找航段、指明出发时间段
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String earliest, String latest);
	//查找航段、指明航空公司、是否直达
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode, char nonStop);
	//查找航段、是否直达、指明出发时间段
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, char nonStop, String earliest, String latest);
	//查找航段、指明航空公司、指明出发时间段
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode, String earliest, String latest);
	//查找航班、指明航空公司、是否直达、指明出发时间段
	Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode, char nonStop, String earliest, String latest);
	//添加航段
	void post(Segment segments);
	//添加多个航段
	void post(Iterable<Segment> segments);
	//修改航段
	void put(Segment segments);
	//修改多个航段
	void put(Iterable<Segment> segments);
	//根据ID删除航段
	void delete(SegmentId id);
	//删除航段
	void delete(Segment segment);
	//删除多个航段
	void delete(Iterable<Segment> segments);
	
}
