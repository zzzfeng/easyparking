package com.tan.parking.model;

import java.util.List;

public class Parkinglots {

	private List<Double> charge_days ;
	private List<Double> charge_nights ;
	private List<Integer> frees;
	private int result;
	private String message;
	
	public List<Double> getCharge_days() {
		return charge_days;
	}
	public void setCharge_days(List<Double> charge_days) {
		this.charge_days = charge_days;
	}
	public List<Double> getCharge_nights() {
		return charge_nights;
	}
	public void setCharge_nights(List<Double> charge_nights) {
		this.charge_nights = charge_nights;
	}
	public List<Integer> getFrees() {
		return frees;
	}
	public void setFrees(List<Integer> frees) {
		this.frees = frees;
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
