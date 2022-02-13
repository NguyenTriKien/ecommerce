package com.ecommerce.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecommerce.dao.AccountDAO;
import com.ecommerce.entity.Account;
import com.ecommerce.model.AccountInfo;
import com.ecommerce.model.CustomerInfo;



@Component
public class AccountInfoValidator implements Validator{
	
	@Autowired
	private AccountDAO accountDAO;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AccountInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		AccountInfo accountInfo = (AccountInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.accountForm.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm.password");
		
		String username = accountInfo.getUsername();
		Account accountDB = accountDAO.getAccountByUserName(username);
		if(accountDB != null) {
			if(username.equals(accountDB.getUsername())) {
				errors.rejectValue("username", "Duplicate.accountForm.username");
			}
		}
	
	}


	
}
