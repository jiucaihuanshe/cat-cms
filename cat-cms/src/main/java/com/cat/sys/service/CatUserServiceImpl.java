package com.cat.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cat.common.exception.ServiceException;
import com.cat.common.vo.PageObject;
import com.cat.sys.mapper.CatUserMapper;
import com.cat.sys.mapper.CatUserRoleMapper;
import com.cat.sys.pojo.CatUser;

@Service
public class CatUserServiceImpl implements CatUserService {
	@Autowired
	private CatUserMapper catUserMapper;
	@Autowired
	private CatUserRoleMapper catUserRoleMapper;
	@Override
	public PageObject<CatUser> findPageObjects(Integer pageCurrent, String username) {
		if(pageCurrent<1)throw new ServiceException("当前页码不能为负数");
		//1.获取当前页数据
		//1.1定义每页最多显示3条记录
		int pageSize=3;
		//1.2计算每页查询的起始位置
		int startIndex= (pageCurrent-1)*pageSize;
		List<CatUser> list = catUserMapper.findPageObjects(username, startIndex, pageSize);
		//2.获取总记录数,计算总页数
		int rowCount = catUserMapper.getRowCount(username);
		int pageCount = rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}
		//3.封装数据
		PageObject<CatUser> pageObject = new PageObject<>();
		pageObject.setRecords(list);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount(pageCount);
		pageObject.setPageCurrent(pageCurrent);
		return pageObject;
	}
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//1.参数有效性验证
		if(id==null||id<0){
			throw new ServiceException("id值无效");
		}
		if(valid==null||valid<0){
			throw new ServiceException("状态值无效");
		}
		//2.修改状态信息
		int rows = catUserMapper.validById(id, valid, modifiedUser);
		return rows;
	}
	@Override
	public int insertObject(CatUser entity, String roleIds) {
		String pwd = entity.getPassword();
		String username = entity.getUsername();
		if(StringUtils.isEmpty(username)){
			throw new ServiceException("用户名不能为空");
		}
		if(StringUtils.isEmpty(pwd)){
			throw new ServiceException("密码不能为空");
		}
		//将用户数据的密码转化为ByteSource对象
		ByteSource source = ByteSource.Util.bytes(pwd.getBytes());
		//创建一个随机盐值(采用盐值加密会更加的安全)
		String salt = UUID.randomUUID().toString();
		//对密码，盐值进行统一加密,产生一个密文对象
		SimpleHash simpleHash = new SimpleHash("MD5", source, salt);
		//设置密码与盐值到entity对象
		entity.setPassword(simpleHash.toHex());
		entity.setSalt(salt);
		//1.保存用户信息
		int rows = catUserMapper.insertObject(entity);
		//2.保存关系数据(用户与角色关系数据)
		catUserRoleMapper.insertObject(entity.getId(), roleIds.split(","));
		return rows;
	}
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		//查询用户信息
		CatUser user = catUserMapper.findObjectById(id);
		//查询角色信息
		List<Integer> roleIds = catUserRoleMapper.findRolesByUserId(id);
		//封装数据
		Map<String, Object> map = new HashMap<>();
		map.put("catUser", user);
		map.put("roleIds", roleIds);
		return map;
	}
	@Override
	public int updateObject(CatUser entity, String roleIds) {
		//1.参数业务验证
		if(entity==null){
			throw new ServiceException("更新对象不能为空");
		}
		if(entity.getId()==null){
			throw new ServiceException("更新用户id不能为空");
		}
		if(StringUtils.isEmpty(roleIds)){
			throw new ServiceException("用户角色不能为空");
		}
		String pwd = entity.getPassword();
		String username = entity.getUsername();
		if(StringUtils.isEmpty(username)){
			throw new ServiceException("用户名不能为空");
		}
		if(StringUtils.isEmpty(pwd)){
			throw new ServiceException("密码不能为空");
		}
		//将用户数据的密码转化为ByteSource对象
		ByteSource source = ByteSource.Util.bytes(pwd.getBytes());
		//创建一个随机盐值(采用盐值加密会更加的安全)
		String salt = UUID.randomUUID().toString();
		//对密码，盐值进行统一加密,产生一个密文对象
		SimpleHash simpleHash = new SimpleHash("MD5", source, salt);
		//设置密码与盐值到entity对象
		entity.setPassword(simpleHash.toHex());
		entity.setSalt(salt);
		//2.更新数据
		//2.1更新用户基本信息
		int rows = catUserMapper.updateObject(entity);
		//2.2删除用户角色关系数据
		catUserRoleMapper.deleteObject(entity.getId());
		//2.3重新建立关系
		catUserRoleMapper.insertObject(entity.getId(), roleIds.split(","));
		return rows;
	}
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<0){
			throw new ServiceException("id值无效");
		}
		int rows = catUserMapper.deleteObject(id);
		catUserRoleMapper.deleteObject(id);
		return rows;
	}

}
