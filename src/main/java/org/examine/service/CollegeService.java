package org.examine.service;

import java.util.List;

import org.examine.po.College;

public interface CollegeService {
	
	// ��ѯ����Ժϵ
	List<College> findAllCollege() throws Exception;
	
}
