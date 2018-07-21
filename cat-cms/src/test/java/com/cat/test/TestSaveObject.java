package com.cat.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cat.sys.pojo.CatRole;
import com.cat.sys.service.CatRoleService;

public class TestSaveObject {
	private ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-configs.xml");
	}
	@Test
	public void testSaveObject(){
		CatRoleService rs = ctx.getBean("catRoleServiceImpl", CatRoleService.class);
		CatRole catRole = new CatRole();
		catRole.setId(3);
		catRole.setName("管理严");
		catRole.setNote("最大的管理者");
		catRole.setCreatedUser("tom");
		catRole.setModifiedUser("tom");
		int rows = rs.insertObject(catRole);
		Assert.assertEquals(1, rows);
	}
	@After
	public void destory(){
		ctx.close();
	}
}
