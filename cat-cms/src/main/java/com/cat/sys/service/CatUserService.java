package com.cat.sys.service;

import com.cat.common.vo.PageObject;
import com.cat.sys.pojo.CatUser;

public interface CatUserService {
	PageObject<CatUser> findPageObjects(Integer pageCurrent,String name);
}
