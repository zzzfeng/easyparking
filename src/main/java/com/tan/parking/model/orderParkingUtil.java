package com.tan.parking.model;

public class orderParkingUtil {
	int result;
	String message;

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
		return "orderParking{" + "result=" + result + ", message='" + message + '}';
	}
}