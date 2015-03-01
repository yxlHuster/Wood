package com.iwood.web.service.impl;

import com.iwood.data.mapper.UserMapper;
import com.iwood.data.model.User;
import com.iwood.web.service.UserService;
import com.iwood.web.util.DES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-2-28
 * Time: 下午6:57
 * To change this template use File | Settings | File Templates.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final String ENCRY_KEYS = "!@$#%$#%^$QWE23512312";

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByPhone(String phone) {
		try {
			return userMapper.getUserByPhone(phone);
		} catch (Exception e) {
			LOGGER.warn("getUserByPhone error! e = {}", e);
		}
		return null;
	}

	@Override
	public boolean addUser(String phone, String realName, String company, String mainProducts, String passWord) {
		User user = new User();
		user.setPhone(phone);
		user.setRealName(realName);
		user.setCompany(company);
		user.setMainProducts(mainProducts);
		try {
			String pwd = DES.encrypt(passWord, ENCRY_KEYS);
			user.setPassWord(pwd);
		} catch (Exception e) {
			LOGGER.warn("encrypt error! e = {}!", e);
			return false;
		}
		try {
			userMapper.addUser(user);
		} catch (Exception e) {
			LOGGER.warn("add user to db error! phone = {}, passWd = {}, realName = {}, company = {}, e = {}!", phone, passWord, realName, company, e);
			return false;
		}
		return true;
	}

	@Override
	public User getUserByPhoneAndPassWd(String phone, String passWd) {
		try {
			String pwd = DES.encrypt(passWd, ENCRY_KEYS);
			return userMapper.getUserByPhoneAndPassWord(phone, pwd);
		} catch (Exception e) {
			LOGGER.error("getUser from db by phone and pwd error! phone = {}, e = {}", phone, e);
		}
		return null;
	}
}
