package com.dk.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dk.dao.UserMapperDao;
import com.dk.object.UserInfo;
import com.dk.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapperDao dao;

	public List<UserInfo> getUserInfo() {
		// TODO Auto-generated method stub
		List<UserInfo> infos = new ArrayList<UserInfo>();
		infos = dao.getUserInfo();
		return infos;
	}

}
