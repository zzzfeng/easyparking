package com.tan.parking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tan.parking.model.FreeplaceList;
import com.tan.parking.model.Parkinglot;
import com.tan.parking.model.Record;
import com.tan.parking.model.orderParkingUtil;

public interface IRecordDao {

	public List<Integer> getFreeplaceList(@Param("parking_name") String parking_name, @Param("local") String local,
			@Param("start_time") String start_time, @Param("end_time") String end_time);

	public FreeplaceList getFreeplaceList2(@Param("parking_name") String parking_name, @Param("local") String local,
			@Param("start_time") String start_time, @Param("end_time") String end_time);

	public Parkinglot getParkinglot(@Param("parking_name") String parking_name, @Param("local") String local);

	public int orderParking(@Param("record_id") String record_id, @Param("parking_id") int parking_id,
			@Param("parking_num") int parking_num, @Param("start_time") String start_time,
			@Param("end_time") String end_time, @Param("order_time") String order_time,
			@Param("complete") boolean complete, @Param("expense") double expense, @Param("username") String username);

	public int updateRecord(@Param("order_time") String order_time);

	public List<Record> getRecords(@Param("username") String username);

	public int getEnableSpace(@Param("parking_name") String parking_name, @Param("local") String local,
			@Param("current_time") String current_time);

	public int cancelOrder(@Param("order_id")String order_id);


}
