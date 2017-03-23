package com.tan.parking.model;

public class MotifyPassword {
	int result;
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

	@Override
	public String toString() {
		return "Login{" + "result=" + result + ", message='" + message + '}';
	}
}
