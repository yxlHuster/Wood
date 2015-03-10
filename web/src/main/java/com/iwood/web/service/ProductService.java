package com.iwood.web.service;

import com.iwood.data.model.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-3-3
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public interface ProductService {

	public boolean addProduct(int pstat, String pname, String material, String origin,
								String process, String specifications, String plevel, double price,
								double amount, String storehouse, String application, String company,
								String thumbnail, String imgs, long uid, int rtype);

	public List<Product> getLastestProducts(int pages);

	public List<Product> getLastestProductsByPrice(int pages);

	public List<Product> getLastestProductsByAmount(int pages);

	public List<Product> getLastestProductsByUid(long uid, int pages);


}
