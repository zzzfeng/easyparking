package com.tan.parking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tan.parking.model.ForgetPassword;
import com.tan.parking.model.GetChange;
import com.tan.parking.model.Login;
import com.tan.parking.model.MotifyPassword;
import com.tan.parking.model.Register;
import com.tan.parking.model.User;
import com.tan.parking.model.UserInfo;
import com.tan.parking.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		User user = this.userService.getUserByUserName(username);
		model.addAttribute("user", user);
		return "showUser";
	}

	// @RequestMapping("/hello")
	// public String hello() {
	// return "hello";
	// }

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Login checkLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		Login login = new Login();
		User user = this.userService.getUserByUserName(username);
		if (user == null) {
			login.setMessage("没有该用户:" + username);
			login.setResult(0);
		} else {
			this.userService.updateUserInfo(username);
			UserInfo u = this.userService.getUserInfo(username);
			int noConstimes = u.getNoconstimes();
			// System.out.println("the time is " + noConstimes);
			if (noConstimes >= 3) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// System.out.println("Add to noConstimes!");
				this.userService.addToBlacklist(username, dateFormat.format(new Date()));
				login.setResult(0);
				login.setMessage("该用户已经被加入黑名单");
			} else {
				String pwd = user.getPassword();
				if (!pwd.equals(password)) {
					login.setMessage("密码错误！");
					login.setResult(0);
				} else {
					login.setResult(1);
					login.setMessage("成功登陆!");
				}
			}
		}
		return login;
	}

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public UserInfo getUserInfo(@RequestParam("username") String username) {
		UserInfo info = this.userService.getUserInfo(username);
		if (info == null) {
			info = new UserInfo();
			info.setResult(0);
		} else {
			info.setResult(1);
		}
		return info;
	}

	@RequestMapping("/forgetPassword")
	@ResponseBody
	public ForgetPassword forgetPassword(@RequestParam("username") String username,
			@RequestParam("newPassword") String newPassword) {

		ForgetPassword forgetPassword = new ForgetPassword();
		User user = this.userService.getUserByUserName(username);
		if (user == null) {
			forgetPassword.setResult(0);
			forgetPassword.setMessage("没有该用户 " + username);
		} else {
			this.userService.forgetPassword(username, newPassword);
			forgetPassword.setResult(1);
			forgetPassword.setMessage("成功修改密码");
		}
		return forgetPassword;
	}

	@RequestMapping(value = "/registerUser")
	@ResponseBody
	public Register registerUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		Register register = new Register();

		User user = this.userService.getUserByUserName(username);
		if (user != null) {
			register.setResult(0);
			register.setMessage("该用户已经存在!");
		} else {
			this.userService.registerUser(username, password);
			register.setResult(1);
			register.setMessage("成功创建用户！");
		}

		return register;
	}

	@RequestMapping("/motifyPassword")
	@ResponseBody
	public MotifyPassword motifyPassword(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("newpassword") String newpassword) {

		MotifyPassword motifyPassword = new MotifyPassword();
		User user = this.userService.getUserByUserName(username);
		if (user == null) {
			motifyPassword.setResult(0);
			motifyPassword.setMessage("没有该用户!");
		} else {
			this.userService.motifyPassword(username, password, newpassword);
			motifyPassword.setResult(1);
			motifyPassword.setMessage("修改成功！");
		}
		return motifyPassword;
	}

	@RequestMapping("/getChange")
	@ResponseBody
	public GetChange getChange(@RequestParam("username") String username) {
		GetChange getChange = new GetChange();
		UserInfo userInfo = this.userService.getUserInfo(username);
		if (userInfo == null) {
			getChange.setResult(0);
			getChange.setMessage("获取用户信息失败");
		} else {
			getChange.setUsername(userInfo.getUsername());
			getChange.setChangemoney(userInfo.getChangemoney());
			getChange.setResult(1);
			getChange.setMessage("成功获取用户信息");
		}
		return getChange;
	}

	@RequestMapping("/charge")
	@ResponseBody
	public GetChange charge(@RequestParam("username") String username, @RequestParam("num") String num) {
		GetChange getChange = new GetChange();
		this.userService.charge(Integer.parseInt(num), username);
		UserInfo userInfo = this.userService.getUserInfo(username);
		if (userInfo == null) {
			getChange.setResult(0);
			getChange.setMessage("用户充值失败");
		} else {
			getChange.setUsername(userInfo.getUsername());
			getChange.setChangemoney(userInfo.getChangemoney());
			getChange.setResult(1);
			getChange.setMessage("用户充值成功");
		}
		return getChange;
	}

}
