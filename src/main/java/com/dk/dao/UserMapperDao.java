package com.dk.dao;

import java.util.List;

import com.dk.object.UserInfo;

public interface UserMapperDao {
	public List<UserInfo> getUserInfo();
	public UserInfo getUserInfoAsNameAndPwd(UserInfo userInfo);

}
