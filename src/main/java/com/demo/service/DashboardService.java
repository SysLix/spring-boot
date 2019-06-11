package com.demo.service;

import java.util.List;

public interface DashboardService {
	List<Object[]> finddis(Integer caremaId, String queryTime);
	
	public List<Object> findPICLIBPath(Integer caremaId);
	
	List<Object> findimgpath(Integer caremaId);
}
