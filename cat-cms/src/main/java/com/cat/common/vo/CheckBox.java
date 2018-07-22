package com.cat.common.vo;

import java.io.Serializable;

/**
 * 用于保存角色表中查询的name,id的数据
 * @author lenovo
 *
 */
public class CheckBox implements Serializable{
	private static final long serialVersionUID = -1468443249666800128L;
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
