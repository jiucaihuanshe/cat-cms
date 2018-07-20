package com.cat.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.sys.mapper.CatRoleMapper;
import com.cat.sys.pojo.CatRole;
@Service
public class CatRoleServiceImpl implements CatRoleService {
	@Autowired
	private CatRoleMapper catRoleMapper;
	@Override
	public List<CatRole> findPageObjects() {
		return catRoleMapper.findPageObjects();
	}
	@Override
	public int deleteObject(String ids) {
		//1.验证参数有效性
		if(ids==null || ids.length()==0){
			throw new RuntimeException("选中的记录不能为null");
		}
		//2.对参数数据进行转换
		String[] checkedIds = ids.split(",");
		//3.执行删除操作
		return catRoleMapper.deleteObject(checkedIds);
	}
}
