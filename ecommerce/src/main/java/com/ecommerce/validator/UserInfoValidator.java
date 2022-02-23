package com.ecommerce.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.entity.User;
import com.ecommerce.model.UserInfo;

@Component
public class UserInfoValidator {

	@Autowired
	private UserDAO userDAO;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserInfo userInfo = (UserInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.user.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
		
		String username = userInfo.getUsername();
		User userDB = userDAO.getUserByName(username);
		if(userDB != null) {
			if(username.equals(userDB.getUsername())) {
				errors.rejectValue("username", "Duplicate.userForm.username");
			}
		}
	
	}
}
