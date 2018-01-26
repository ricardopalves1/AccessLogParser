/**
 * 
 */
package com.ricardopalvesjr.parser.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ricardopalvesjr.parser.enums.DurationEnum;

/**
 * DurationConstraint argument validator class.
 * 
 * @author ricardopalvesjr
 *
 */
public class DurationValidator implements ConstraintValidator<DurationConstraint, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DurationValidator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(DurationConstraint constraintAnnotation) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		DurationEnum duration = DurationEnum.valueOf(value);
		LOGGER.debug(String.valueOf(duration.retrieveLimit()));

		return true;
	}
}