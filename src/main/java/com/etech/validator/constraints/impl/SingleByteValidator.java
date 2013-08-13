package com.etech.validator.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.etech.validator.constraints.SingleByte;

public class SingleByteValidator implements ConstraintValidator<SingleByte, String>{
	
	
	@Override
	public void initialize(SingleByte parameters) {
		validateParameters();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null)
			return true;
		
		for (int i=0; i<value.length(); i++) {
			if (((int)value.charAt(i))>126)
				return false;
		}
		
		return true;
	}
	
	private void validateParameters() {
		/*
		if (this.value<0)
			throw new IllegalArgumentException("The value cannot be nagetive.");
		*/
	}

}
