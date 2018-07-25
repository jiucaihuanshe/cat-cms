package com.cat.sys.service.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cat.sys.mapper.CatUserMapper;
import com.cat.sys.pojo.CatUser;

public class RememberMeFilter extends FormAuthenticationFilter{
	@Autowired
	private CatUserMapper catUserMapper;
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		if(!subject.isAuthenticated() && subject.isRemembered() && session.getAttribute("currentUser")==null){
			Object principal = subject.getPrincipal();
			if(principal !=null){
				String account = principal.toString();
				CatUser catUser = catUserMapper.findObjectByName(account);
				session.setAttribute("currentUser", catUser);
			}
		}
		return subject.isAuthenticated() || subject.isRemembered();
	}
}
