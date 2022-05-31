package com.estimulo.logistics.production.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.estimulo.logistics.production.mapper.WorkOrderDAO;
import com.estimulo.logistics.production.to.ProductionPerformanceInfoTO;
import com.estimulo.logistics.production.to.WorkOrderInfoTO;

import com.estimulo.logistics.production.to.WorkSiteLogTO;

@Component
public class WorkOrderApplicationServiceImpl implements WorkOrderApplicationService {

	// DAO 참조변수 선언
	// private static MpsDAO mpsDAO = MpsDAOImpl.getInstance();
	// private static MrpDAO mrpDAO = MrpDAOImpl.getInstance();	
	@Autowired
	private WorkOrderDAO workOrderDAO;
	

		@Override
	public HashMap<String,Object> getWorkOrderableMrpList() {

			HashMap<String, Object> map = new HashMap<>();
			
			workOrderDAO.getWorkOrderableMrpList(map);

			return map;
	}

	@Override
	public HashMap<String,Object> getWorkOrderSimulationList(String mrpGatheringNoList,String mrpNoList) {
		
		/*List를 string으로 바꿔서 매개변수로 넘겨받은 값을 수정해서 던져줌
		프로시저 안에서 정규표현식으로 ,기준으로 잘라서 사용하기 떄문에 여기서 잘라서 보내줄 필요 없음
		*/
		mrpGatheringNoList=mrpGatheringNoList.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");
		mrpNoList=mrpNoList.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");

        HashMap<String,Object> map = new HashMap<>();
        
        map.put("mrpGatheringNoList",mrpGatheringNoList);
        map.put("mrpNoList",mrpNoList);
        
		workOrderDAO.getWorkOrderSimulationList(map);
		
		return map;
		
	}

	@Override
	public HashMap<String,Object> workOrder(String mrpGatheringNo, String workPlaceCode,String productionProcess,String mrpNo) {
		//프로시저 안에서 정규표현식으로 ,기준으로 잘라서 사용하기 떄문에 여기서 잘라서 보내줄 필요 없음
		//그래서 괄호만 제거해 줌.
		mrpGatheringNo=mrpGatheringNo.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");
		mrpNo=mrpNo.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");
		HashMap<String, Object> map=new HashMap<>();
		
		map.put("mrpGatheringNo", mrpGatheringNo);
		map.put("workPlaceCode", workPlaceCode);
		map.put("productionProcess", productionProcess);
		map.put("mrpNo", mrpNo);
		
		workOrderDAO.workOrder(map);
		
		return map;
		
	}

	@Override
	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList() {


		ArrayList<WorkOrderInfoTO> workOrderInfoList = null;

			workOrderInfoList = workOrderDAO.selectWorkOrderInfoList();
			
		return workOrderInfoList;
		
	}

	@Override
	public HashMap<String,Object> workOrderCompletion(String workOrderNo,String actualCompletionAmount) {
		
		HashMap<String, Object> map=new HashMap<>();
		map.put("workOrderNo", workOrderNo);
		map.put("actualCompletionAmount", actualCompletionAmount);

		workOrderDAO.workOrderCompletion(map);
		
		return map;
		
	}
	
	@Override
	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList() {

		ArrayList<ProductionPerformanceInfoTO> productionPerformanceInfoList = null;

			productionPerformanceInfoList = workOrderDAO.selectProductionPerformanceInfoList();
			
		return productionPerformanceInfoList;
		
	}

	@Override
	public HashMap<String,Object> showWorkSiteSituation(String workSiteCourse,String workOrderNo,String itemClassIfication) {
		
		HashMap<String,Object> map = new HashMap<>();
	  	map.put("workOrderNo", workOrderNo);
	  	map.put("workSiteCourse", workSiteCourse);
	  	map.put("itemClassIfication", itemClassIfication);
		
	  	workOrderDAO.selectWorkSiteSituation(map);
	  	return map;
	}

	@Override
	public void workCompletion(String workOrderNo, String itemCode ,  ArrayList<String> itemCodeListArr) {
			
		String itemCodeList=itemCodeListArr.toString().replace("[", "").replace("]", "");
		
		HashMap<String,String> map = new HashMap<>();
	  	map.put("workOrderNo", workOrderNo);
	  	map.put("itemCode", itemCode);
	  	map.put("itemCodeList", itemCodeList);
		
		workOrderDAO.updateWorkCompletionStatus(map);
			
	}

	@Override
	public HashMap<String, Object> workSiteLogList(String workSiteLogDate) {

		ArrayList<WorkSiteLogTO> list = workOrderDAO.workSiteLogList(workSiteLogDate);
		HashMap<String,Object> resultMap = new HashMap<>();
   	  	
		resultMap.put("gridRowJson",list);
		
		return resultMap;
	}
		
}
