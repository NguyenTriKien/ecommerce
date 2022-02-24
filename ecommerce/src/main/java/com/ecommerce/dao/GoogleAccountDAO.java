package com.ecommerce.dao;

import com.ecommerce.entity.GoogleAccount;
import com.ecommerce.model.GooglePojo;

public interface GoogleAccountDAO {

	public void saveGoogleAccount(GooglePojo googlePojo);
	
	public GoogleAccount getGoogleAccountByEmail(String email);
}
