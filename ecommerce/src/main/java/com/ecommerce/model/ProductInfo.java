package com.ecommerce.model;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.Type;

public class ProductInfo {

private String code;
	
	private String name;
	
	private Type type;
	
	private double price;
	
	private boolean newProduct = false;
	
	private CommonsMultipartFile fileData;
	
	private List<Type> types;
	
	public ProductInfo() {
		super();
	}

	public ProductInfo(String code, String name, Type type, double price) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public ProductInfo(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.type = product.getType();
		this.price = product.getPrice();
	}

	public ProductInfo(String code, String name, Type type, double price, boolean newProduct,
			CommonsMultipartFile fileData, List<Type> types) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
		this.price = price;
		this.newProduct = newProduct;
		this.fileData = fileData;
		this.types = types;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}
	
}
