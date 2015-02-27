package com.iwood.data.model;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-2-27
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class User {

	private long userId;
	private String phone;
	private String nick;
	private String passWord;
	private int userType;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
}
