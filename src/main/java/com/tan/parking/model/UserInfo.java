package com.tan.parking.model;

public class UserInfo {
	private String username;
	private String carnum;
	int result;
	int constimes;
	int noconstimes;
	int changemoney;

	public int getNoconstimes() {
		return noconstimes;
	}

	public void setNoconstimes(int noconstimes) {
		this.noconstimes = noconstimes;
	}

	public int getChangemoney() {
		return changemoney;
	}

	public void setChangemoney(int changemoney) {
		this.changemoney = changemoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int reuslt) {
		this.result = reuslt;
	}

	public int getConstimes() {
		return constimes;
	}

	public void setConstimes(int constimes) {
		this.constimes = constimes;
	}

	@Override
	public String toString() {
		return "UserInfo { " + "username :" + username + "carnum :" + carnum + "result :" + result + "constimes :"
				+ constimes + "noconstimes :" + noconstimes + "}";

	}
}
