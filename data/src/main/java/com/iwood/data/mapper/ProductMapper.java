package com.iwood.data.mapper;

import com.iwood.data.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-3-3
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public interface ProductMapper {

	public void addProduct(Product product);

	public List<Product> getLastestProducts(@Param("startPos") long startPos,
											@Param("offset") long offset);

	public List<Product> getLastestProductsByPrice(@Param("startPos") long startPos,
												   @Param("offset") long offset);

	public List<Product> getLastestProductsByAmount(@Param("startPos") long startPos,
													@Param("offset") long offset);

	public List<Product> getProductsByUid(@Param("uid") long uid,
										  @Param("startPos") long startPos,
										  @Param("offset") long offset);

	public void updateProduct(Product product);

}
