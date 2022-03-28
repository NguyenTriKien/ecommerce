package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.entity.Order;
import com.ecommerce.model.CartInfo;
import com.ecommerce.model.OrderDetailInfo;
import com.ecommerce.model.OrderInfo;
import com.ecommerce.model.PaginationResult;

public interface OrderDAO {

	public void saveOrder(CartInfo cartInfo);

	public PaginationResult<OrderInfo> getAllOrderInfosByEmail(int page, int maxResult, int maxNavigationPage, String userAccount);

	public Order getOrderById(String orderId);

	public OrderInfo getOrderInfoById(String orderId);
	
	public OrderInfo getOrderInfoByGoogleId(String gmail);
	
	public Order getOrderByGoogleId(String GoogleAccount);

	public List<OrderDetailInfo> getAllDetailInfos(String orderId);
	
	public Order updateOrderStatus(String orderId);
	
	
	
}
