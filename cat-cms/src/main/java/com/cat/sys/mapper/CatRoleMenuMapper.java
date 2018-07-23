package com.cat.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CatRoleMenuMapper {
	List<Integer> findMenuIdsByRoleId(Integer roleId);
	/**向表中写入关系数据*/
	int insertObject(@Param("roleId")Integer roleId,@Param("menuIds")String[] menuIds);
	int deleteObjects(@Param("ids")String[] ids);
	int deleteObject(Integer roleId);
}
