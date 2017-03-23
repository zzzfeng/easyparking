package com.tan.parking.model;

import java.util.List;

public class RecordList {
	int result;
	List<Record> list;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<Record> getList() {
		return list;
	}

	public void setList(List<Record> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "RcordList{" + "list=" + list + ", result=" + result + '}';
	}
}