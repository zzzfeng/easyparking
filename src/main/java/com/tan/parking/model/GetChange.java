package com.tan.parking.model;

public class GetChange {

	private String username;
	private int changemoney;
	private int result;
	private String message;
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetChange(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getChangemoney() {
		return changemoney;
	}

	public void setChangemoney(int changemoney) {
		this.changemoney = changemoney;
	}


	
	
	
}
