package org.examine.service;

import java.util.List;

import org.examine.po.College;

public interface CollegeService {
	
	// 查询所有院系
	List<College> findAllCollege() throws Exception;
	
}
