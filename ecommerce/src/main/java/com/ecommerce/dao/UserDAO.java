package com.ecommerce.dao;

import com.ecommerce.entity.User;
import com.ecommerce.model.OrderInfo;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.UserInfo;

public interface UserDAO {

	public User getUserById(String userid);
	
	public User getUserByName(String username);
	
	public void saveUserInfo(UserInfo userInfo);
	
	public PaginationResult<OrderInfo> getAllOrderInfos(int page, int maxResult, int maxNavigationPage);
}
