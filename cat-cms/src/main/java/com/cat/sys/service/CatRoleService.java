package com.cat.sys.service;

import java.util.List;

import com.cat.common.vo.CheckBox;
import com.cat.common.vo.PageObject;
import com.cat.sys.pojo.CatRole;

public interface CatRoleService {
	PageObject<CatRole> findPageObjects(Integer pageCurrent,String name);
	int deleteObject(String ids);
	int insertObject(CatRole entity);
	CatRole findObjectById(Integer id);
	int updateObject(CatRole entity);
	List<CheckBox> findObjects();
}
