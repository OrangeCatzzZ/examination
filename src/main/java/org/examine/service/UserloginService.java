package org.examine.service;

import org.examine.po.Userlogin;

public interface UserloginService {
	
	// ����userName�����û�
	Userlogin findUserByUsername(String username);
	
}
