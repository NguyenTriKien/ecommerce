package com.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", length = 50)
	private String id;
	
	@Column(name = "Order_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@Column(name = "Order_Num", nullable = false)
	private int orderNum;
	
	@Column(name = "Amount", nullable = false)
	private double amount;
	
	@Column(name = "Customer_Name", length = 255, nullable = false)
	private String customerName;
	
	@Column(name = "Customer_Address", length = 255, nullable = false)
	private String customerAddress;
	
	@Column(name = "Customer_Email", length = 128, nullable = false)
	private String customerEmail;
	
	@Column(name = "Customer_Phone", length = 128, nullable = false)
	private String customerPhone;
	
	@Column(name = "Order_Status", length = 128, nullable = false)
	private String orderstatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GoogleEmail",foreignKey = @ForeignKey(name = "ORDER_GMAIL_FK"), updatable = true, insertable = true)
	private GoogleAccount gmail;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false, foreignKey = @ForeignKey(name ="ORDER_USER_FK"),updatable = true, insertable = true)
	private User user;*/

	
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

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public GoogleAccount getGmail() {
		return gmail;
	}

	public void setGmail(GoogleAccount gmail) {
		this.gmail = gmail;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGoogleAccount() {
		return GoogleAccount;
	}*/

	/*public void setGoogleAccount(String googleAccount) {
		GoogleAccount = googleAccount;
	}*/
	
	
	
	
}

