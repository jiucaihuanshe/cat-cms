package com.cat.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cat.sys.pojo.CatUser;

public interface CatUserMapper {
	List<CatUser> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	int getRowCount(@Param("username")String username);
}
