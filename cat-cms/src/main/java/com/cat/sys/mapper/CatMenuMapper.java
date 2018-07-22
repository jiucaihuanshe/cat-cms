package com.cat.sys.mapper;

import java.util.List;
import java.util.Map;

import com.cat.common.vo.Node;
import com.cat.sys.pojo.CatMenu;

public interface CatMenuMapper {
	List<Map<String, Object>> findObjects();
	//查询要删除的菜单是否有子菜单
	int hasChild(Integer menuId);
	//删除菜单
	int deleteObject(Integer id);
	//查询节点信息
	List<Node> findZtreeNodes();
	//保存菜单数据
	int insertObject(CatMenu entity);
	//修改菜单时要先根据用户选择的id查询数据库，然后将数据填充到表单中
	Map<String, Object> findObjectById(Integer id);
	//修改数据
	int updateObject(CatMenu entity);
}
