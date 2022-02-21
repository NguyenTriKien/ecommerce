package com.ecommerce.dao;

import com.ecommerce.entity.User;
import com.ecommerce.model.OrderInfo;
import com.ecommerce.model.PaginationResult;

public interface UserDAO {

	public User getUserById(String userid);
	
	public User getUserByName(String username);
	
	public PaginationResult<OrderInfo> getAllOrderInfos(int page, int maxResult, int maxNavigationPage);
}
