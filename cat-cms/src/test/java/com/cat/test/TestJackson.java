package com.cat.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class TestJackson {
	//测试JSON对象
	@RequestMapping("/doTestJackson")
	@ResponseBody
	public Map<String, Object> doTestJackson(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", 100);
		map.put("name", "cat");
		return map;
	}
}
