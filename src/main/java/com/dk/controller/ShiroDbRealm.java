package com.dk.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.dk.object.UserInfo;
import com.dk.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm{
	@Resource
	private UserService userService;
	public static final String SESSION_USER_KEY = "gray";

	/** 
     * ��Ȩ��ѯ�ص�����, ���м�Ȩ�����������û�����Ȩ��Ϣʱ����,������Ӧ�ó����о����û��ķ��ʿ��Ƶķ��� 
     */  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		UserInfo info = (UserInfo)SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
		SimpleAuthorizationInfo sinfo = new SimpleAuthorizationInfo();
		sinfo.addRole(info.getRole().trim());
		return sinfo;
	}

	 /** 
     * ��֤�ص���������¼��Ϣ���û���֤��Ϣ��֤ 
     */ 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		UserInfo userInfo = tokenToUser((UsernamePasswordToken)arg0);
		UserInfo info = userService.getUserInfoAsNameAndPwd(userInfo);
		
		if(info==null){
			return null;  // �쳣�����Ҳ�������  
		}
		
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(ShiroDbRealm.SESSION_USER_KEY, info);
		//��ǰ Realm �� name  
        String realmName = this.getName();  
      //��½����Ҫ��Ϣ: ������һ��ʵ����Ķ���, ����ʵ����Ķ���һ���Ǹ��� token �� username ��ѯ�õ���.  
//      Object principal = ui.getUsername();  
        Object principal = arg0.getPrincipal();  
        return new SimpleAuthenticationInfo(principal, userInfo.getPassword(), realmName);  
	}
	
	private UserInfo tokenToUser(UsernamePasswordToken token){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(token.getUsername());
		userInfo.setPassword(String.valueOf(token.getPassword()));
		return userInfo;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
