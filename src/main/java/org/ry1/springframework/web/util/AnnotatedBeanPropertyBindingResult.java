/* $Id: AnnotatedBeanPropertyBindingResult.java 155 2008-04-19 08:48:38Z rberdeen $ */

package org.ry1.springframework.web.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.BeanPropertyBindingResult;

public class AnnotatedBeanPropertyBindingResult extends BeanPropertyBindingResult {
	private static final long serialVersionUID = 1L;
	private String context;

	public AnnotatedBeanPropertyBindingResult(Object target, String objectName, String context) {
		super(target, objectName);
		this.context = context;
	}
	
	@Override
	protected BeanWrapper createBeanWrapper() {
		return new AnnotatedBeanWrapper(getTarget(), context);
	}
}
