package com.cat.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cat.sys.pojo.CatRole;

/**
 * 创建角色Mapper接口
 * 
 * 提醒@Param注解的作用是给参数命名,参数命名后就能根据名字得到参数值,正确的将参数传入sql语句中
 * @author wangchaofan
 *
 */
public interface CatRoleMapper {
	//查询所有角色信息
	List<CatRole> findPageObjects();
	//删除角色信息
	int deleteObject(@Param("ids")String[] ids);
	//向表中写入数据
//	int insertObject(CatRole entity);
	//修改角色信息
//	int updateObject(CatRole entity);
	//根据id查询角色信息
//	CatRole findObjectById(Integer id);
}
