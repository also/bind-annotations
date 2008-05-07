/* $Id: Bindable.java 155 2008-04-19 08:48:38Z rberdeen $ */

package org.ry1.springframework.web.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bindable {
	String[] value() default {};
}
