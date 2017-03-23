package com.tan.parking.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tan.parking.dao.IUserDao;
import com.tan.parking.model.ChangeOperationUtil;
import com.tan.parking.model.User;
import com.tan.parking.model.UserInfo;
import com.tan.parking.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	public User getUserByUserName(String username) {
		return userDao.getUserByUserName(username);
	}

	@Override
	public UserInfo getUserInfo(String username) {
		return userDao.getUserInfo(username);
	}

	@Override
	public void forgetPassword(String username, String newPassword) {
		userDao.forgetPassword(username, newPassword);
	}

	@Override
	public void registerUser(String username, String password) {
		userDao.registerUser(username, password);
	}

	@Override
	public void motifyPassword(String username, String password, String newpassword) {
		userDao.motifyPassword(username, password, newpassword);
	}

	@Override
	public void changeConsume(int num, String username) {

		userDao.changeConsume(num, username);
	}

	@Override
	public void charge(int num, String username) {
		// TODO Auto-generated method stub
		userDao.charge(num, username);
	}

	@Override
	public void updateUserInfo(String username) {
		// TODO Auto-generated method stub
		userDao.updateUserInfo(username);
	}

	@Override
	public int getNoconstimes(String username) {
		// TODO Auto-generated method stub
		return userDao.getNoconstimes(username);
	}

	@Override
	public void addToBlacklist(String username, String time) {
		// TODO Auto-generated method stub
		userDao.addToBlacklist(username,time);
	}

}
