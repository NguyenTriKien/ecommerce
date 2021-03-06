package com.ecommerce.validator;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecommerce.model.CustomerInfo;

@Component
public class CustomerInfoValidator implements Validator {
    
	public boolean supports(Class<?> clazz) {
		return CustomerInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomerInfo customerInfo = (CustomerInfo) target;
        String phone = customerInfo.getPhone();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");

		try {
			Integer.parseInt(phone);
		} catch (Exception e) {
			errors.rejectValue("phone", "Pattern.customerForm.phone");
		}
	}

}
