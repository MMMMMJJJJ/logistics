package com.estimulo.logistics.material.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.estimulo.logistics.material.applicationService.BomApplicationService;
import com.estimulo.logistics.material.applicationService.OrderApplicationService;
import com.estimulo.logistics.material.applicationService.StockApplicationService;
import com.estimulo.logistics.material.to.BomDeployTO;
import com.estimulo.logistics.material.to.BomInfoTO;
import com.estimulo.logistics.material.to.BomTO;
import com.estimulo.logistics.material.to.OrderInfoTO;
import com.estimulo.logistics.material.to.StockChartTO;
import com.estimulo.logistics.material.to.StockLogTO;
import com.estimulo.logistics.material.to.StockTO;

@Service
public class MaterialServiceFacadeImpl implements MaterialServiceFacade {
	
	@Autowired
	private BomApplicationService bomAS;
	@Autowired
	private OrderApplicationService orderAS;
	@Autowired
	private StockApplicationService stockAS;
	
	ModelMap modelMap = new ModelMap();
	
	@Override
	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode,
			String itemClassificationCondition) {

		ArrayList<BomDeployTO> bomDeployList = null;

			bomDeployList = bomAS.getBomDeployList(deployCondition, itemCode, itemClassificationCondition);

		return bomDeployList;
	}

	@Override
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode) {

		ArrayList<BomInfoTO> bomInfoList = null;

			bomInfoList = bomAS.getBomInfoList(parentItemCode);

		return bomInfoList;
	}

	@Override
	public HashMap<String,Object> getOrderList(String startDate, String endDate) {

        HashMap<String,Object> resultMap = null;

		resultMap = orderAS.getOrderList(startDate, endDate);

		return resultMap;
	}

	@Override
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable() {

		ArrayList<BomInfoTO> allItemWithBomRegisterAvailable = null;

			allItemWithBomRegisterAvailable = bomAS.getAllItemWithBomRegisterAvailable();
			
		return allItemWithBomRegisterAvailable;
	}

	@Override
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList) {

		HashMap<String, Object> resultMap = null;


			resultMap = bomAS.batchBomListProcess(batchBomList);
			 
		return resultMap;

	}

	@Override
	public ModelMap getOrderDialogInfo(String mrpNoArr) {
        
		modelMap = orderAS.getOrderDialogInfo(mrpNoArr);
					
		return modelMap;

	}

	@Override
	public ModelMap order(ArrayList<String> mrpGaNoArr) {

		
        ModelMap resultMap = null;
		
			resultMap = orderAS.order(mrpGaNoArr);
		
    	return resultMap;
		
	}

	@Override
	public HashMap<String,Object> optionOrder(String itemCode, String itemAmount) {
		// TODO Auto-generated method stub
		
        HashMap<String,Object> resultMap = null;

			resultMap = orderAS.optionOrder(itemCode, itemAmount);
		
    	return resultMap;
		
	}

	@Override
	public ArrayList<StockTO> getStockList() {


		ArrayList<StockTO> stockList = null;

			stockList = stockAS.getStockList();

		return stockList;
	}

	@Override
	public ArrayList<StockLogTO> getStockLogList(String startDate, String endDate) {

		ArrayList<StockLogTO> stockLogList = null;

			stockLogList = stockAS.getStockLogList(startDate, endDate);

		return stockLogList;
	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery() {

		ArrayList<OrderInfoTO> orderInfoListOnDelivery = null;

			orderInfoListOnDelivery = orderAS.getOrderInfoListOnDelivery();

		return orderInfoListOnDelivery;

	}

	@Override
	public HashMap<String,Object> warehousing(ArrayList<String> orderNoArr) {

        HashMap<String,Object> resultMap = null;
		
			resultMap = stockAS.warehousing(orderNoArr);

    	return resultMap;
	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoList(String startDate, String endDate) {

		ArrayList<OrderInfoTO> orderInfoList  = null;

		return orderInfoList;

	}

	@Override
	public HashMap<String, Object> changeSafetyAllowanceAmount(String itemCode, String itemName,
			String safetyAllowanceAmount) {
		
		HashMap<String, Object> resultMap = null;

			resultMap = stockAS.changeSafetyAllowanceAmount(itemCode, itemName, safetyAllowanceAmount);
			
		return resultMap;
	}

	@Override
	public ModelMap checkOrderInfo(ArrayList<String> orderNoArr) {
		// TODO Auto-generated method stub
	
		ModelMap resultMap = null;
		
			resultMap = orderAS.checkOrderInfo(orderNoArr);
			
		return resultMap;
	}
	
	@Override
	public ArrayList<StockChartTO> getStockChart() {

		ArrayList<StockChartTO> stockChart  = null;

			stockChart = stockAS.getStockChart();
			
		return stockChart;

	}
}
