package com.cat.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.exception.ServiceException;
import com.cat.common.vo.PageObject;
import com.cat.sys.mapper.CatRoleMapper;
import com.cat.sys.pojo.CatRole;
@Service
public class CatRoleServiceImpl implements CatRoleService {
	@Autowired
	private CatRoleMapper catRoleMapper;
	@Override
	public PageObject<CatRole> findPageObjects(Integer pageCurrent,String name) {
		if(pageCurrent<1)throw new ServiceException("当前页码不能为负数");
		//1.获取当前页数据
		//1.1定义每页最多显示3条记录
		int pageSize=3;
		//1.2计算每页查询的起始位置
		int startIndex= (pageCurrent-1)*pageSize;
		List<CatRole> list = catRoleMapper.findPageObjects(name, startIndex, pageSize);
		//2.获取总记录数,计算总页数
		int rowCount = catRoleMapper.getRowCount(name);
		int pageCount = rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}
		//3.封装数据
		PageObject<CatRole> pageObject = new PageObject<>();
		pageObject.setRecords(list);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount(pageCount);
		pageObject.setPageCurrent(pageCurrent);
		return pageObject;
	}
	@Override
	public int deleteObject(String ids) {
		//1.验证参数有效性
		if(ids==null || ids.length()==0){
			throw new ServiceException("选中的记录不能为null");
		}
		//2.对参数数据进行转换
		String[] checkedIds = ids.split(",");
		//3.执行删除操作
		return catRoleMapper.deleteObject(checkedIds);
	}
	@Override
	public int insertObject(CatRole entity) {
		return catRoleMapper.insertObject(entity);
	}
	@Override
	public CatRole findObjectById(Integer id) {
		if(id==null){
			throw new ServiceException("id is null");
		}
		return catRoleMapper.findObjectById(id);
	}
	@Override
	public int updateObject(CatRole entity) {
		if(entity==null){
			throw new ServiceException("更新的对象不能为空");
		}
		if(entity.getId()==null){
			throw new ServiceException("id不能为空");
		}
		return catRoleMapper.updateObject(entity);
	}
}
