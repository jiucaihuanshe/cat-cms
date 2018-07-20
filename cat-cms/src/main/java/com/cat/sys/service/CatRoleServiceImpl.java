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
}
