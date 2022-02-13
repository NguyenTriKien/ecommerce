package com.ecommerce.dao;

import com.ecommerce.entity.Account;
import com.ecommerce.model.AccountInfo;
import com.ecommerce.model.PaginationResult;

public interface AccountDAO {

	public Account getAccountByUserName(String userName);

	public PaginationResult<AccountInfo> getAllAccountInfos(int page, int maxResult, int maxNavigationPage);

	public void saveAccountInfo(AccountInfo accountInfo);

	public boolean removeAccountByUsername(String username);
}
