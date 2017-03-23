package com.tan.parking.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tan.parking.model.FreeplaceList;
import com.tan.parking.model.Parkinglot;
import com.tan.parking.model.Record;
import com.tan.parking.model.orderParkingUtil;

public interface IRecordService {
	public List<Integer> getFreeplaceList(String parking_name, String local, String start_time, String end_time);

	public Parkinglot getParkinglot(String parking_name, String local);

	public FreeplaceList getFreeplaceList2(String parking_name, String local, String start_time, String end_time);

	public int orderParking(String record_id, int parking_id, int parking_num, String start_time, String end_time,
			String order_time, boolean complete, double expense, String username);

	public int updateRecord(String order_time);

	public List<Record> getRecords(String username);
	
	public int getEnableSpace(String parking_name,String local, String current_time);

	public int cancelRecord(String order_id);
	
}
