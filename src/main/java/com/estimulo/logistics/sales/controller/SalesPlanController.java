package com.estimulo.logistics.sales.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estimulo.logistics.sales.serviceFacade.SalesServiceFacade;
import com.estimulo.logistics.sales.to.SalesPlanTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/sales/*")
public class SalesPlanController {

	// serviceFacade 참조변수 선언
	@Autowired
	SalesServiceFacade salesServiceFacade;
	
	ModelMap map =  new ModelMap();

	// gson 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping(value="/searchSalesPlanInfo.do", method=RequestMethod.POST)
	public ModelMap searchSalesPlanInfo(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String dateSearchCondition = request.getParameter("dateSearchCondition");

		try {
			ArrayList<SalesPlanTO> salesPlanTOList = salesServiceFacade.getSalesPlanList(dateSearchCondition, startDate, endDate);

			map.put("gridRowJson", salesPlanTOList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		} 
		return map;
	}
	
	@RequestMapping(value="/batchSalesPlanListProcess.do", method=RequestMethod.POST)
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");

		try {

			ArrayList<SalesPlanTO> salesPlanTOList = gson.fromJson(batchList, new TypeToken<ArrayList<SalesPlanTO>>() {
			}.getType());

			HashMap<String, Object> resultMap = salesServiceFacade.batchSalesPlanListProcess(salesPlanTOList);

			map.put("result", resultMap);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		} 
		return map;
	}

}
