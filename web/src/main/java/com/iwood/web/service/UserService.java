package com.iwood.web.service;

import com.iwood.data.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-2-28
 * Time: 下午6:51
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

	public User getUserByPhone(String phone);

	public boolean addUser(String phone, String realName, String company, String mainCategory, String passWord);

	public User getUserByPhoneAndPassWd(String phone, String passWd);

}
