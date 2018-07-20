package com.cat.sys.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建角色实体类
 * 这是个持久化对象
 * 对象编写注意：
 * 1)实现Serializable接口，并提供版本ID
 * 2)提供无参构造函数
 * 3)提供属性以及对应的set/get方法，一般属性名要与表中的字段名保持一致
 * 4)有选择性的重写toString()方法
 * @author wangchaofan
 *
 */
public class CatRole implements Serializable{
	private static final long serialVersionUID = -3806423152835115464L;
	private Integer id;
	private String name;	//角色名称
	private String note;	//备注
	private Date createdTime;	//创建时间
	private Date modifiedTime;	//修改时间
	private String createdUser;	//创建用户
	private String modifiedUser;//修改用户
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "CatRole [id=" + id + ", name=" + name + ", note=" + note + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser
				+ "]";
	}
	
}
