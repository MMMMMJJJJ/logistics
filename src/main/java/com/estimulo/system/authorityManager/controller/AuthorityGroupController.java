package com.estimulo.system.authorityManager.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estimulo.system.authorityManager.serviceFacade.AuthorityManagerServiceFacade;
import com.estimulo.system.authorityManager.to.AuthorityGroupTO;
import com.estimulo.system.authorityManager.to.EmployeeAuthorityTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/authorityManager/*")
public class AuthorityGroupController {
	
	// gson
	private static Gson gson = new GsonBuilder().serializeNulls().create();
	
	ModelMap map =  new ModelMap();
	
	// SF참조변수
	@Autowired
	private AuthorityManagerServiceFacade authorityManagerServiceFacade;
	
	@RequestMapping(value="/getUserAuthorityGroup.do", method = RequestMethod.POST)
	public ModelMap getUserAuthorityGroup(HttpServletRequest request, HttpServletResponse response) {
		
		String empCode = request.getParameter("empCode");

		try {
			
			ArrayList<AuthorityGroupTO> authorityGroupTOList = authorityManagerServiceFacade.getUserAuthorityGroup(empCode);
			
			map.put("gridRowJson", authorityGroupTOList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());

		}
		
		return map;
	}
	
	@RequestMapping(value="/getAuthorityGroup.do", method = RequestMethod.POST)
	public ModelMap getAuthorityGroup(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			ArrayList<AuthorityGroupTO> authorityGroupTOList = authorityManagerServiceFacade.getAuthorityGroup();
			
			map.put("gridRowJson", authorityGroupTOList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");
			
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());
			
		}
		
		return map;
	}
	
	@RequestMapping(value="/insertEmployeeAuthorityGroup.do", method = RequestMethod.POST)
	public ModelMap insertEmployeeAuthorityGroup(HttpServletRequest request, HttpServletResponse response) {
		
		String empCode = request.getParameter("empCode");
		String insertDataList = request.getParameter("insertData");
		ArrayList<EmployeeAuthorityTO> employeeAuthorityTOList = gson.fromJson(insertDataList,
				new TypeToken<ArrayList<EmployeeAuthorityTO>>() {}.getType()); 
		
		try {
			
			authorityManagerServiceFacade.insertEmployeeAuthorityGroup(empCode, employeeAuthorityTOList);

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
