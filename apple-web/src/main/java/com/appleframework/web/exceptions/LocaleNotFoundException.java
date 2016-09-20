package com.appleframework.web.exceptions;

/**
 *
 * @author Cruise.Xu
 * @date: 2011-9-9
 *
 */
public class LocaleNotFoundException extends RuntimeException {
	   private static final long serialVersionUID = -6099587474634812356L;

	   public LocaleNotFoundException(String localeCode) {
	      super(String.format("UserLocale = %s not found", localeCode));
	   }
	}
