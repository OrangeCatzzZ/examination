package org.examine.service.impl;

import java.util.List;

import org.examine.mapper.CollegeMapper;
import org.examine.po.College;
import org.examine.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private CollegeMapper collegeMapper;
	
	// 查询所有院系
	@Override
	public List<College> findAllCollege() throws Exception {
		return collegeMapper.selectByExample(null);
	}

}
