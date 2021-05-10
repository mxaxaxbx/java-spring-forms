package com.ideas.springboot.form.app.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || !StringUtils.hasText(value) ? false : true;
	}

}
