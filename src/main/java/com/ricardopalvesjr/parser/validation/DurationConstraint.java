package com.ricardopalvesjr.parser.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation Constraint Interface for duration validation purposes.
 * 
 * @author ricardopalvesjr
 *
 */
@Documented
@Constraint(validatedBy = DurationValidator.class)
@Target({ METHOD, FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface DurationConstraint {

	String message() default "{DurationConstraint value NOT supported!}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] vereisteRollen() default {};
}
