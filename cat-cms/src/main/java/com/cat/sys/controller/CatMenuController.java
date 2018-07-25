package com.cat.sys.controller;

import java.util.List;
import java.util.Map;

import javax.swing.plaf.ListUI;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.common.vo.JsonResult;
import com.cat.common.vo.Node;
import com.cat.sys.pojo.CatMenu;
import com.cat.sys.pojo.CatUser;
import com.cat.sys.service.CatMenuService;

@Controller
@RequestMapping("/menu/")
public class CatMenuController {
	@Autowired
	private CatMenuService catMenuService;
	CatUser catUser = (CatUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
	@RequestMapping("listUI")
	public String ListUI(){
		return "sys/menu_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "sys/menu_edit";
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		List<Map<String, Object>> list = catMenuService.findObjects();
		return new JsonResult(1, "ok", list);
	}
	@RequiresPermissions("sys:menu:delete")
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer menuId){
		catMenuService.deleteObject(menuId);
		return new JsonResult(1,"delete ok");
	}
	@RequestMapping("treeUI")
	@ResponseBody
	public JsonResult treeUI(){
		List<Node> list = catMenuService.findZtreeNodes();
		return new JsonResult(1, "ok",list);
	}
	@RequiresPermissions("sys:menu:create")
	@RequestMapping("doInsertObject")
	@ResponseBody
	public JsonResult doInsertObject(CatMenu entity){
		entity.setCreatedUser(catUser.getUsername());
		catMenuService.insertObject(entity);
		return new JsonResult(1, "insert ok");
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String, Object> map = catMenuService.findObjectById(id);
		return new JsonResult(1, "ok",map);
	}
	@RequiresPermissions("sys:menu:update")
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(CatMenu entity){
		entity.setModifiedUser(catUser.getUsername());
		catMenuService.updateObject(entity);
		return new JsonResult(1, "update ok");
	}
}
