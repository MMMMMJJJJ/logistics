package com.estimulo.logistics.production.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.estimulo.logistics.production.to.MpsTO;

@Mapper
public interface MpsDAO {

	public ArrayList<MpsTO> selectMpsList(HashMap<String,String> param);
	
	public ArrayList<MpsTO> selectMpsCount(String mpsPlanDate);
	
	public void insertMps(MpsTO TO);
	
	public void updateMps(MpsTO TO);
	
	public void changeMrpApplyStatus(HashMap<String,String> param);
	
	public void deleteMps(MpsTO TO);
	
}
