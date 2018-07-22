package com.cat.common.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.common.exception.ServiceException;
import com.cat.common.vo.JsonResult;

/**
 * 定义统一异常处理类
 * @author wangchaofan
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(value=ServiceException.class)
	@ResponseBody
	public JsonResult handlerServiceException(ServiceException e){
		 e.printStackTrace();
		 return new JsonResult(0, e.getMessage());
	}
}
