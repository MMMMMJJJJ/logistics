package com.estimulo.logistics.sales.to;

import java.util.ArrayList;

import com.estimulo.system.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ContractTO extends BaseTO {
	private String contractType;
	private String estimateNo;
	private String contractDate;
	private String description;
	private String contractRequester;
	private String customerCode;
	private String personCodeInCharge;
	private String contractNo;
	private ArrayList<ContractDetailTO> contractDetailTOList;

	//지우면 에러 나서 그냥 둠
	public ArrayList<ContractDetailTO> getContractDetailTOList() {
		return contractDetailTOList;
	}
	public void setContractDetailTOList(ArrayList<ContractDetailTO> contractDetailTOList) {
		this.contractDetailTOList = contractDetailTOList;
	}
	 
}