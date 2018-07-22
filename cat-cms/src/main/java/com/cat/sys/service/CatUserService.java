package com.cat.sys.service;

import java.util.Map;

import com.cat.common.vo.PageObject;
import com.cat.sys.pojo.CatUser;

public interface CatUserService {
	PageObject<CatUser> findPageObjects(Integer pageCurrent,String username);
	int validById(Integer id,Integer valid,String modifiedUser);
	//保存用户信息
	int insertObject(CatUser entity,String roleIds);
	Map<String, Object> findObjectById(Integer id);
	//修改用户信息
	int updateObject(CatUser entity,String roleIds);
	//删除用户信息
	int deleteObject(Integer id);
}
