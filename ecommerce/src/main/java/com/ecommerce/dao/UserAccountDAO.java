package com.ecommerce.dao;

import com.ecommerce.entity.UserAccount;
import com.ecommerce.model.GooglePojo;
import com.ecommerce.model.UserAccountInfo;

public interface UserAccountDAO {

	public void saveGoogleAccount(GooglePojo googlePojo);
	
	public void saveUserAccount(UserAccountInfo userAccountInfo);
	
	public UserAccount getGoogleAccountByEmail(String email);
	
	public UserAccount getAccountByUsername(String username);
}
