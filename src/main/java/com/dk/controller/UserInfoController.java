package com.dk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.UserInfo;
import com.dk.result.Result;
import com.dk.service.UserService;

@Controller
@RequestMapping("user")
public class UserInfoController {
	@Resource
	private UserService userService;
	
	@RequestMapping("getuserinfo.ll")
	@ResponseBody
	public List<UserInfo> getUserInfo(){
		List<UserInfo> infos = new ArrayList<UserInfo>();
		infos = userService.getUserInfo();
		return infos;
	}
	
	@RequestMapping("login.ll")
	@ResponseBody
	public Result login(UserInfo userInfo){
		Result result = new Result();
		UserInfo info = new UserInfo();
		info = userService.getUserInfoAsNameAndPwd(userInfo);
		result.setData(info);
		result.setMessage("查询成功");
		result.setStatus(true);
		return result;
	}
	
	@RequestMapping("openlogin.ll")
	public String openLogin(){
		return "../jsp/login.jsp";
	}
	
	@RequestMapping("success.ll")
	public String openSuccessPage(){
		return "../jsp/success.jsp";
	}
	
	@RequestMapping("openuser.ll")
	public String openUserPage(){
		return "../jsp/user.jsp";
	}
	
	@RequestMapping("openadmin.ll")
	public String openAdminPage(){
		return "../jsp/admin.jsp";
	}
	
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null){
			subject.logout();
		}
		return "../index.jsp";
	}
	
	@RequestMapping("userlogin.ll")
	public String doLogin(UserInfo info,Model model){
		String str = loginUser(info);
		if(!"SUCC".equals(str)){
			model.addAttribute("failMsg","用户不存在或密码错误！");
			return "../jsp/fail.jsp";
		}else{
			model.addAttribute("successMsg","登录成功");
			model.addAttribute("name",info.getUserName());
			return "../jsp/success.jsp";
		}
	}
	
	private String shiroLogin(UserInfo info){
		UsernamePasswordToken token = new UsernamePasswordToken(info.getUserName(),info.getPassword().toCharArray(),null);
		token.setRememberMe(true);
		
		// shiro登陆验证  
	    try {  
	        SecurityUtils.getSubject().login(token);  
	    } catch (UnknownAccountException ex) {  
	        return "用户不存在或者密码错误！";  
	    } catch (IncorrectCredentialsException ex) {  
	        return "用户不存在或者密码错误！";  
	    } catch (AuthenticationException ex) {  
	        return ex.getMessage(); // 自定义报错信息  
	    } catch (Exception ex) {  
	        ex.printStackTrace();  
	        return "内部错误，请重试！";  
	    }  
	    return "SUCC";  
		
	}
	
	private String loginUser(UserInfo info){
		if(isRelogin(info)){
			return "SUCC";
		}
		return shiroLogin(info);
	}
	
	private boolean isRelogin(UserInfo info){
		Subject us = SecurityUtils.getSubject();
		if(us.isAuthenticated()){
			return true;
		}
		return false;
	}

}
