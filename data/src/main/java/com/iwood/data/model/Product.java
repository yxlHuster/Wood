package com.iwood.data.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-3-3
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
public class Product {

	private long id; //产品ID
	private int pstat; //类型，现货
	private String pname; //产品名称
	private String material; //材质
	private String origin; //产地
	private String process; //加工
	private String specifications; //规格
	private String plevel; //等级
	private double price; //价格
	private double amount; //数量
	private String storehouse; //仓库
	private String application; //用途
	private Date created; //入库时间
	private Date modified; //更新时间
   	private String company; //公司信息

	/*added img infos*/
	private String thumbnail; //缩略图
	private String imgs; //图片列表

	private long uid; //产品发布方ID
	private int rtype; //求购或者供货

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPstat() {
		return pstat;
	}

	public void setPstat(int pstat) {
		this.pstat = pstat;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getPlevel() {
		return plevel;
	}

	public void setPlevel(String plevel) {
		this.plevel = plevel;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getStorehouse() {
		return storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getRtype() {
		return rtype;
	}

	public void setRtype(int rtype) {
		this.rtype = rtype;
	}
}
