package com.estimulo.system.base.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estimulo.system.base.serviceFacade.BaseServiceFacade;
import com.estimulo.system.base.to.AddressTO;


@RestController
@RequestMapping("/base/*")
public class AddressController{

	// serviceFacade 참조변수 선언
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	
	@RequestMapping("/searchAddressList.do")
	public ModelMap searchAddressList(HttpServletRequest request, HttpServletResponse response) {

		String sidoName = request.getParameter("sidoName");
		String searchAddressType = request.getParameter("searchAddressType");
		String searchValue = request.getParameter("searchValue");
		String mainNumber = request.getParameter("mainNumber");

		ModelMap map = new ModelMap();

		try {
			ArrayList<AddressTO> addressList = baseServiceFacade.getAddressList(sidoName, searchAddressType, searchValue,
					mainNumber);

			map.put("addressList", addressList);
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
