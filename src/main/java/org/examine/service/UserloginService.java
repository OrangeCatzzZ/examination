package org.examine.service;

import org.examine.po.Userlogin;

public interface UserloginService {
	
	// 根据userName查找用户
	Userlogin findUserByUsername(String username);
	
}
