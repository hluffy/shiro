package com.dk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.UserInfo;
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

}
