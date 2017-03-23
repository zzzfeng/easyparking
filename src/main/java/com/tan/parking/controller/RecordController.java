package com.tan.parking.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tan.parking.model.CancelOrder;
import com.tan.parking.model.ChangeOperationUtil;
import com.tan.parking.model.EnableSpace;
import com.tan.parking.model.FreeplaceList;
import com.tan.parking.model.GetChange;
import com.tan.parking.model.Parkinglot;
import com.tan.parking.model.Parkinglots;
import com.tan.parking.model.ParkinglotsRatio;
import com.tan.parking.model.Record;
import com.tan.parking.model.RecordList;
import com.tan.parking.model.UserInfo;
import com.tan.parking.model.orderParkingUtil;
import com.tan.parking.service.IRecordService;
import com.tan.parking.service.IUserService;

@Controller
@RequestMapping("/record")
public class RecordController {

	@Resource
	IRecordService recordService;

	@Resource
	IUserService userService;

	@RequestMapping("/getFreeplaceList")
	@ResponseBody
	public FreeplaceList getFreeplaceList(@RequestParam("parking_name") String parking_name,
			@RequestParam("local") String local, @RequestParam("start_time") String start_time,
			@RequestParam("end_time") String end_time) {

		try {
			parking_name = new String(parking_name.getBytes("ISO-8859-1"), "UTF-8");
			local = new String(local.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FreeplaceList freeplaceList = new FreeplaceList();
		List<Integer> list = this.recordService.getFreeplaceList(parking_name, local, start_time, end_time);

		// System.out.println(list);

		freeplaceList.setList(list);
		System.out.println(JSONObject.toJSONString(freeplaceList));
		if (freeplaceList == null) {
			freeplaceList.setResult(0);
			freeplaceList.setMesage("该车场不存在");
		} else {
			if (freeplaceList.getList().size() == 0) {
				freeplaceList.setResult(0);
				freeplaceList.setMesage("该车场没有空闲的车位!");
			} else {
				freeplaceList.setResult(1);
				freeplaceList.setMesage("成功获取");
			}
		}
		// System.out.println(JSONObject.toJSONString(freeplaceList));
		return freeplaceList;

	}

	@RequestMapping("/getFreeplaceList2")
	@ResponseBody
	public FreeplaceList getFreeplaceList2(@RequestParam("parking_name") String parking_name,
			@RequestParam("local") String local, @RequestParam("start_time") String start_time,
			@RequestParam("end_time") String end_time) {

		// try {
		// parking_name = new String(parking_name.getBytes("ISO-8859-1"),
		// "UTF-8");
		// local = new String(local.getBytes("ISO-8859-1"), "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		FreeplaceList freeplaceList = this.recordService.getFreeplaceList2(parking_name, local, start_time, end_time);

		// System.out.println(JSONObject.toJSONString(freeplaceList));
		if (freeplaceList == null) {
			freeplaceList = new FreeplaceList();
			freeplaceList.setResult(0);
			freeplaceList.setMesage("该车场不存在");
		} else {
			if (freeplaceList.getList().size() == 0) {
				freeplaceList.setResult(0);
				freeplaceList.setMesage("该车场没有空闲的车位!");
			} else {
				freeplaceList.setResult(1);
				freeplaceList.setMesage("成功获取");
			}
		}
		// System.out.println(JSONObject.toJSONString(freeplaceList));
		return freeplaceList;

	}

	@RequestMapping("/getParkinglot")
	@ResponseBody
	public Parkinglot getParkinglot(@RequestParam("parking_name") String parking_name,
			@RequestParam("local") String local) {
		try {
			parking_name = new String(parking_name.getBytes("ISO-8859-1"), "UTF-8");
			local = new String(local.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Parkinglot parkinglot = recordService.getParkinglot(parking_name, local);
		System.out.println(JSONObject.toJSONString(parkinglot));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowDate = sdf.format(new Date());
		System.out.println(nowDate);
		int free_num = this.recordService.getEnableSpace(parking_name, local, nowDate);
		parkinglot.setFree_num(free_num);
		// String s1 = parkinglot.getParking_name();
		// String s2 = parkinglot.getLocal();
		// System.out.println(JSONObject.toJSONString(parkinglot));

		return parkinglot;

	}

	@RequestMapping("/orderParking")
	@ResponseBody
	public orderParkingUtil orderParking(@RequestParam("parking_id") int parking_id,
			@RequestParam("parking_num") int parking_num, @RequestParam("start_time") String start_time,
			@RequestParam("end_time") String end_time, @RequestParam("order_time") String order_time,
			@RequestParam("complete") boolean complete, @RequestParam("expense") double expense,
			@RequestParam("username") String username) {

		orderParkingUtil parkingUtil = new orderParkingUtil();

		UserInfo info = this.userService.getUserInfo(username);
		if (info == null) {
			parkingUtil.setResult(0);
			parkingUtil.setMessage("获取用户信息失败");
			return parkingUtil;
		} else {
			int change = info.getChangemoney();
			if (change - 1 >= 0) {
				this.userService.changeConsume(1, username);// ---param为数额大小
			} else {
				parkingUtil.setResult(0);
				parkingUtil.setMessage("用户零钱不足以支付预定费用（1元/次）");
				return parkingUtil;
			}
		}

		String record_id = getUUID();
		int r = this.recordService.orderParking(record_id, parking_id, parking_num, start_time, end_time, order_time,
				complete, expense, username);

		if (r < 1) {
			parkingUtil.setResult(r);
			parkingUtil.setMessage("订单错误!");
		} else if (r == 1) {
			updateRecord(username );
			parkingUtil.setResult(r);
			parkingUtil.setMessage("新增订单成功！");
		}
		return parkingUtil;
	}

	public void updateRecord(String username) {
		long currentTime = System.currentTimeMillis();
		currentTime += 30 * 60 * 1000;
		Date date = new Date(currentTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int t = this.recordService.updateRecord(dateFormat.format(date));
		if (t > 0) {
			this.userService.updateUserInfo(username);
		}
	}

	public String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}

	@RequestMapping("/getRecords")
	@ResponseBody
	public RecordList getRecords(@RequestParam("username") String username) {
		updateRecord(username);
		RecordList recordList = new RecordList();
		List<Record> list = this.recordService.getRecords(username);
		recordList.setList(list);
		if (list == null) {
			recordList.setResult(0);
		} else {
			recordList.setResult(1);
		}
		System.out.println(JSONObject.toJSONString(recordList));
		return recordList;
	}

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public CancelOrder cancelOrder(@RequestParam("order_id") String order_id) {
		CancelOrder cancelOrder = new CancelOrder();

		int r = this.recordService.cancelRecord(order_id);
		if (r < 0) {
			cancelOrder.setResult(0);
			cancelOrder.setMessage("取消订单发生错误");
		} else if (r > 0) {
			cancelOrder.setResult(1);
			cancelOrder.setMessage("成功取消订单");
		}
		System.out.println(JSONObject.toJSONString(cancelOrder));
		return cancelOrder;
	}

	// @RequestMapping("/getEnableSpace")
	// @ResponseBody
	// public String getEnableSpace(@RequestParam("parking_name")String
	// parking_name,@RequestParam("local") String local){
	// return local;
	//
	// }

	@SuppressWarnings("unchecked")
	@RequestMapping("/getParkinglots")
	@ResponseBody
	public Parkinglots getParkinglots(@RequestParam("list_parkingname") String str_list_parkingname,
			@RequestParam("list_local") String str_list_local,@RequestParam("username")String username) {
		// // 网页调试功能
		// try {
		// str_list_parkingname = new
		// String(str_list_parkingname.getBytes("ISO-8859-1"), "UTF-8");
		// str_list_local = new String(str_list_local.getBytes("ISO-8859-1"),
		// "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		updateRecord(username);
		List<String> list_parkingname = (List<String>) JSONObject.parse(str_list_parkingname);
		List<String> list_local = (List<String>) JSONObject.parse(str_list_local);

		// for (int i = 0; i < list_parkingname.size(); i++) {
		// System.out.println(list_parkingname.get(i) + " , " +
		// list_local.get(i));
		// }

		Parkinglots parkinglots = new Parkinglots();
		if (list_parkingname.size() == 0 || list_local.size() == 0) {
			parkinglots.setResult(0);
			parkinglots.setMessage("获取车场信息错误！");
		} else {
			List<Double> charge_days = new ArrayList<>();
			List<Double> charge_nights = new ArrayList<>();
			List<Integer> frees = new ArrayList<>();
			for (int i = 0; i < list_parkingname.size(); i++) {
				String parking_name = list_parkingname.get(i);
				String local = list_local.get(i);
				Parkinglot p = this.recordService.getParkinglot(parking_name, local);
				// System.out.println("Parkinglot : " + JSON.toJSONString(p));
				if (p != null) {
					charge_days.add(p.getCharge_day());
					charge_nights.add(p.getCharge_night());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String nowDate = sdf.format(new Date());
					System.out.println(JSONObject.toJSONString(p));
					int free_num = this.recordService.getEnableSpace(parking_name, local, nowDate);
					frees.add(free_num);
				}
			}
			parkinglots.setCharge_days(charge_days);
			parkinglots.setCharge_nights(charge_nights);
			parkinglots.setFrees(frees);
			parkinglots.setResult(1);
			parkinglots.setMessage("成功获取附近车场的信息");

		}
		return parkinglots;
	}

	@RequestMapping("/getParkinglotsRatio")
	@ResponseBody
	public ParkinglotsRatio getParkinglotsRatio(@RequestParam("list_parkingname") String str_list_parkingname,
			@RequestParam("list_local") String str_list_local, @RequestParam("username") String username) {
		// try {
		// str_list_parkingname = new
		// String(str_list_parkingname.getBytes("ISO-8859-1"), "UTF-8");
		// str_list_local = new String(str_list_local.getBytes("ISO-8859-1"),
		// "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println("Hello");
		// for (int i = 0; i < list_parkingname.size(); i++) {
		// System.out.println(list_parkingname.get(i) + " , " +
		// list_local.get(i));
		// }
		updateRecord(username);
		List<String> list_parkingname = (List<String>) JSONObject.parse(str_list_parkingname);
		List<String> list_local = (List<String>) JSONObject.parse(str_list_local);
		ParkinglotsRatio parkinglotsRatio = new ParkinglotsRatio();

		if (list_parkingname.size() == 0 || list_local.size() == 0) {
			parkinglotsRatio.setResult(0);
			parkinglotsRatio.setMessage("获取车场信息错误！");
		} else {
			List<Integer> ratio_list = new ArrayList<Integer>();
			for (int i = 0; i < list_parkingname.size(); i++) {
				String parking_name = list_parkingname.get(i);
				String local = list_local.get(i);
				Parkinglot p = this.recordService.getParkinglot(parking_name, local);
				if (p != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String nowDate = sdf.format(new Date());
					// System.out.println(JSONObject.toJSONString(p));
					int free_num = this.recordService.getEnableSpace(parking_name, local, nowDate);
					int total = p.getTotal();// 获取该车场中所有车位的数量
					double r = 1 - (free_num * 1.0 / total);
					// System.out.println("the r is " + r + " the free-num is "
					// + free_num );
					if (r <= 0.2) {
						ratio_list.add(1);// 1代表空闲、2代表良好、3代表一般、4代表拥挤、5代表满
					} else if (r <= 0.4) {
						ratio_list.add(2);
					} else if (r <= 0.6) {
						ratio_list.add(3);
					} else if (r <= 0.8) {
						ratio_list.add(4);
					} else {
						ratio_list.add(5);
					}
				}
			}
			parkinglotsRatio.setRatio_list(ratio_list);
			parkinglotsRatio.setResult(1);
			parkinglotsRatio.setMessage("成功获取车位比率");
		}

		return parkinglotsRatio;

	}

}
