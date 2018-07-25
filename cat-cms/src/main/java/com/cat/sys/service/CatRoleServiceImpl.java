package com.cat.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.exception.ServiceException;
import com.cat.common.vo.CheckBox;
import com.cat.common.vo.PageObject;
import com.cat.sys.mapper.CatRoleMapper;
import com.cat.sys.mapper.CatRoleMenuMapper;
import com.cat.sys.pojo.CatRole;
import com.cat.sys.pojo.CatUser;
@Service
public class CatRoleServiceImpl implements CatRoleService {
	@Autowired
	private CatRoleMapper catRoleMapper;
	@Autowired
	private CatRoleMenuMapper catRoleMenuMapper;
	@Override
	public PageObject<CatRole> findPageObjects(Integer pageCurrent,String name) {
		if(pageCurrent<1)throw new ServiceException("当前页码不能为负数");
		//1.获取当前页数据
		//1.1定义每页最多显示3条记录
		int pageSize=10;
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
		int rows = catRoleMapper.deleteObject(checkedIds);
		catRoleMenuMapper.deleteObjects(checkedIds);
		return rows;
	}
	@Override
	public int insertObject(CatRole entity,String menuIds) {
		//判断新增的名称是否已存在 ??
		if("admin".equals(entity.getName())){
			throw new ServiceException("role's name already exists");
		}
		int rows= catRoleMapper.insertObject(entity);
		catRoleMenuMapper.insertObject(entity.getId(), menuIds.split(","));
		return rows;
	}
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		if(id==null){
			throw new ServiceException("id is null");
		}
		CatRole role = catRoleMapper.findObjectById(id);
		List<Integer> menuIds = catRoleMenuMapper.findMenuIdsByRoleId(id);
		Map<String, Object> map = new HashMap<>();
		map.put("role", role);
		map.put("menuIds", menuIds);
		return map;
	}
	@Override
	public int updateObject(CatRole entity,String menuIds) {
		if(entity==null){
			throw new ServiceException("更新的对象不能为空");
		}
		if(entity.getId()==null){
			throw new ServiceException("id不能为空");
		}
		int rows = catRoleMapper.updateObject(entity);
		
		catRoleMenuMapper.deleteObject(entity.getId());
		catRoleMenuMapper.insertObject(entity.getId(), menuIds.split(","));
		return rows;
	}
	@Override
	public List<CheckBox> findObjects() {
		return catRoleMapper.findObjects();
	}
}
