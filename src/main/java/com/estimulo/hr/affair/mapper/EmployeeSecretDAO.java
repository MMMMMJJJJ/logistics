package com.estimulo.hr.affair.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.estimulo.hr.affair.to.EmployeeSecretTO;

@Mapper
public interface EmployeeSecretDAO {

	public ArrayList<EmployeeSecretTO> selectEmployeeSecretList(HashMap<String,String> param);

	public EmployeeSecretTO selectUserPassWord(HashMap<String,String> map);

	public void insertEmployeeSecret(EmployeeSecretTO TO);
	
	public int selectUserPassWordCount(String companyCode, String empCode);

}
