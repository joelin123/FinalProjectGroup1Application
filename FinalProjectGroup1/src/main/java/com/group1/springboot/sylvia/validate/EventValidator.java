package com.group1.springboot.sylvia.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.group1.springboot.sylvia.model.Event;


@Component
public class EventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Event.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		Event event =(Event) target;
		ValidationUtils.rejectIfEmpty(errors, "Id", "event.Id.notempty",  "編號不能是空白(D.V.)");
		ValidationUtils.rejectIfEmpty(errors, "name", "event.name.notempty","名稱不能是空白(D.V.)");
		ValidationUtils.rejectIfEmpty(errors, "price", "event.price.notempty", "價格不能是空白(D.V.)");
		
//       if(event.getName() == null) {
//	}
  }
}
