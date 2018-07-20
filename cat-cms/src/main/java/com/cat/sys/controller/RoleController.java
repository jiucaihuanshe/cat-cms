package com.cat.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通过@Controller配置这个是控制器
 * 通过@RequestMapping实现url与控制器及方法的映射
 * 讲解@ResponseBody的作用：该注解作用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换
 * 为指定格式后，写入后Response对象的body数据区，使用情况：返回的数据不是html标签的页面，而是其他数据格式的数据(
 * 如JSON、xml)使用。
 * 
 * @author wangchaofan
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@RequestMapping("/listUI")
	public String listUI(){
		return "/index";
	}
	
	// /role/admin-role.html
	@RequestMapping("admin-role")
	public String role(){
		return "admin-role";
	}
}
