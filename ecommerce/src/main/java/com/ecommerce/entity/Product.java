package com.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "Code", length = 20, nullable = false)
	private String code;
	
	@Column(name = "Name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "CPU", length = 255, nullable = false)
	private String cpu;
	
	@Column(name = "RAM", length = 255, nullable = false)
	private String ram;
	
	@Column(name = "Screen", nullable = false)
	private double screen;
	
	@Column(name = "GPU", length = 255, nullable = false)
	private String gpu;
	
	@Column(name = "Storage", nullable = false)
	private String storage;
	
	@Column(name = "OS", length = 255, nullable = false )
	private String os;
	
	@Column(name = "Quantity", length = 255, nullable = false )
	private int quantity;
	
	@Column(name = "Status", length = 255, nullable = false )
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TYPE_ID", nullable = false, foreignKey = @ForeignKey(name ="PRODUCT_TYPE_FK"),updatable = true, insertable = true)
	private Type type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCER_ID", nullable = false, foreignKey = @ForeignKey(name ="PRODUCT_PRODUCER_FK"),updatable = true, insertable = true)
	private Producer producer;
	
	@Column(name = "Price", nullable = false)
	private double price;
	
	@Lob
	@Column(name = "Image",length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Create_Date", nullable = false)
	private Date createDate;

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
