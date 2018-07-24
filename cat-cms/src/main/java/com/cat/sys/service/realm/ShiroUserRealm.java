package com.cat.sys.service.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.sys.mapper.CatUserMapper;
import com.cat.sys.pojo.CatUser;
/**
 * 实现基本的认证及权限控制
 * @author wangchaofan
 *
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private CatUserMapper catUserMapper;
	/**负责授权检测*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		CatUser catUser = (CatUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		int userId = catUser.getId();
		List<String> permsList = new ArrayList<>();
		/**根据用户id查找用户角色权限信息(对资源的访问)*/
		permsList = catUserMapper.findUserPermissions(userId);
		//用户权限列表
		Set<String> parmsSet = new HashSet<>();
		for(String perm : permsList){
			if(perm!=null && !("".equals(perm))){
				parmsSet.add(perm);
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(parmsSet);
		return info;
	}

	/**此方法负责认证检测*/
	/**
	 * 此方法负责认证检测(检测用户身份是否存在，密码是否正确)
	 * 1)subject.login(token)
	 * 2)securityManager
	 * 3)Authentication
	 * 4)ShiroUserRealm
	 * 5)doGetAuthenticationInfo
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.把AuthenticationToken 转化为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//2.从UsernamePasswordToken中来获取username
		String username = upToken.getUsername();
		//3.判断用户名是否存在，若存在，返回user对象
		CatUser user = catUserMapper.findObjectByName(username);
		//盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		//自动完成密码比对
		//通过AuthenticatingRealm的credentialsMatcher属性来进行密码的比对
		//用户身份 已加密的密码 盐值对于的byteSource realm的名字
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), credentialsSalt,getName());
		//4.存储用户信息(session)
		SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
		return info;//返回给了认证器，谁调用返回给谁
	}

}
