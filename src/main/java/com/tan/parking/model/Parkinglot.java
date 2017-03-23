package com.tan.parking.model;

public class Parkinglot {
	private String parking_name;
	private String local;
	private int parking_id;
	private boolean flag;
	private int free_num;
	private int total;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private double charge_day ;
	private double charge_night;

	
	public double getCharge_day() {
		return charge_day;
	}

	public void setCharge_day(double charge_day) {
		this.charge_day = charge_day;
	}

	public double getCharge_night() {
		return charge_night;
	}

	public void setCharge_night(double charge_night) {
		this.charge_night = charge_night;
	}

	public int getFree_num() {
		return free_num;
	}

	public void setFree_num(int free_num) {
		this.free_num = free_num;
	}

	public String getParking_name() {
		return parking_name;
	}

	public void setParking_name(String parkingname) {
		this.parking_name = parkingname;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getParking_id() {
		return parking_id;
	}

	public void setParking_id(int parking_id) {
		this.parking_id = parking_id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
