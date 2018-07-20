package com.cat.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public List<CatRole> doFindPageObjects(){
		List<CatRole> list = catRoleService.findPageObjects();
		return list;	//jackson,fastjson
	}
}
