package com.cat.sys.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.cat.common.exception.ServiceException;

/**
 * 登录认证
 * @author wangchaofan
 *
 */
@Service
public class CatShiroServiceImpl implements CatShiroService {
	@Override
	public void login(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		//如果已经验证了
		if(subject.isAuthenticated()){
			return;
		}
		//把用户名和密码封装为UsernamePasswordToken对象
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
		}catch (IncorrectCredentialsException ice) {
	        throw new ServiceException("密码错误！");
	    } catch(AuthenticationException ae){
	        ae.printStackTrace();
	        throw new ServiceException("认证失败");
	    }

	}

}
