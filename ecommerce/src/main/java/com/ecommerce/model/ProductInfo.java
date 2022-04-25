package com.ecommerce.model;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ecommerce.entity.Producer;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Type;

public class ProductInfo {

    private String code;
	
	private String name;
	
	private String cpu;
	
	private String ram;
	
    private double screen;
    
    private String gpu;
    
    private String storage;
    
    private String os;
    
    private int quantity;
    
    private String status;
	
	private Type type;
	
	private Producer producer;
	
	private double price;
	
	private boolean newProduct = false;
	
	private CommonsMultipartFile fileData;
	
	private List<Type> types;
	
	private List<Producer> producers;
	
	public ProductInfo() {
		super();
	}
    
	public ProductInfo(String code, String name, int quantity, String status, Type type, Producer producer,
			double price) {
		super();
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.status = status;
		this.type = type;
		this.producer = producer;
		this.price = price;
	}

	public ProductInfo(String code, String name, int quantity, String status, Type type, double price) {
		super();
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.status = status;
		this.type = type;
		this.price = price;
	}

	public ProductInfo(String code, String name, String cpu, String ram, double screen, String gpu, String storage,
			String os, int quantity, String status, Type type, Producer producer, double price) {
		super();
		this.code = code;
		this.name = name;
		this.cpu = cpu;
		this.ram = ram;
		this.screen = screen;
		this.gpu = gpu;
		this.storage = storage;
		this.os = os;
		this.quantity = quantity;
		this.status = status;
		this.type = type;
		this.producer = producer;
		this.price = price;

	}

	public ProductInfo(String code, String name, String cpu, String ram, double screen, String gpu, String storage,
			String os, int quantity, Type type, Producer producer, double price, boolean newProduct,
			CommonsMultipartFile fileData, List<Type> types, List<Producer> producers) {
		super();
		this.code = code;
		this.name = name;
		this.cpu = cpu;
		this.ram = ram;
		this.screen = screen;
		this.gpu = gpu;
		this.storage = storage;
		this.os = os;
		this.quantity = quantity;
		this.type = type;
		this.producer = producer;
		this.price = price;
		this.newProduct = newProduct;
		this.fileData = fileData;
		this.types = types;
		this.producers = producers;
	}

	public ProductInfo(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.cpu = product.getCpu();
		this.ram = product.getRam();
		this.screen = product.getScreen();
		this.gpu = product.getGpu();
		this.storage = product.getStorage();
		this.os = product.getOs();
		this.quantity = product.getQuantity();
		this.type = product.getType();
		this.producer = product.getProducer();
		this.price = product.getPrice();
		this.status = product.getStatus();
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

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public double getScreen() {
		return screen;
	}

	public void setScreen(double screen) {
		this.screen = screen;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
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

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
}
