package com.cat.common.exception;
/**
 * 业务层异常类
 * 1)定义一个异常类,例如ServiceException处理业务层异常
 * 2)定义一个全局的异常处理器(handler),在此类中进行全局异常处理.
 * @author wangchaofan
 *
 */
public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = -6307895640061877334L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}
