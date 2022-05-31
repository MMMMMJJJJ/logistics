package com.estimulo.system.base.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estimulo.system.base.mapper.AddressDAO;
import com.estimulo.system.base.to.AddressTO;

@Component
public class AddressApplicationServiceImpl implements AddressApplicationService {

	// DAO 참조변수
	@Autowired
	private AddressDAO addressDAO;

	@Override
	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue,
			String mainNumber) {

		ArrayList<AddressTO> addressList = null;
		HashMap<String,String> map = new HashMap<>();
			String sidoCode = addressDAO.selectSidoCode(sidoName);

			switch (searchAddressType) {

			case "roadNameAddress":

				String buildingMainNumber = mainNumber;
				map.put("sidoCode",sidoCode);
				map.put("searchValue",searchValue);
				map.put("buildingMainNumber",buildingMainNumber);
				addressList = addressDAO.selectRoadNameAddressList(map);

				break;

			case "jibunAddress":

				String jibunMainAddress = mainNumber;
				map.put("sidoCode",sidoCode);
				map.put("searchValue",searchValue);
				map.put("jibunMainAddress",jibunMainAddress);
				addressList = addressDAO.selectJibunAddressList(sidoCode, searchValue, jibunMainAddress);

				break;

			}
 
		return addressList;

	}

}
