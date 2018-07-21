package com.cat.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.exception.ServiceException;
import com.cat.common.vo.PageObject;
import com.cat.sys.mapper.CatUserMapper;
import com.cat.sys.pojo.CatUser;

@Service
public class CatUserServiceImpl implements CatUserService {
	@Autowired
	private CatUserMapper catUserMapper;
	@Override
	public PageObject<CatUser> findPageObjects(Integer pageCurrent, String name) {
		if(pageCurrent<1)throw new ServiceException("当前页码不能为负数");
		//1.获取当前页数据
		//1.1定义每页最多显示3条记录
		int pageSize=3;
		//1.2计算每页查询的起始位置
		int startIndex= (pageCurrent-1)*pageSize;
		List<CatUser> list = catUserMapper.findPageObjects(name, startIndex, pageSize);
		//2.获取总记录数,计算总页数
		int rowCount = catUserMapper.getRowCount(name);
		int pageCount = rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}
		//3.封装数据
		PageObject<CatUser> pageObject = new PageObject<>();
		pageObject.setRecords(list);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount(pageCount);
		pageObject.setPageCurrent(pageCurrent);
		return pageObject;
	}

}
