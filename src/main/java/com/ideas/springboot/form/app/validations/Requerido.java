package com.ideas.springboot.form.app.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RequeridoValidador.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface Requerido {
	
	String message() default "El campo es requerido - ss";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
