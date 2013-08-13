package com.etech.validator.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.etech.validator.constraints.impl.SingleByteValidator;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=SingleByteValidator.class)
@Documented
public @interface SingleByte {
	
	String message() default "{com.etech.constraints.singlebyte}";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
	
}
