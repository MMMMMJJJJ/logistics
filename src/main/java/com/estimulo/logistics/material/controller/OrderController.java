package com.estimulo.logistics.material.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estimulo.logistics.material.serviceFacade.MaterialServiceFacade;
import com.estimulo.logistics.material.to.OrderInfoTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/material/*")
public class OrderController {
	
	@Autowired
	private MaterialServiceFacade materialSF;
	
	private ModelMap resultMap = new ModelMap();
	
	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	
	@RequestMapping(value="/checkOrderInfo.do" , method=RequestMethod.POST)
	public ModelMap checkOrderInfo(HttpServletRequest request, HttpServletResponse response) {

		String orderNoListStr = request.getParameter("orderNoList");
		
		ArrayList<String> orderNoArr = gson.fromJson(orderNoListStr,new TypeToken<ArrayList<String>>(){}.getType());
		try {
			
			resultMap=materialSF.checkOrderInfo(orderNoArr);
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", e1.getMessage());

		} 
		return resultMap;
		
	}
	
	@RequestMapping(value="/getOrderList.do" , method=RequestMethod.POST)
	public ModelMap getOrderList(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		try {
			
			resultMap = (ModelMap)materialSF.getOrderList(startDate, endDate);

		} catch (Exception e1) {
			
			e1.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", e1.getMessage());

		}
		
		return resultMap;
		
	}
	
	@RequestMapping(value="/showOrderDialog.do" , method= RequestMethod.POST)
	public ModelMap openOrderDialog(HttpServletRequest request, HttpServletResponse response) {

		String mrpNoListStr = request.getParameter("mrpGatheringNoList");
	/*	ArrayList<String> mrpNoArr = gson.fromJson(mrpNoListStr, new TypeToken<ArrayList<String>>() {
		}.getType()); //제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
	*/
		try {
			
			resultMap =(ModelMap) materialSF.getOrderDialogInfo(mrpNoListStr);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", e1.getMessage());

		} 
		
		return resultMap;
	}

	@RequestMapping(value= "/showOrderInfo.do" ,method=RequestMethod.POST)
	public ModelMap showOrderInfo(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {

			ArrayList<OrderInfoTO> orderInfoList = materialSF.getOrderInfoList(startDate,endDate);
			resultMap.put("gridRowJson", orderInfoList);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", e1.getMessage());

		}

		return resultMap;
	}
	
	@RequestMapping(value="/searchOrderInfoListOnDelivery.do", method=RequestMethod.POST)
	public ModelMap searchOrderInfoListOnDelivery(HttpServletRequest request, HttpServletResponse response) {

		try {

			ArrayList<OrderInfoTO> orderInfoListOnDelivery = materialSF.getOrderInfoListOnDelivery();
			resultMap.put("gridRowJson", orderInfoListOnDelivery);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", e1.getMessage());

		} 
		return resultMap;
	}
	
	@RequestMapping(value="/order.do", method=RequestMethod.POST)
	public ModelMap order(HttpServletRequest request, HttpServletResponse response) {

		String mrpGatheringNoListStr = request.getParameter("mrpGatheringNoList");

		 ArrayList<String> mrpGaNoArr = gson.fromJson(mrpGatheringNoListStr , new TypeToken<ArrayList<String>>() {
	      }.getType());
		 	//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		try {
///////////////////////////////시작부
			resultMap = materialSF.order(mrpGaNoArr);

		} catch (Exception e1) {
			e1.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", e1.getMessage());

		}
		return resultMap;
	}
	
	@RequestMapping(value="/optionOrder.do", method=RequestMethod.POST)
	public ModelMap optionOrder(HttpServletRequest request, HttpServletResponse response) {

		try {

			String itemCode = request.getParameter("itemCode");
			String itemAmount = request.getParameter("itemAmount");

			resultMap =(ModelMap) materialSF.optionOrder(itemCode, itemAmount);

		} catch (Exception e1) {
			e1.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", e1.getMessage());

		}
		return resultMap;
	}


}
