package com.tan.parking.service;


import com.tan.parking.model.User;
import com.tan.parking.model.UserInfo;

public interface IUserService {

	public User getUserByUserName(String username);

	public UserInfo getUserInfo(String username);

	public void forgetPassword(String username, String newPassword);

	public void registerUser(String username, String password);
	
	public void motifyPassword(String username,String password,String newpassword);

	public void changeConsume(int num,String username);//消费
	
	public void charge(int num,String username);
	
	public void updateUserInfo(String username);
	
	public int getNoconstimes(String username);
	
	public void addToBlacklist(String username,String time);
}
