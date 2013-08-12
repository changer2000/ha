package com.etech.validator.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.etech.validator.constraints.impl.ByteCountValidator;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=ByteCountValidator.class)
@Documented
public @interface ByteCount {
	
	String message() default "{com.etech.constraints.bytecount}";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
	
	int value() default 0;
	
}
