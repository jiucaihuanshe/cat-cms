package com.cat.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class TestPool {
	@Test
	public void testPool(){
		//spring bean容器
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-configs.xml");
		DruidDataSource dataSource = (DruidDataSource) ctx.getBean("dataSource");
		System.out.println(dataSource);
		//断言是编写测试用例的核心实现方式，即期望值是多少，测试的结果是多少，以此来判断测试是否通过。
		//assertNotEquals(first, second)	查看两个对象是否不相等。
		Assert.assertNotEquals(dataSource,null);
	}
}
