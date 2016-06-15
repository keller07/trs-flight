package com.flight.trs.service.basic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.flight.trs.dao.ISegmentDAO;
import com.flight.trs.model.entity.Segment;
import com.flight.trs.model.entity.SegmentId;
import com.flight.trs.service.basic.ISegmentService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月14日 上午2:32:10
 * @version V1.0
 */
@Repository
public class SegmentService implements ISegmentService {

	@Resource
	ISegmentDAO segmentDAO;

	@Override
	public List<Segment> get() {
		return segmentDAO.get();
	}

	@Override
	public Segment get(SegmentId id) {
		return segmentDAO.get(id);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate, carrierCode);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, char nonStop) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate, nonStop);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String earliest,
			String latest) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate, earliest, latest);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			char nonStop) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate, carrierCode, nonStop);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, char nonStop,
			String earliest, String latest) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate, earliest, latest);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			String earliest, String latest) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate, carrierCode, earliest, latest);
	}

	@Override
	public Iterable<Segment> get(String depAirportCode, String arrAirportCode, String depDate, String carrierCode,
			char nonStop, String earliest, String latest) {
		return segmentDAO.get(depAirportCode, arrAirportCode, depDate, carrierCode, nonStop, earliest, latest);
	}

	@Override
	public void post(Segment segment) {
		segmentDAO.post(segment);
	}

	@Override
	public void post(Iterable<Segment> segments) {
		segmentDAO.post(segments);
	}

	@Override
	public void put(Segment segment) {
		segmentDAO.put(segment);
	}

	@Override
	public void put(Iterable<Segment> segments) {
		segmentDAO.put(segments);
	}

	@Override
	public void delete(SegmentId id) {
		segmentDAO.delete(id);
	}

	@Override
	public void delete(Segment segment) {
		segmentDAO.delete(segment);
	}

	@Override
	public void delete(Iterable<Segment> segments) {
		segmentDAO.delete(segments);
	}

}
