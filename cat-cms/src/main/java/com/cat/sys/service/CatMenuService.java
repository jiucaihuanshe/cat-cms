package com.cat.sys.service;

import java.util.List;
import java.util.Map;

import com.cat.common.vo.Node;
import com.cat.sys.pojo.CatMenu;

public interface CatMenuService {
	List<Map<String, Object>> findObjects();
	void deleteObject(Integer menuId);
	//查询节点的信息
	List<Node> findZtreeNodes();
	//保存菜单数据
	int insertObject(CatMenu entity);
	//根据id查询数据
	Map<String, Object> findObjectById(Integer id);
	//修改数据
	int updateObject(CatMenu entity);
}
