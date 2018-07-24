package com.cat.common.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
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
	@ExceptionHandler(value=RuntimeException.class)
	@ResponseBody
	public JsonResult handlerServiceException(RuntimeException e){
		 e.printStackTrace();
		 String msg = e.getMessage();
		 if(e instanceof IncorrectCredentialsException){
			 msg = "password is error";
		 }else if(e instanceof AuthenticationException){
			 msg = "username is not exits";
		 }else if(e instanceof UnauthorizedException){
			 msg = "has not permission";
		 }
		 return new JsonResult(0, msg);
	}
}
