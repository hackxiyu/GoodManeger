package com.xr.server.dao;

import java.util.List;

import com.xr.bean.Good;

public interface GoodDao {

	public int addGood(Good good);
	public int deleteGood(int id);
	public int updateGood(Good good);
	public Good findGood(int id);
	public List<Good> allGood();
}
