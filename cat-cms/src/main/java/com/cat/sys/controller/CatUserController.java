package com.cat.sys.controller;

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
	@RequestMapping("listUI")
	public String listUI(){
		return "sys/user_list";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent,String name){
		PageObject<CatUser> pageObject = catUserService.findPageObjects(pageCurrent,name);
		return new JsonResult(1, "query ok", pageObject);	//jackson,fastjson
	}
}
