package com.ecommerce.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.model.ProductInfo;

@Component
public class ProductValidator implements Validator {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductInfo productInfo = (ProductInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpu", "NotEmpty.productForm.cpu");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ram", "NotEmpty.productForm.ram");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "screen", "NotEmpty.productForm.screen");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gpu", "NotEmpty.productForm.screen");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storage", "NotEmpty.productForm.storage");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "os", "NotEmpty.productForm.os");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.productForm.quantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "NotEmpty.productForm.type");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "producer", "NotEmpty.productForm.producer");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "NotEmpty.productForm.status");

		String code = productInfo.getCode();
		double price = productInfo.getPrice();
		if (code != null && code.length() > 0) {
			if (code.matches("\\s+")) {
				errors.rejectValue("code", "Pattern.productForm.code");
			} else if (productInfo.isNewProduct()) {
				Product product = productDAO.getProductByCode(code);
				if (product != null) {
					errors.rejectValue("code", "Duplicate.productForm.code");
				}
			}
		}
		
		if(price <= 0) {
			errors.rejectValue("price", "Pattern.productForm.price");
		}
	}

}
