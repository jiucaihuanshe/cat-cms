package com.cat.sys.mapper;

import java.util.List;

import com.cat.sys.pojo.CatRole;

/**
 * 创建角色Mapper接口
 * @author wangchaofan
 *
 */
public interface CatRoleMapper {
	//查询所有角色信息
	List<CatRole> findPageObjects();
	//向表中写入数据
//	int insertObject(CatRole entity);
	//修改角色信息
//	int updateObject(CatRole entity);
	//根据id查询角色信息
//	CatRole findObjectById(Integer id);
}
