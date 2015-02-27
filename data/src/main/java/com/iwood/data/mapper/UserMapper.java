package com.iwood.data.mapper;

import com.iwood.data.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-2-27
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public interface UserMapper {

	public void addUser(User user);

	public User getUserByPhone(@Param("phone") String phone);

	public User getUserByPhoneAndPassWord(@Param("phone") String phone,
										  @Param("passWord") String passWord);

	public void updateUser(User user);

}
