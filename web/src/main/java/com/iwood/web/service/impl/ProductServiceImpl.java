package com.iwood.web.service.impl;

import com.iwood.data.mapper.ProductMapper;
import com.iwood.data.model.Product;
import com.iwood.web.conf.Constants;
import com.iwood.web.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-3-3
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductMapper productMapper;

	@Override
	public boolean addProduct(int pstat, String pname, String material, String origin, String process, String specifications, String plevel, double price, double amount, String storehouse, String application, String company, String thumbnail, String imgs, long uid, int rtype) {
		Product product = new Product();
		product.setPstat(pstat);
		product.setPname(pname);
		product.setMaterial(material);
		product.setOrigin(origin);
		product.setSpecifications(specifications);
		product.setPlevel(plevel);
		product.setPrice(price);
		product.setAmount(amount);
		product.setStorehouse(storehouse);
		product.setApplication(application);
		product.setCompany(company);
		product.setThumbnail(thumbnail);
		product.setImgs(imgs);
		product.setUid(uid);
		product.setRtype(rtype);
		try {
			productMapper.addProduct(product);
		} catch (Exception e) {
			LOGGER.warn("add product to db error! e = {}", e);
			return false;
		}
		return true;
	}

	@Override
	public List<Product> getLastestProducts(int pages) {
		List<Product> products = Collections.emptyList();
		try {
			return productMapper.getLastestProducts(getPageIndex(pages, Constants.default_page_size), Constants.default_page_size);
		} catch (Exception e) {
			LOGGER.warn("getLastestProducts from db error! e = {}", e);
		}
		return products;
	}

	@Override
	public List<Product> getLastestProductsByPrice(int pages) {
		List<Product> products = Collections.emptyList();
		try {
			return productMapper.getLastestProductsByPrice(getPageIndex(pages, Constants.default_page_size), Constants.default_page_size);
		} catch (Exception e) {
			LOGGER.warn("getLastestProductsByPrice from db error! e = {}", e);
		}
		return products;
	}

	@Override
	public List<Product> getLastestProductsByAmount(int pages) {
		List<Product> products = Collections.emptyList();
		try {
			return productMapper.getLastestProductsByAmount(getPageIndex(pages, Constants.default_page_size), Constants.default_page_size);
		} catch (Exception e) {
			LOGGER.warn("getLastestProductsByAmount from db error! e = {}", e);
		}
		return products;
	}

	@Override
	public List<Product> getLastestProductsByUid(long uid, int pages) {
		List<Product> products = Collections.emptyList();
		try {
			return productMapper.getProductsByUid(uid, getPageIndex(pages, Constants.default_page_size), Constants.default_page_size);
		} catch (Exception e) {
			LOGGER.warn("getLastestProductsByUid from db error! e = {}", e);
		}
		return products;
	}

	private static int getPageIndex(int page, int defaultPageSize) {
		return (page - 1) * defaultPageSize;
	}
}
