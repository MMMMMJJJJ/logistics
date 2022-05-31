package com.estimulo.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.estimulo.logistics.material.mapper.StockDAO;
import com.estimulo.logistics.material.to.StockChartTO;
import com.estimulo.logistics.material.to.StockLogTO;
import com.estimulo.logistics.material.to.StockTO;

@Component
public class StockApplicationServiceImpl implements StockApplicationService{

		// DAO 참조변수 선언
		@Autowired
		private StockDAO stockDAO;

		@Override
		public ArrayList<StockTO> getStockList() {

			ArrayList<StockTO> stockList = null;

				stockList = stockDAO.selectStockList();

			return stockList;
		}
	
		@Override
		public ArrayList<StockLogTO> getStockLogList(String startDate,String endDate) {

			HashMap<String, String> param = new HashMap<>();
			param.put("startDate", startDate);
			param.put("endDate", endDate);

			return stockDAO.selectStockLogList(param);
		}

		@Override
		public HashMap<String,Object> warehousing(ArrayList<String> orderNoArr) {
			
            HashMap<String,Object> resultMap = new HashMap<>();
				
			String orderNoList = orderNoArr.toString().replace("[", "").replace("]", "");
			
			resultMap.put("orderNoList",orderNoList);
			
			stockDAO.warehousing(resultMap);
				
        	return resultMap;
			
		}
		
		@Override
		public HashMap<String, Object> changeSafetyAllowanceAmount(String itemCode, String itemName,
				String safetyAllowanceAmount) {

			HashMap<String,String> param = new HashMap<>();
			param.put("itemCode",itemCode);
			param.put("itemName",itemName);
			param.put("safetyAllowanceAmount",safetyAllowanceAmount);

			stockDAO.updatesafetyAllowance(param);

			ModelMap modelMap = new ModelMap();
			modelMap.put("errorCode", param.get("ERROR_CODE")); //성공시 0
			modelMap.put("errorMsg", param.get("ERROR_MSG")); //## MPS등록완료 ##
			
			return modelMap;
		}
		
		@Override
		public ArrayList<StockChartTO> getStockChart() {

			ArrayList<StockChartTO> stockChart = null;

				stockChart = stockDAO.selectStockChart();

			return stockChart;
		}
		
}
