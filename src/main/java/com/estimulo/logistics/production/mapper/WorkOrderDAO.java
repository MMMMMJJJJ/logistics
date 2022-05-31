package com.estimulo.logistics.production.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.estimulo.logistics.production.to.ProductionPerformanceInfoTO;
import com.estimulo.logistics.production.to.WorkOrderInfoTO;
import com.estimulo.logistics.production.to.WorkSiteLogTO;
@Mapper
public interface WorkOrderDAO {

	public HashMap<String,Object> getWorkOrderableMrpList(HashMap<String, Object> param);
	
	public HashMap<String,Object> getWorkOrderSimulationList(HashMap<String, Object> param);	
	
	public HashMap<String,Object> workOrder(HashMap<String,Object> param);
	
	public ArrayList<WorkOrderInfoTO> selectWorkOrderInfoList();
	
	public HashMap<String,Object> workOrderCompletion(HashMap<String,Object> param);
	
	public ArrayList<ProductionPerformanceInfoTO> selectProductionPerformanceInfoList();
	
	public HashMap<String,Object> selectWorkSiteSituation(HashMap<String,Object> param);
	
	public void updateWorkCompletionStatus(HashMap<String,String> param);
	
	public ArrayList<WorkSiteLogTO> workSiteLogList(String workSiteLogDate);
	
}
