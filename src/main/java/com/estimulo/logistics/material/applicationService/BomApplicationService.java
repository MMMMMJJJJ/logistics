package com.estimulo.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.estimulo.logistics.material.to.BomDeployTO;
import com.estimulo.logistics.material.to.BomInfoTO;
import com.estimulo.logistics.material.to.BomTO;

public interface BomApplicationService {

	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode, String itemClassificationCondition);
	
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode);
	
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable();
	
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList);

}
