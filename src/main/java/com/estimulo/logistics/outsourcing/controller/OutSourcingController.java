package com.estimulo.logistics.outsourcing.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estimulo.logistics.outsourcing.to.OutSourcingTO;
import com.estimulo.logistics.outsourcing.serviceFacade.OutSourcingServiceFacade;

@RestController
@RequestMapping("/outsourcing/*")
public class OutSourcingController {

	// serviceFacade 참조변수 선언
	@Autowired
	private OutSourcingServiceFacade outSourcingServiceFacade;

	@RequestMapping(value="/getOutSourcingList.do" , method=RequestMethod.GET)
	public ModelMap searchOutSourcingList(HttpServletRequest request, HttpServletResponse response) {

		ModelMap map = new ModelMap();
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String customerCode = request.getParameter("customerCode");
		String itemCode = request.getParameter("itemCode");
		String materialStatus = request.getParameter("materialStatus");
		
		ArrayList<OutSourcingTO> outSourcingList = outSourcingServiceFacade.searchOutSourcingList(fromDate,toDate,customerCode,itemCode,materialStatus);
		map.put("outSourcingList", outSourcingList);
		map.put("errorCode", 1);
		map.put("errorMsg", "성공");

		return map;
	}
}
