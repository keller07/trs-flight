package com.flight.trs.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flight.trs.model.entity.Airport;
import com.flight.trs.model.entity.Carrier;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.entity.FreightRate;
import com.flight.trs.model.entity.Order;
import com.flight.trs.model.entity.OrderVoyage;
import com.flight.trs.model.entity.OrderVoyageId;
import com.flight.trs.model.entity.Payment;
import com.flight.trs.model.entity.Segment;
import com.flight.trs.model.entity.Ticket;
import com.flight.trs.model.entity.Voyage;
import com.flight.trs.model.info.OrderInfo;
import com.flight.trs.model.info.Pax;
import com.flight.trs.model.info.PaymentInfo;
import com.flight.trs.service.basic.ICustomerService;
import com.flight.trs.service.basic.impl.CarrierService;
import com.flight.trs.service.basic.impl.VoyageService;
import com.flight.trs.service.impl.BookTicketService;
import com.flight.trs.service.impl.OrderService;
import com.flight.trs.util.DateUtil;
import com.flight.trs.util.OrderNoGenerator;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午12:07:42
 * @version V1.0
 */
@Controller
@RequestMapping("/orderform")
public class OrderController extends BaseController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Resource
	BookTicketService bookTicketService;
	
	@Resource
	ICustomerService customerService;
	
	@Resource
	VoyageService voyageService;
	
	@Resource
	OrderService orderService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView order(
			@RequestParam String orderNo,
			ModelAndView modelAndView){
		BigDecimal _orderNo = BigDecimal.valueOf(Long.valueOf(orderNo));
		Order order = orderService.findByOrderNo(_orderNo);
		if (null == order) {
			modelAndView.setViewName("order.query");
		}
		Customer customer = order.getCustomer();
		Subject currentUser = SecurityUtils.getSubject();
		long cutmid = Long.valueOf((String) currentUser.getPrincipal());
		if (cutmid!=customer.getId()) {
			modelAndView.setViewName("unauthorized");
			return modelAndView;
		}
		modelAndView.addObject("order",order);
		modelAndView.setViewName("customer/orderform");
		return modelAndView;
	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> order(
		@RequestBody OrderInfo orderInfo){
		
		Map<String, String> result = new HashMap<>();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("orderInfo", orderInfo);
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping(value="/reconfirm", method=RequestMethod.GET)
	public ModelAndView reconfirm(ModelAndView modelAndView){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String depCityName = (String) session.getAttribute("depCityName");
		String arrCityName = (String) session.getAttribute("arrCityName");
		String carrierName = (String) session.getAttribute("carrierName");
		String flightNo = (String) session.getAttribute("flightNo");
		String acName = (String) session.getAttribute("acName");
		String meals = (String) session.getAttribute("meals");
		String depDate = (String) session.getAttribute("depDate");
		String depTime = (String) session.getAttribute("depTime");
		String depAirportName = (String) session.getAttribute("depAirportName");
		String depTerminal = (String) session.getAttribute("depTerminal");
		String arrDate = (String) session.getAttribute("arrDate");
		String arrTime = (String) session.getAttribute("arrTime");
		String arrAirportName = (String) session.getAttribute("arrAirportName");
		String arrTerminal = (String) session.getAttribute("arrTerminal");
		String classOfService = (String) session.getAttribute("classOfService");
		float discount = (float) session.getAttribute("discount");
		int ticketFee = (int) session.getAttribute("ticketFee");
		int airportTax = (int) session.getAttribute("airportTax");
		int baf = (int) session.getAttribute("baf");
		int price = (int) session.getAttribute("price");
		
		modelAndView.addObject("depCityName",depCityName);
		modelAndView.addObject("arrCityName",arrCityName);
		
		modelAndView.addObject("carrierName",carrierName);
		modelAndView.addObject("flightNo",flightNo);
		modelAndView.addObject("acName",acName);
		modelAndView.addObject("meals",meals);
		
		modelAndView.addObject("depDate",depDate);
		modelAndView.addObject("depTime",depTime);
		modelAndView.addObject("depAirportName",depAirportName);
		modelAndView.addObject("depTerminal",null == depTerminal ? "" :"T"+depTerminal);
		modelAndView.addObject("arrDate",arrDate);
		modelAndView.addObject("arrTime",arrTime);
		modelAndView.addObject("arrAirportName",arrAirportName);
		modelAndView.addObject("arrTerminal",null == arrTerminal ? "" :"T"+arrTerminal);
		
		modelAndView.addObject("classOfService",classOfService);
		modelAndView.addObject("discount",discount);
		modelAndView.addObject("ticketFee",ticketFee);
		
		modelAndView.addObject("airportTax",airportTax);
		modelAndView.addObject("baf",baf);
		modelAndView.addObject("price",price);
		

		OrderInfo orderInfo = (OrderInfo) session.getAttribute("orderInfo");
		if (null == orderInfo) {
			modelAndView.setViewName("error");
			return modelAndView;
		}
		String contactName = orderInfo.getContactName();
		String contactPhone = orderInfo.getContactPhone();
		String remark = orderInfo.getRemark();
		List<Pax> paxes = orderInfo.getPaxes();
		
		modelAndView.addObject("contactName",contactName);
		modelAndView.addObject("contactPhone",contactPhone);
		modelAndView.addObject("remark",remark);
		modelAndView.addObject("paxNumber",paxes.size());
		modelAndView.addObject("paxes",paxes);
		modelAndView.addObject("totalPrice",price*paxes.size());
		
		modelAndView.setViewName("orderform.reconfirm");
		return modelAndView;
	}
	
	@RequestMapping(value="/do", method=RequestMethod.POST)
	public Map<String, String> reconfirm(
			@RequestParam String paymentMode){
		
		Map<String, String> result = new HashMap<>();

		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		String depDate =  (String) session.getAttribute("depDate");
		Segment segment = (Segment) session.getAttribute("segment");
		FreightRate freightRate = (FreightRate) session.getAttribute("freightRate");
		
		int price = (int) session.getAttribute("price");
		
		String userId = (String) currentUser.getPrincipal();
		Customer customer = customerService.findByID(Long.valueOf(userId));
		if (null == customer) {
			result.put("status", "error");
			return result;
		}
		
		OrderInfo orderInfo = (OrderInfo) session.getAttribute("orderInfo");
		if (null == orderInfo) {
			result.put("status", "failed");
			return result;
		}
		String contactName = orderInfo.getContactName();
		String contactPhone = orderInfo.getContactPhone();
		String remark = orderInfo.getRemark();
		List<Pax> paxes = orderInfo.getPaxes();
		int paxNumber = paxes.size();
		
		BigDecimal orderNo = getOrderNo();
		Date orderDate = new Date();
		int totalPrice = price*paxNumber;
		Order order = new Order(orderNo, customer, orderDate, contactName, contactPhone,
				totalPrice, remark);
		
		String PNR = bookTicketService.getPNR(order);
		if (null == PNR) {
			result.put("status", "get PNR failed");
			return result;
		}
		order.setPnr(PNR);
		order.setCustomer(customer);
		addTickets(order,orderInfo,freightRate);
		Map<String,Segment> flights = new HashMap<>();
		flights.put(depDate, segment);
		addVoyages(order, flights);
		
		PaymentInfo paymentInfo = bookTicketService.getPaymenInfo(order,paymentMode,totalPrice);
		
		addPayment(order, paymentInfo);
		
		onPayment(order,segment.getId().getCarrierCode());
		order.setLastModified(new Date());
		orderService.save(order);
		
		session.setAttribute("orderNo", order.getNo());
		session.setAttribute("businessTransaction", paymentInfo.getAmount());
		session.setAttribute("transSeqNo", paymentInfo.getTransSeqNo());
		
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping(value="/succeed", method=RequestMethod.GET)
	public ModelAndView succeed(ModelAndView modelAndView) throws Throwable{
		
		modelAndView.setViewName("orderform.succeed");
		return modelAndView;
		
		
	}
	
	private BigDecimal getOrderNo(){
		String orderNoStr;
		try {
			orderNoStr = OrderNoGenerator.getObject();
		} catch (Exception e) {
			// TODO
			return BigDecimal.ZERO;
		}
		return BigDecimal.valueOf(Long.valueOf(orderNoStr));
	}
	
	
	@Resource
	CarrierService carrierService;
	
	private String getTicketNo(String carrierCode){
		Carrier carrier = carrierService.findByCode(carrierCode);
		String ticketCode = carrier.getTicketCode();
		return ticketCode + OrderNoGenerator.getNo();
	}
	
	private void addTickets(Order order, OrderInfo orderInfo,FreightRate freightRate){
		List<Ticket> tickets = new ArrayList<>();
		Short airportTax = Short.valueOf(String.valueOf(freightRate.getAirportTax()));
		Short baf = Short.valueOf(String.valueOf(freightRate.getBaf()));
		int ticketFee = Integer.valueOf(String.valueOf(freightRate.getTicketFee()));
		for (Pax pax : orderInfo.getPaxes()) {
			Ticket ticket = new Ticket();
			ticket.setPaxName(pax.getPaxName());
			ticket.setPaxType(pax.getPaxType());
			ticket.setPaxIdType(pax.getPaxIdType());
			ticket.setPaxIdNo(pax.getPaxIdNo());
			ticket.setPaxBirthday(DateUtil.getDate(pax.getPaxBirthday()));
			ticket.setAirportTax(airportTax);
			ticket.setBaf(baf);
			ticket.setTicketFee(ticketFee);
			ticket.setOrder(order);
			ticket.setLastModified(new Date());
			tickets.add(ticket);
		}
		order.getTickets().addAll(tickets);
	}
	
	//添加行程信息
	private void addVoyages(Order order, Map<String,Segment> flights){
		int voyageCount = 1;
		Iterator<String> it = flights.keySet().iterator();
		while(it.hasNext()){
			String depDate = it.next();
			Segment segment = flights.get(depDate);
			
			String flightNo = segment.getId().getTotalFlightNo();
			Airport depAirport = segment.getDepAirport();
			Airport arrAirport = segment.getArrAirport();
			String depAirportCode = depAirport.getCode();
			String arrAirportCode = arrAirport.getCode();
			String simpleDepDate = DateUtil.getSimpleDateStr(depDate);
			String depDateTime = DateUtil.getDateTimeStrSimple(simpleDepDate,segment.getId().getDepTime());
			String arrDateTime = DateUtil.getDateTimeStrSimple(simpleDepDate,segment.getArrTime());
			
			if (null == segment.getTraAirport()) {
				Voyage voyageTo = voyageService.findByUK(flightNo, depDateTime, depAirportCode, arrAirportCode);
				if (null == voyageTo) {
					voyageTo = new Voyage(flightNo, depAirport, depDateTime, segment.getDepTerminal(), arrAirport,
							arrDateTime, segment.getArrTerminal(), segment.getAircraftType(), segment.getMealService());
					voyageService.add(voyageTo);
				}
				OrderVoyageId orderVotageIdTo = new OrderVoyageId(order.getNo(), voyageTo.getId(), voyageCount ++);
				OrderVoyage orderVoyageTo = new OrderVoyage(orderVotageIdTo, "onward");
				order.getOrderVoyages().add(orderVoyageTo);
			}
			else {
				String traFlightNo = null;
				String traAirportCode = null;
				String traArrTime = null;
				String traDepTime = null;
				String traArrDateTime = null;
				String traDepDateTime = null;
				traFlightNo = segment.getId().getCarrierCode() + segment.getTraFlightNo();
				traAirportCode = segment.getTraAirport().getCode();
				traArrTime = segment.getTraArrTime();
				traDepTime = segment.getTraDepTime();
				traArrDateTime = DateUtil.getDateTimeStrSimple(depDate,traArrTime);
				traDepDateTime = DateUtil.getDateTimeStrSimple(depDate,traDepTime);
				Voyage voyageTo = voyageService.findByUK(flightNo, depDateTime, depAirportCode, traAirportCode);
				if (null == voyageTo) {
					voyageTo = new Voyage(flightNo, segment.getDepAirport(), depDateTime, segment.getDepTerminal(), segment.getTraAirport(),
							traArrDateTime, segment.getTraArrTerminal(), segment.getAircraftType(), segment.getMealService());
					voyageService.add(voyageTo);
				}
				OrderVoyageId orderVotageIdTo = new OrderVoyageId(order.getNo(), voyageTo.getId(), voyageCount ++);
				OrderVoyage orderVoyageTo = new OrderVoyage(orderVotageIdTo, "onward");
				order.getOrderVoyages().add(orderVoyageTo);
				
				Voyage voyageTra = voyageService.findByUK(traFlightNo, traArrDateTime, traAirportCode, arrAirportCode);
				if (null == voyageTra) {
					voyageTo = new Voyage(traFlightNo, segment.getTraAirport(), traDepDateTime, segment.getTraDepTerminal(), segment.getArrAirport(),
							arrDateTime, segment.getArrTerminal(), segment.getTraAircraftType(), segment.getMealService());
					voyageService.add(voyageTra);
				}
				OrderVoyageId orderVotageIdTra = new OrderVoyageId(order.getNo(), voyageTra.getId(), voyageCount ++);
				OrderVoyage orderVoyageTra = new OrderVoyage(orderVotageIdTra, "transit");
				order.getOrderVoyages().add(orderVoyageTra);
			}
			
		}
	}
	
	private void addPayment(Order order, PaymentInfo paymentInfo){
		String paymentMode = paymentInfo.getPaymentMode();
		String paymentAccount = paymentInfo.getPaymentAccount();
		String transSeqNo = paymentInfo.getTransSeqNo();
		double amount = paymentInfo.getAmount();
		Date paymentTime = paymentInfo.getPaymentTime();
		Payment payment = new Payment(order, paymentMode, paymentAccount, transSeqNo,
				amount, paymentTime);
		payment.setOrder(order);
		order.getPayments().add(payment);
	}
	
	//支付完毕后更新机票和订单状态
	private void onPayment(Order order,String carrierCode){
		for (Ticket ticket : order.getTickets()) {
			String ticketNo = getTicketNo(carrierCode);
			logger.info(""+ticketNo);
			ticket.setTicketNo(ticketNo);
		}
		order.setStatus("finished");
	}
	
}
