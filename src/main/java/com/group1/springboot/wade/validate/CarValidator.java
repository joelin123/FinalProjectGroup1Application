package com.group1.springboot.wade.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.group1.springboot.wade.model.Car;


@Component
public class CarValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Car.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		Car car =(Car) target;
//		ValidationUtils.rejectIfEmpty(errors, "Id", "car.Id.notempty",  "編號不能是空白(D.V.)");
		ValidationUtils.rejectIfEmpty(errors, "name", "car.name.notempty","名稱不能是空白(D.V.)");
		ValidationUtils.rejectIfEmpty(errors, "price", "car.price.notempty", "價格不能是空白(D.V.)");
		
//       if(event.getName() == null) {
//	}
  }
}
