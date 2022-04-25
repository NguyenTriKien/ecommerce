package com.ecommerce.validator;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecommerce.dao.UserAccountDAO;
import com.ecommerce.entity.UserAccount;
import com.ecommerce.model.UserAccountInfo;

@Component
public class LoginValidator implements Validator {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserAccountInfo.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
        UserAccountInfo userAccountInfo = (UserAccountInfo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userAccount.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userAccount.password");
		
		String username = userAccountInfo.getUsername();
		String password = userAccountInfo.getPassword();
		UserAccount userAccountDB = userAccountDAO.getAccountByUsername(username);
		if(userAccountDB != null) {
			if(!username.equals(userAccountDB.getUsername())) {
				errors.rejectValue("username", "Invalid.userAccount.username");
			}else if(!password.equals(userAccountDB.getPassword())) {
				errors.rejectValue("password", "Invalid.userAccount.password");
			}
		}if(userAccountDB == null) {
			errors.rejectValue("username", "Null.userAccount.username");
			
		}

	}

}
