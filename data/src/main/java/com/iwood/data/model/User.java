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
	private String realName;
	private String company;
	private String mainProducts;
	private String passWord;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMainProducts() {
		return mainProducts;
	}

	public void setMainProducts(String mainProducts) {
		this.mainProducts = mainProducts;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
