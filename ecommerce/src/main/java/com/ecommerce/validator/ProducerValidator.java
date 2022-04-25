package com.ecommerce.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecommerce.dao.ProducerDAO;
import com.ecommerce.entity.Producer;
import com.ecommerce.model.ProducerInfo;

@Component
public class ProducerValidator implements Validator {

	@Autowired
	ProducerDAO producerDAO;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProducerInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProducerInfo producerInfo = (ProducerInfo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "producerid", "NotEmpty.producerForm.producerid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "producername", "NotEmpty.producerForm.producername");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.producerForm.country");
		
		String producerid = producerInfo.getProducerid();
		
		Producer producerDB = producerDAO.getProducerById2(producerid);
		if(producerDB != null) {
			if(producerid.equals(producerDB.getProducerid())) {
				errors.rejectValue("producername", "Duplicate.producerForm.producerid");
			}
		}
	}

}
