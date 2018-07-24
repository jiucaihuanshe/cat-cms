package com.cat.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.common.vo.CheckBox;
import com.cat.common.vo.JsonResult;
import com.cat.common.vo.PageObject;
import com.cat.sys.pojo.CatRole;
import com.cat.sys.service.CatRoleService;

@Controller
@RequestMapping("/role/")
public class CatRoleController {
	@Autowired
	private CatRoleService catRoleService;
	@RequestMapping("listUI")
	public String listUI(){
		return "sys/role_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "sys/role_edit";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent,String name){
		PageObject<CatRole> pageObject = catRoleService.findPageObjects(pageCurrent,name);
		return new JsonResult(1, "query ok", pageObject);	//jackson,fastjson
	}
	
	//@RequiresPermissions("sys:role:delete")
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(String checkedIds){
		catRoleService.deleteObject(checkedIds);
		return new JsonResult(1,"delete ok");
	}
	
	@RequestMapping("doInsertObject")
	@ResponseBody
	public JsonResult doInsertObject(CatRole entity,String menuIds){
		catRoleService.insertObject(entity,menuIds);
		return new JsonResult(1, "insert ok");
	}
	
	@RequiresPermissions("sys:role:create")
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String, Object> map = catRoleService.findObjectById(id);
		return new JsonResult(1, "ok", map);
	}
	
	@RequiresPermissions("sys:role:update")
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(CatRole entity,String menuIds){
		entity.setModifiedUser("admin");
		catRoleService.updateObject(entity,menuIds);
		return new JsonResult(1, "update ok");
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		List<CheckBox> list= catRoleService.findObjects();
		return new JsonResult(1,"ok",list);
	}
}
