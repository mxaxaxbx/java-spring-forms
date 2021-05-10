package com.ideas.springboot.form.app.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean match;
		match = value.matches("[0-9]{1}[-][A-Z]{1}") ? true : false;
		return match;
	}
}
