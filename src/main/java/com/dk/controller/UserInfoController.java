package com.dk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
		result.setMessage("≤È—Ø≥…π¶");
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

}
