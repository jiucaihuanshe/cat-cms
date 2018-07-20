package com.cat.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSessionFactory {
	@Test
	public void testSessionFactory() {
		ApplicationContext ctx=
		new ClassPathXmlApplicationContext("spring-configs.xml");    
		Object sessionFactory=ctx.getBean("sqlSessionFactory");
		System.out.println(sessionFactory);
		Assert.assertNotEquals(sessionFactory, null);
	}
}
