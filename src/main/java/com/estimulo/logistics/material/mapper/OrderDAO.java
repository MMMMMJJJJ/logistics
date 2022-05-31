package com.estimulo.logistics.material.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.estimulo.logistics.material.to.OrderInfoTO;
@Mapper
public interface OrderDAO {
	
	 public HashMap<String,Object> getOrderList(HashMap<String, String> param);
	 
	 public HashMap<String,Object> getOrderDialogInfo(HashMap<String,String> param);
	 
	 public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery();
	 
	 public ArrayList<OrderInfoTO> getOrderInfoList(HashMap<String,String> param);

	 public void order(HashMap<String,String> param);	 
	 
	 public void optionOrder(HashMap<String,String> param);

	public void updateOrderInfo(HashMap<String,String> param);
}
