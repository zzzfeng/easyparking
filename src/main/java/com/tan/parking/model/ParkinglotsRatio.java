package com.tan.parking.model;

import java.util.ArrayList;
import java.util.List;

public class ParkinglotsRatio {

	private List<Integer> ratio_list = new ArrayList<Integer>();
	private int result;
	private String message;

	public List<Integer> getRatio_list() {
		return ratio_list;
	}

	public void setRatio_list(List<Integer> ratio_list) {
		this.ratio_list = ratio_list;
	}

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

}
