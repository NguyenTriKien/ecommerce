package com.ecommerce.model;

import java.util.Date;
import java.util.List;

import com.ecommerce.entity.GoogleAccount;
import com.ecommerce.entity.Order;

public class OrderInfo {
 
	private String id;

	private Date orderDate;

	private int orderNum;

	private double amount;

	private String customerName;

	private String customerAddress;

	private String customerEmail;

	private String customerPhone;
	
	private String orderstatus;
	
	private GoogleAccount gmail;

	private List<OrderDetailInfo> orderDetailInfos;

	public OrderInfo(String id, Date orderDate, int orderNum, double amount, String customerName,
			String customerAddress, String customerEmail, String customerPhone, String orderstatus,
			GoogleAccount gmail) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.orderstatus = orderstatus;
		this.gmail = gmail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public List<OrderDetailInfo> getOrderDetailInfos() {
		return orderDetailInfos;
	}

	public void setOrderDetailInfos(List<OrderDetailInfo> orderDetailInfos) {
		this.orderDetailInfos = orderDetailInfos;
	}

	public GoogleAccount getGmail() {
		return gmail;
	}

	public void setGmail(GoogleAccount gmail) {
		this.gmail = gmail;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
}
