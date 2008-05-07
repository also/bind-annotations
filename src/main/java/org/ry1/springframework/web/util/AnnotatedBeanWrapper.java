/* $Id: AnnotatedBeanWrapper.java 155 2008-04-19 08:48:38Z rberdeen $ */

package org.ry1.springframework.web.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.core.annotation.AnnotationUtils;

public class AnnotatedBeanWrapper extends BeanWrapperImpl {
	private String context;
	
	public AnnotatedBeanWrapper(String context) {
		super();
		this.context = context;
	}

	public AnnotatedBeanWrapper(boolean registerDefaultEditors, String context) {
		super(registerDefaultEditors);
		this.context = context;
	}

	public AnnotatedBeanWrapper(Class<?> clazz, String context) {
		super(clazz);
		this.context = context;
	}

	public AnnotatedBeanWrapper(Object object, String nestedPath, Object rootObject, String context) {
		super(object, nestedPath, rootObject);
		this.context = context;
	}

	public AnnotatedBeanWrapper(Object object, String context) {
		super(object);
		this.context = context;
	}
	
	@Override
	protected PropertyDescriptor getPropertyDescriptorInternal(String propertyName) throws BeansException {
		PropertyDescriptor pd =  super.getPropertyDescriptorInternal(propertyName);
		if (pd != null) {
			Method writeMethod = pd.getWriteMethod();
			if (writeMethod != null) {
				Bindable annotation = AnnotationUtils.getAnnotation(writeMethod, Bindable.class);
				if (annotation != null) {
					if (annotation.value().length == 0) {
						return pd;
					}
					for (String allowedContext : annotation.value()) {
						if (allowedContext.equals(context)) {
							return pd;
						}
					}
				}
			}
			
			try {
				return new PropertyDescriptor(propertyName, pd.getReadMethod(), null);
			}
			catch (IntrospectionException e) {
				return null;
			}
		}
		
		return null;
	}

	@Override
	public boolean isWritableProperty(String propertyName) {
		if (super.isWritableProperty(propertyName)) {
			PropertyDescriptor pd = getPropertyDescriptorInternal(propertyName);
			if (pd != null) {
				Method writeMethod = pd.getWriteMethod();
				if (writeMethod != null) {
					Bindable annotation = AnnotationUtils.getAnnotation(writeMethod, Bindable.class);
					if (annotation != null) {
						if (annotation.value().length == 0) {
							return true;
						}
						for (String allowedContext : annotation.value()) {
							if (allowedContext.equals(context)) {
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
}
