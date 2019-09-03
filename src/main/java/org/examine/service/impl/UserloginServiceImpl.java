package org.examine.service.impl;

import org.examine.mapper.UserloginMapper;
import org.examine.po.Userlogin;
import org.examine.po.UserloginExample;
import org.examine.po.UserloginExample.Criteria;
import org.examine.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserloginServiceImpl implements UserloginService {

	@Autowired
	private UserloginMapper userloginMapper;

	// 根据userName查找用户
	@Override
	public Userlogin findUserByUsername(String username) {
		UserloginExample example = new UserloginExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		example.or(criteria);
		return userloginMapper.selectByExample(example).get(0);
	}

}
