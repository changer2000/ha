/*
 * Copyright (C) by Prima Solutions, All Rights Reserved.
 * 
 * THIS MATERIAL IS CONSIDERED A TRADE SECRET BY PRIMA SOLUTIONS. UNAUTHORIZED ACCESS, USE, MODIFICATION, REPRODUCTION OR DISTRIBUTION IS PROHIBITED.
 */
package net.ambre.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface LookupMethod {
    String beanRef() default "";

    String qualifier() default "";
}
