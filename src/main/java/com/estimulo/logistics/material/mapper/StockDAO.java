package com.estimulo.logistics.material.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.estimulo.logistics.material.to.StockChartTO;
import com.estimulo.logistics.material.to.StockLogTO;
import com.estimulo.logistics.material.to.StockTO;
@Mapper
public interface StockDAO {
	
	public ArrayList<StockTO> selectStockList();
	
	public ArrayList<StockLogTO> selectStockLogList(HashMap<String, String> param);
	
	public void warehousing(HashMap<String, Object> param);
	
	public HashMap<String, Object> updatesafetyAllowance(HashMap<String,String> param);
	
	public ArrayList<StockChartTO> selectStockChart();
}
