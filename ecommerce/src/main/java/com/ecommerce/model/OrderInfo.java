package com.ecommerce.model;

import java.util.Date;
import java.util.List;

import com.ecommerce.entity.UserAccount;
import com.ecommerce.entity.Order;

public class OrderInfo {
 
	private String id;

	private Date orderDate;

	private int orderNum;

	private double amount;

	private String customerName;

	private String customerAddress;

	private String customerPhone;
	
	private String orderstatus;
	
	private UserAccount userAccount;

	private List<OrderDetailInfo> orderDetailInfos;

	
	public OrderInfo(String id, Date orderDate, int orderNum, double amount, String customerName,
			String customerAddress, String customerPhone, String orderstatus, UserAccount userAccount) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.orderstatus = orderstatus;
		this.userAccount = userAccount;
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
    
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public List<OrderDetailInfo> getOrderDetailInfos() {
		return orderDetailInfos;
	}

	public void setOrderDetailInfos(List<OrderDetailInfo> orderDetailInfos) {
		this.orderDetailInfos = orderDetailInfos;
	}

	
}
