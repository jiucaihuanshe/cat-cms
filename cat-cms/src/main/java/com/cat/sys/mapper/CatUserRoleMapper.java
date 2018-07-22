package com.cat.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 操作用户和角色的关系数据
 * @author lenovo
 *
 */
public interface CatUserRoleMapper {
	int insertObject(@Param("userId")Integer userId,@Param("roleIds")String[] roleIds);
	//根据userId查询角色信息的方法
	List<Integer> findRolesByUserId(Integer id);
	//添加删除方法，目的是在修改用户角色信息时，先删除原先的关系在添加新的关系
	int deleteObject(Integer userId);
}
