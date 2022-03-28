package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.entity.Product;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.ProductInfo;
import com.ecommerce.model.TypeInfo;
import com.ecommerce.entity.Type;

public interface ProductDAO {

	public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult, int maxNavigationPage,
			String likeName, double price);

	public Product getProductByCode(String code);

	public ProductInfo getProductInfoByCode(String code);

	public void saveProductInfo(ProductInfo productInfo);

	public boolean removeProductByCode(String code);
	
	public Product updateProductQuantity(String code, int quantity);
	
	public PaginationResult<ProductInfo> getAllProductInfoByType(int page, int maxResult, int maxNavigationPage,
			String type);
	
}
