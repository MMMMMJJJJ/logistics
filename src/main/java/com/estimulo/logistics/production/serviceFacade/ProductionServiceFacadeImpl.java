package com.estimulo.logistics.production.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estimulo.logistics.production.applicationService.MpsApplicationService;
import com.estimulo.logistics.production.applicationService.MrpApplicationService;
import com.estimulo.logistics.production.applicationService.WorkOrderApplicationService;
import com.estimulo.logistics.production.to.ContractDetailInMpsAvailableTO;
import com.estimulo.logistics.production.to.MpsTO;
import com.estimulo.logistics.production.to.MrpGatheringTO;
import com.estimulo.logistics.production.to.MrpTO;
import com.estimulo.logistics.production.to.ProductionPerformanceInfoTO;
import com.estimulo.logistics.production.to.SalesPlanInMpsAvailableTO;
import com.estimulo.logistics.production.to.WorkOrderInfoTO;

@Service
public class ProductionServiceFacadeImpl implements ProductionServiceFacade {
	
	@Autowired
	private MpsApplicationService mpsApplicationService;
	@Autowired
	private MrpApplicationService mrpApplicationService;
	@Autowired
	private WorkOrderApplicationService workOrderApplicationService;

	@Override
	public ArrayList<MpsTO> getMpsList(String startDate, String endDate, String includeMrpApply) {

		
		ArrayList<MpsTO> mpsTOList = null;

			mpsTOList = mpsApplicationService.getMpsList(startDate, endDate, includeMrpApply);

		return mpsTOList;
	}

	@Override
	public ArrayList<ContractDetailInMpsAvailableTO> getContractDetailListInMpsAvailable(String searchCondition,
			String startDate, String endDate) {

		
		ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList = null;

			contractDetailInMpsAvailableList = mpsApplicationService.getContractDetailListInMpsAvailable(searchCondition, startDate,
					endDate);

		return contractDetailInMpsAvailableList;

	}

	@Override
	public ArrayList<SalesPlanInMpsAvailableTO> getSalesPlanListInMpsAvailable(String searchCondition,
			String startDate, String endDate) {
		
		ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList = null;

			salesPlanInMpsAvailableList = mpsApplicationService.getSalesPlanListInMpsAvailable(searchCondition, startDate, endDate);


		return salesPlanInMpsAvailableList;

	}

	@Override
	public HashMap<String, Object> convertContractDetailToMps(
			ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList) {
		
		HashMap<String, Object> resultMap = null;

			resultMap = mpsApplicationService.convertContractDetailToMps(contractDetailInMpsAvailableList);
			
		return resultMap;

	}

	@Override
	public HashMap<String, Object> convertSalesPlanToMps(
			ArrayList<SalesPlanInMpsAvailableTO> contractDetailInMpsAvailableList) {
		
		HashMap<String, Object> resultMap = null;

			resultMap = mpsApplicationService.convertSalesPlanToMps(contractDetailInMpsAvailableList);
			
		return resultMap;

	}

	@Override
	public HashMap<String, Object> batchMpsListProcess(ArrayList<MpsTO> mpsTOList) {
		
		HashMap<String, Object> resultMap = null;

			resultMap = mpsApplicationService.batchMpsListProcess(mpsTOList);
			
		return resultMap;

	}

	@Override
	public ArrayList<MrpTO> searchMrpList(String mrpGatheringStatusCondition) {

		ArrayList<MrpTO> mrpList = null;


			mrpList = mrpApplicationService.searchMrpList(mrpGatheringStatusCondition);

		return mrpList;

	}

	@Override
	public ArrayList<MrpTO> searchMrpListAsDate(String dateSearchCondtion, String startDate, String endDate) {

		ArrayList<MrpTO> mrpList = null;

			mrpList = mrpApplicationService.searchMrpListAsDate(dateSearchCondtion, startDate, endDate);
			
		return mrpList;
	}

	@Override
	public ArrayList<MrpTO> searchMrpListAsMrpGatheringNo(String mrpGatheringNo) {
		
		ArrayList<MrpTO> mrpList = null;

			mrpList = mrpApplicationService.searchMrpListAsMrpGatheringNo(mrpGatheringNo);
			
		return mrpList;
	}

	@Override
	public ArrayList<MrpGatheringTO> searchMrpGatheringList(String dateSearchCondtion, String startDate,
			String endDate) {
		
		ArrayList<MrpGatheringTO> mrpGatheringList = null;

			mrpGatheringList = mrpApplicationService.searchMrpGatheringList(dateSearchCondtion, startDate, endDate);

		return mrpGatheringList;
	}

	@Override
	public HashMap<String, Object> openMrp(ArrayList<String> mpsNoArr) {

		HashMap<String, Object> resultMap = null;

			resultMap = mrpApplicationService.openMrp(mpsNoArr);

		return resultMap;
	}
	
	@Override
	public HashMap<String, Object> registerMrp(String mrpRegisterDate, ArrayList<String> mpsList) {

		HashMap<String, Object> resultMap = null;
		
			resultMap = mrpApplicationService.registerMrp(mrpRegisterDate, mpsList);
			
		return resultMap;
	}

	@Override
	public HashMap<String, Object> batchMrpListProcess(ArrayList<MrpTO> mrpTOList) {
		
		HashMap<String, Object> resultMap = null;

			resultMap = mrpApplicationService.batchMrpListProcess(mrpTOList);

		return resultMap;
	}

	@Override
	public ArrayList<MrpGatheringTO> getMrpGathering(ArrayList<String> mrpNoArr) {
		
		ArrayList<MrpGatheringTO> mrpGatheringList = null;

			mrpGatheringList = mrpApplicationService.getMrpGathering(mrpNoArr);
			
		return mrpGatheringList;
	}
	
	@Override
	public HashMap<String, Object> registerMrpGathering(String mrpGatheringRegisterDate,ArrayList<String> mrpNoArr,HashMap<String, String> mrpNoAndItemCodeMap) {
		
		HashMap<String, Object> resultMap = null;
		
			resultMap = mrpApplicationService.registerMrpGathering(mrpGatheringRegisterDate, mrpNoArr,mrpNoAndItemCodeMap);
			//											???????????????            	getRowData		MRP-NO : DK-AP01
		return resultMap;
	}
	
	@Override
	public HashMap<String, Object> getWorkOrderableMrpList() {
		
        HashMap<String,Object> resultMap = null;
        
			resultMap = workOrderApplicationService.getWorkOrderableMrpList();
			
		return resultMap;
		
	}
	
	@Override
	public HashMap<String,Object> getWorkOrderSimulationList(String mrpGatheringNoList ,String mrpNoList) {
		
        HashMap<String,Object> resultMap = null;
        
			resultMap = workOrderApplicationService.getWorkOrderSimulationList(mrpGatheringNoList,mrpNoList);
			
		return resultMap;
	}
	
	@Override
	public HashMap<String,Object> workOrder(String mrpGatheringNo,String workPlaceCode,String productionProcess,String mrpNo) {
		
        HashMap<String,Object> resultMap = null;
			
			resultMap = workOrderApplicationService.workOrder(mrpGatheringNo,workPlaceCode,productionProcess,mrpNo);
			
    	return resultMap;
		
	}

	@Override
	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList() {
		
		ArrayList<WorkOrderInfoTO> workOrderInfoList = null;

			workOrderInfoList = workOrderApplicationService.getWorkOrderInfoList();

		return workOrderInfoList;
		
	}

	@Override
	public HashMap<String,Object> workOrderCompletion(String workOrderNo,String actualCompletionAmount) {
		
        HashMap<String,Object> resultMap = null;		
		
			resultMap = workOrderApplicationService.workOrderCompletion(workOrderNo,actualCompletionAmount);

    	return resultMap;
		
	}

	@Override
	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList() {
		
		ArrayList<ProductionPerformanceInfoTO> productionPerformanceInfoList = null;

			productionPerformanceInfoList = workOrderApplicationService.getProductionPerformanceInfoList();
			
		return productionPerformanceInfoList;

	}

	@Override
	public HashMap<String,Object> showWorkSiteSituation(String workSiteCourse,String workOrderNo,String itemClassIfication) {

		
		HashMap<String,Object> showWorkSiteSituation = null;


			showWorkSiteSituation = workOrderApplicationService.showWorkSiteSituation(workSiteCourse,workOrderNo,itemClassIfication);

		return showWorkSiteSituation;

	}

	@Override
	public void workCompletion(String workOrderNo, String itemCode ,  ArrayList<String> itemCodeListArr) {

			workOrderApplicationService.workCompletion(workOrderNo,itemCode,itemCodeListArr);
	}

	@Override
	public HashMap<String, Object> workSiteLogList(String workSiteLogDate) {
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
			resultMap=workOrderApplicationService.workSiteLogList(workSiteLogDate);
			
		return resultMap;
	}

	
}
