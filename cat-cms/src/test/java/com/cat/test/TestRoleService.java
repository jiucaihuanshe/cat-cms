package com.cat.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cat.common.vo.PageObject;
import com.cat.sys.pojo.CatRole;
import com.cat.sys.service.CatRoleService;

/**
 * 基于Service创建单元测试
 * @author wangchaofan
 *
 */
public class TestRoleService {
	private ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-configs.xml");
	}
	@Test
	public void testFindPageObjects(){
		CatRoleService roleService = ctx.getBean("catRoleServiceImpl", CatRoleService.class);
		String name = "admin";
		int pageCurrent = 1;
		PageObject list = roleService.findPageObjects(pageCurrent,name);
		System.out.println(list);
	}
	@After
	public void destory(){
		ctx.close();
	}
}
