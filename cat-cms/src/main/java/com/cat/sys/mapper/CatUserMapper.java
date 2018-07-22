package com.cat.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cat.sys.pojo.CatUser;

public interface CatUserMapper {
	//查询、分页
	List<CatUser> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	int getRowCount(@Param("username")String username);
	//启用/禁用
	int validById(@Param("id")Integer id,@Param("valid")Integer valid,@Param("modifiedUser")String modifiedUser);
	//新增
	int insertObject(CatUser entity);
	//根据id查询数据的方法
	CatUser findObjectById(Integer id);
	//修改
	int updateObject(CatUser entity);
	//删除
	int deleteObject(Integer id);
}
