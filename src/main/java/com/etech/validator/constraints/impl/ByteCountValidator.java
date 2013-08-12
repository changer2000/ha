package com.etech.validator.constraints.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.etech.validator.constraints.ByteCount;

public class ByteCountValidator implements ConstraintValidator<ByteCount, String>{
	
	private int value;
	
	@Override
	public void initialize(ByteCount parameters) {
		this.value = parameters.value();
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
		if (this.value<0)
			throw new IllegalArgumentException("The value cannot be nagetive.");
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
