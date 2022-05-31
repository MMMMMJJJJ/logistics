package com.estimulo.logistics.production.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estimulo.logistics.production.serviceFacade.ProductionServiceFacade;
import com.estimulo.logistics.production.to.MrpGatheringTO;
import com.estimulo.logistics.production.to.MrpTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/production/*")
public class MrpController{
	
	@Autowired
	private ProductionServiceFacade productionServiceFacade;
	
	ModelMap map =  new ModelMap();
	
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // �냽�꽦媛믪씠 null �씤 �냽�꽦�룄 json 蹂��솚
	
	@RequestMapping(value="/getMrpList.do", method=RequestMethod.POST)
	public ModelMap getMrpList(HttpServletRequest request, HttpServletResponse response) {
		
		String mrpGatheringStatusCondition = request.getParameter("mrpGatheringStatusCondition");	
		String dateSearchCondition = request.getParameter("dateSearchCondition");
		String mrpStartDate = request.getParameter("mrpStartDate");
		String mrpEndDate = request.getParameter("mrpEndDate");
		String mrpGatheringNo = request.getParameter("mrpGatheringNo");
	
		try {

			ArrayList<MrpTO> mrpList = null;
			
		
			if(mrpGatheringStatusCondition != null ) {
				
				mrpList = productionServiceFacade.searchMrpList(mrpGatheringStatusCondition);
				
			} else if (dateSearchCondition != null) {
				
				mrpList = productionServiceFacade.searchMrpListAsDate(dateSearchCondition, mrpStartDate, mrpEndDate);
				
			} else if (mrpGatheringNo != null) {
				
				mrpList = productionServiceFacade.searchMrpListAsMrpGatheringNo(mrpGatheringNo);
				
			}
			
			map.put("gridRowJson", mrpList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		}
		return map;
	}
	
	@RequestMapping(value="/openMrp.do", method=RequestMethod.POST)
	public ModelMap openMrp(HttpServletRequest request, HttpServletResponse response) {
		
		String mpsNoListStr = request.getParameter("mpsNoList"); 
		
		ArrayList<String> mpsNoArr = gson.fromJson(mpsNoListStr,
				new TypeToken<ArrayList<String>>() { }.getType());		

		try {
			
			HashMap<String,Object> result = new HashMap<>();
			result= productionServiceFacade.openMrp(mpsNoArr);

			map.put("gridRowJson",result);
			map.put("errorCode",result.get("ERROR_CODE"));
			map.put("errorMsg",result.get("ERROR_MSG"));
		} catch (Exception e1) {
			
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		}
		
		return map;
	}

	@RequestMapping(value="/registerMrp.do", method=RequestMethod.POST)
	public ModelMap registerMrp(HttpServletRequest request, HttpServletResponse response) {
		
		String batchList = request.getParameter("batchList");  // mrp 모의전개 정보 
		String mrpRegisterDate = request.getParameter("mrpRegisterDate");  // 소요량 전개 일자 

		ArrayList<String> mpsList	= gson.fromJson(batchList, 
				new TypeToken<ArrayList<String>>() { }.getType()); // 각각의 json이 to객체가 되어 AraryList에 담김
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용

		try {
			HashMap<String, Object> resultMap = productionServiceFacade.registerMrp(mrpRegisterDate, mpsList);	 
			
			map.put("result", resultMap);
			map.put("errorCode", 0);
			map.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		} 
		return map;
	}
	
	
	@RequestMapping(value="/getMrpGatheringList.do", method=RequestMethod.POST)
	public ModelMap getMrpGatheringList(HttpServletRequest request, HttpServletResponse response) {
		
		String mrpNoList = request.getParameter("mrpNoList");
		
		ArrayList<String> mrpNoArr = gson.fromJson(mrpNoList,
				new TypeToken<ArrayList<String>>() { }.getType());				
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용

		try {

			ArrayList<MrpGatheringTO> mrpGatheringList = productionServiceFacade.getMrpGathering(mrpNoArr);
			
			map.put("gridRowJson", mrpGatheringList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		} 
		return map;
	}
	
	
	@RequestMapping(value="/registerMrpGathering.do", method=RequestMethod.POST)
	public ModelMap registerMrpGathering(HttpServletRequest request, HttpServletResponse response) {

		String mrpGatheringRegisterDate = request.getParameter("mrpGatheringRegisterDate");
		String mrpNoList = request.getParameter("mrpNoList");
		String mrpNoAndItemCodeList = request.getParameter("mrpNoAndItemCodeList");
		ArrayList<String> mrpNoArr = gson.fromJson(mrpNoList,
				new TypeToken<ArrayList<String>>() { }.getType());
		HashMap<String, String> mrpNoAndItemCodeMap =  gson.fromJson(mrpNoAndItemCodeList, //mprNO : ItemCode 
              new TypeToken<HashMap<String, String>>() { }.getType());

		try {

			HashMap<String, Object> resultMap  = productionServiceFacade.registerMrpGathering(mrpGatheringRegisterDate, mrpNoArr,mrpNoAndItemCodeMap);	 
//			선택한날짜                  				getRowData		MRP-NO : DK-AP01
			
			
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
	
	@RequestMapping(value="/searchMrpGathering.do", method=RequestMethod.POST)
	public ModelMap searchMrpGathering(HttpServletRequest request, HttpServletResponse response) {

		String searchDateCondition = request.getParameter("searchDateCondition");
		String startDate = request.getParameter("mrpGatheringStartDate");
		String endDate = request.getParameter("mrpGatheringEndDate");

		try {

			ArrayList<MrpGatheringTO> mrpGatheringList = 
					productionServiceFacade.searchMrpGatheringList(searchDateCondition, startDate, endDate);
			
			map.put("gridRowJson", mrpGatheringList);
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
