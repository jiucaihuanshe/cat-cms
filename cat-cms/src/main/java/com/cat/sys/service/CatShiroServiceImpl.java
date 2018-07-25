package com.cat.sys.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.exception.ServiceException;
import com.cat.sys.mapper.CatUserMapper;
import com.cat.sys.pojo.CatUser;

/**
 * 登录认证
 * @author wangchaofan
 *
 */
@Service
public class CatShiroServiceImpl implements CatShiroService {
	@Autowired
	private CatUserMapper CatUserMapper;
	@Override
	public void login(String username, String password) {
		//1.获取Shiro中的Subject(主体)对象
		Subject subject = SecurityUtils.getSubject();
		CatUser catUser = CatUserMapper.findObjectByName(username);
		if(catUser.getValid()==1){
			//如果已经验证了
			if(subject.isAuthenticated()){
				return;
			}
			//2.把用户名和密码封装为UsernamePasswordToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			//3.执行登录操作(此方法执行可能会出现异常)
			subject.login(token);
		}else if(catUser.getValid()==0 || catUser.getValid()==null){
			throw new ServiceException("Users are not allowed to log in.");
		}
		/*try {
			subject.login(token);
		}catch (IncorrectCredentialsException ice) {
	        throw new ServiceException("密码错误！");
	    } catch(AuthenticationException ae){
	        ae.printStackTrace();
	        throw new ServiceException("认证失败");
	    }*/

	}

}
