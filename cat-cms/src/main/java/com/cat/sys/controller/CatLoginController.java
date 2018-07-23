package com.cat.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.common.vo.JsonResult;
import com.cat.sys.service.CatShiroService;

@Controller
@RequestMapping("/")
public class CatLoginController {
	@Autowired
	private CatShiroService catShiroService;
	@RequestMapping("loginUI")
	public String loginUI(){
		return "sys/login";
	}
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password){
		catShiroService.login(username,password);
		return new JsonResult(1, "login ok");
	}
}
