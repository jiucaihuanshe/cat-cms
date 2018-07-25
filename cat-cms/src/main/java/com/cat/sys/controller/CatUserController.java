package com.cat.sys.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.common.vo.JsonResult;
import com.cat.common.vo.PageObject;
import com.cat.sys.pojo.CatUser;
import com.cat.sys.service.CatUserService;

@Controller
@RequestMapping("/user/")
public class CatUserController {
	@Autowired
	private CatUserService catUserService;
	CatUser catUser = (CatUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
	@RequestMapping("listUI")
	public String listUI(){
		return "sys/user_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "sys/user_edit";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent,String username){
		PageObject<CatUser> pageObject = catUserService.findPageObjects(pageCurrent,username);
		return new JsonResult(1, "query ok", pageObject);	//jackson,fastjson
	}
	@RequiresPermissions("sys:user:valid")
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id,Integer valid){
		catUserService.validById(id, valid, catUser.getUsername());
		return new JsonResult(1, "valid ok");
	}
	@RequiresPermissions("sys:user:create")
	@RequestMapping("doInsertObject")
	@ResponseBody
	public JsonResult doInsertObject(CatUser entity,String roleIds){
		entity.setCreatedUser(catUser.getUsername());
		entity.setValid(1);//用户新增完成为启用状态
		catUserService.insertObject(entity, roleIds);
		return new JsonResult(1, "insert ok");
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String, Object> map = catUserService.findObjectById(id);
		return new JsonResult(1, "ok", map);
	}
	@RequiresPermissions("sys:user:update")
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(CatUser entity,String roleIds){
		entity.setModifiedUser(catUser.getUsername());//暂时设置，现在没有登录用户
		catUserService.updateObject(entity, roleIds);
		return new JsonResult(1, "update ok");
	}
	@RequiresPermissions("sys:user:delete")
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		catUserService.deleteObject(id);
		return new JsonResult(1, "delete ok");
	}
}
