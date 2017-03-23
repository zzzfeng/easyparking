package com.tan.parking.model;

public class Login {
	int result;
	private String message;
	private String session_id;

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

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	@Override
	public String toString() {
		return "Login{" + "result=" + result + ", message='" + message + ", session_id='" + session_id + '}';
	}
}