package com.tan.parking.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tan.parking.dao.IRecordDao;
import com.tan.parking.model.FreeplaceList;
import com.tan.parking.model.Parkinglot;
import com.tan.parking.model.Record;
import com.tan.parking.model.orderParkingUtil;
import com.tan.parking.service.IRecordService;

@Service("recordService")
public class RecordServiceImpl implements IRecordService {
	@Resource
	IRecordDao irecordDao;

	@Override
	public List<Integer> getFreeplaceList(String parking_name, String local, String start_time, String end_time) {
		// TODO Auto-generated method stub
		return irecordDao.getFreeplaceList(parking_name, local, start_time, end_time);
	}

	@Override
	public Parkinglot getParkinglot(String parking_name, String local) {
		// TODO Auto-generated method stub
		return irecordDao.getParkinglot(parking_name, local);
	}

	@Override
	public FreeplaceList getFreeplaceList2(String parking_name, String local, String start_time, String end_time) {
		// TODO Auto-generated method stub
		return irecordDao.getFreeplaceList2(parking_name, local, start_time, end_time);
	}

	@Override
	public int orderParking(String record_id, int parking_id, int parking_num, String start_time, String end_time,
			String order_time, boolean complete, double expense, String username) {
		// TODO Auto-generated method stub
		return irecordDao.orderParking(record_id, parking_id, parking_num, start_time, end_time, order_time, complete,
				expense, username);
	}

	public int updateRecord(String order_time) {
		return irecordDao.updateRecord(order_time);
	}

	@Override
	public List<Record> getRecords(String username) {
		// TODO Auto-generated method stub
		return irecordDao.getRecords(username);
	}

	@Override
	public int getEnableSpace(String parking_name, String local,String current_time) {
		// TODO Auto-generated method stub
		return irecordDao.getEnableSpace(parking_name, local,current_time);
	}

	@Override
	public int cancelRecord(String order_id) {
		// TODO Auto-generated method stub
		return irecordDao.cancelOrder(order_id);
	}

}
