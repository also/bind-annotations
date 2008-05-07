/* $Id: AnnotatedServletRequestDataBinder.java 155 2008-04-19 08:48:38Z rberdeen $ */

package org.ry1.springframework.web.util;

import org.springframework.validation.AbstractPropertyBindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;

public class AnnotatedServletRequestDataBinder extends ServletRequestDataBinder {
	private String context;
	private AnnotatedBeanPropertyBindingResult bindingResult;
	public AnnotatedServletRequestDataBinder(Object target, String context) {
		super(target);
		this.context = context;
	}

	public AnnotatedServletRequestDataBinder(Object target, String objectName, String context) {
		super(target, objectName);
		this.context = context;
	}

	@Override
	protected AbstractPropertyBindingResult getInternalBindingResult() {
		if (bindingResult == null) {
			bindingResult = new AnnotatedBeanPropertyBindingResult(getTarget(), getObjectName(), context);
		}
		return bindingResult;
	}
}
