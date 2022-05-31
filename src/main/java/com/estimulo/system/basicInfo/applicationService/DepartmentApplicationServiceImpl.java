package com.estimulo.system.basicInfo.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.estimulo.system.base.mapper.CodeDetailDAO;
import com.estimulo.system.base.to.CodeDetailTO;
import com.estimulo.system.basicInfo.mapper.DepartmentDAO;
import com.estimulo.system.basicInfo.to.DepartmentTO;

@Component
public class DepartmentApplicationServiceImpl implements DepartmentApplicationService {
	
	@Autowired
	private DepartmentDAO departmentDAO;
	@Autowired
	private CodeDetailDAO codeDetailDAO;
	
	
	public ArrayList<DepartmentTO> getDepartmentList(String searchCondition, String companyCode, String workplaceCode) {
			
		ArrayList<DepartmentTO> departmentList = null;
		HashMap<String,String> map = new HashMap<>();
		map.put("searchCondition",searchCondition);
		map.put("companyCode",companyCode);
		map.put("workplaceCode",workplaceCode);
		departmentList = departmentDAO.selectDepartmentList(map);

		return departmentList;
	}

	public String getNewDepartmentCode(String companyCode) {
		
		ArrayList<DepartmentTO> departmentList = null;
		String newDepartmentCode = null;

			departmentList = departmentDAO.selectDepartmentListByCompany(companyCode);

			TreeSet<Integer> departmentCodeNoSet = new TreeSet<>();

			for (DepartmentTO bean : departmentList) {

				if (bean.getDeptCode().startsWith("DPT-")) {

					try {

						Integer no = Integer.parseInt(bean.getDeptCode().split("DPT-")[1]);
						departmentCodeNoSet.add(no);

					} catch (NumberFormatException e) {

						// "DPT-" 다음 부분을 Integer 로 변환하지 못하는 경우 : 그냥 다음 반복문 실행

					}

				}

			}

			if (departmentCodeNoSet.isEmpty()) {
				newDepartmentCode = "DPT-" + String.format("%02d", 1);
			} else {
				newDepartmentCode = "DPT-" + String.format("%02d", departmentCodeNoSet.pollLast() + 1);
			}

		return newDepartmentCode;
	}


	public HashMap<String, Object> batchDepartmentListProcess(ArrayList<DepartmentTO> departmentList) {

		HashMap<String, Object> resultMap = new HashMap<>();

			ArrayList<String> insertList = new ArrayList<>();
			ArrayList<String> updateList = new ArrayList<>();
			ArrayList<String> deleteList = new ArrayList<>();

			CodeDetailTO detailCodeBean = new CodeDetailTO();

			for (DepartmentTO bean : departmentList) {

				String status = bean.getStatus();

				switch (status) {

				case "INSERT":

					// 새로운 부서번호 생성 후 bean 에 set
					String newDepartmentCode = getNewDepartmentCode(bean.getCompanyCode());
					bean.setDeptCode(newDepartmentCode);

					// 부서 테이블에 insert
					departmentDAO.insertDepartment(bean);
					insertList.add(bean.getDeptCode());

					// CODE_DETAIL 테이블에 Insert
					detailCodeBean.setDivisionCodeNo("CO-03");
					detailCodeBean.setDetailCode(bean.getDeptCode());
					detailCodeBean.setDetailCodeName(bean.getDeptName());

					codeDetailDAO.insertDetailCode(detailCodeBean);

					break;

				case "UPDATE":

					departmentDAO.updateDepartment(bean);
					updateList.add(bean.getDeptCode());

					// CODE_DETAIL 테이블에 Update
					detailCodeBean.setDivisionCodeNo("CO-03");
					detailCodeBean.setDetailCode(bean.getDeptCode());
					detailCodeBean.setDetailCodeName(bean.getDeptName());

					codeDetailDAO.updateDetailCode(detailCodeBean);

					break;

				case "DELETE":

					departmentDAO.deleteDepartment(bean);
					deleteList.add(bean.getDeptCode());

					// CODE_DETAIL 테이블에 Delete
					detailCodeBean.setDivisionCodeNo("CO-03");
					detailCodeBean.setDetailCode(bean.getDeptCode());
					detailCodeBean.setDetailCodeName(bean.getDeptName());

					codeDetailDAO.deleteDetailCode(detailCodeBean);

					break;

				}

			}

			resultMap.put("INSERT", insertList);
			resultMap.put("UPDATE", updateList);
			resultMap.put("DELETE", deleteList);
	
		return resultMap;
	}
	
}
