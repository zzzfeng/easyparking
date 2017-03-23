package com.tan.parking.dao;

import org.apache.ibatis.annotations.Param;

import com.tan.parking.model.ChangeOperationUtil;
import com.tan.parking.model.User;
import com.tan.parking.model.UserInfo;

public interface IUserDao {
	public User getUserByUserName(String username);

	public UserInfo getUserInfo(String username);

	public void forgetPassword(String username, String newPassword);

	public void registerUser(@Param("username") String username, @Param("password") String password);

	public void motifyPassword(@Param("username") String username, @Param("password") String password,
			@Param("newpassword") String newpassword);

	public void changeConsume(@Param("num") int num, @Param("username") String username);

	public void charge(@Param("num") int num, @Param("username") String username);

	public void updateUserInfo(@Param("username")String username);

	public int getNoconstimes(@Param("username")String username);

	public void addToBlacklist(@Param("username")String username, @Param("time")String time);
}
