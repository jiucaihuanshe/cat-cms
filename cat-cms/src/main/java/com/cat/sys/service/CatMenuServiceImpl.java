package com.cat.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cat.common.exception.ServiceException;
import com.cat.common.vo.Node;
import com.cat.sys.mapper.CatMenuMapper;
import com.cat.sys.pojo.CatMenu;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisCluster;
@Transactional
@Service
public class CatMenuServiceImpl implements CatMenuService {
	@Autowired
	private CatMenuMapper catMenuMapper;
	@Autowired
	private JedisCluster jedisCluster;
	private static final ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public List<Map<String, Object>> findObjects() {
		//redis缓存使用
		String key = "MENU_ID";
		String dataJSON = jedisCluster.get(key);
		List<Map<String, Object>> menuList = new ArrayList<>();
		try {
			if(StringUtils.isEmpty(dataJSON)){
				//证明空
				menuList = catMenuMapper.findObjects();
				System.out.println(menuList);
				String jsonResult = objectMapper.writeValueAsString(menuList);
				jedisCluster.set(key, jsonResult);
			}else{
				Map<String, Object>[] maps = objectMapper.readValue(dataJSON, Map[].class);
				for(Map<String, Object> map : maps){
					menuList.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void deleteObject(Integer menuId) {
		if(menuId==null){
			throw new ServiceException("删除菜单,菜单id不能为空");
		}
		int count = catMenuMapper.hasChild(menuId);
		if(count!=0)throw new ServiceException("请先删除子菜单或按钮");
		int i = catMenuMapper.deleteObject(menuId);
		if(i!=1)throw new ServiceException("删除菜单失败！");
	}
	@Transactional(readOnly=true)
	@Override
	public List<Node> findZtreeNodes() {
		return catMenuMapper.findZtreeNodes();
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int insertObject(CatMenu entity) {
		if(entity==null){
			throw new ServiceException("保存的数据不能为空");
		}
		int rows = catMenuMapper.insertObject(entity);
		if(rows!=1){
			throw new ServiceException("数据保存失败");
		}
		return rows;
	}
	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		if(id==null||id<0){
			throw new ServiceException("id值无效");
		}
		Map<String, Object> map = catMenuMapper.findObjectById(id);
		return map;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int updateObject(CatMenu entity) {
		if(entity==null){
			throw new ServiceException("更新的数据不能为空");
		}
		int rows = catMenuMapper.updateObject(entity);
		if(rows!=1){
			throw new ServiceException("数据更新失败");
		}
		return rows;
	}

}
