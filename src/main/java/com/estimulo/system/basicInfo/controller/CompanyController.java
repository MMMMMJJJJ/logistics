package com.estimulo.system.basicInfo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.estimulo.system.basicInfo.serviceFacade.BasicInfoServiceFacade;
import com.estimulo.system.basicInfo.to.CompanyTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/basicInfo/*")
public class CompanyController {

	// serviceFacade 참조변수 선언
	@Autowired
	private BasicInfoServiceFacade basicInfoServiceFacade;
	
	ModelMap map =  new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 JSON 변환
	
	@RequestMapping(value = "/searchCompany.do", method = RequestMethod.GET)
	public ModelMap searchCompanyList(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<CompanyTO> companyList = null;

		try {

			companyList = basicInfoServiceFacade.getCompanyList();
        //         companyList=    
			map.put("gridRowJson", companyList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");

		} catch (Exception e1) { 
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		}
		return map;
	}
	
	@RequestMapping(value = "/batchCompanyListProcess", method = RequestMethod.POST)
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");

		ArrayList<CompanyTO> companyList = gson.fromJson(batchList, new TypeToken<ArrayList<CompanyTO>>() {
		}.getType());

		try {

			HashMap<String, Object> resultMap = basicInfoServiceFacade.batchCompanyListProcess(companyList);

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
