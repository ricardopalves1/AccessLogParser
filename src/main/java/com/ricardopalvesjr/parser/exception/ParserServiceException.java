/**
 * 
 */
package com.ricardopalvesjr.parser.exception;

/**
 * Exception class according to <ParserService>.
 * 
 * @author ricardopalvesjr
 *
 */
public class ParserServiceException extends Exception {

	private static final long serialVersionUID = -3351357950085066064L;

	/**
	 * Default constructor method.
	 */
	public ParserServiceException() {
		super();
	}

	/**
	 * Constructor method with message.
	 * 
	 * @param message
	 *            String the exception message.
	 */
	public ParserServiceException(String message) {
		super(message);
	}

	/**
	 * Constructor method with cause.
	 * 
	 * @param cause
	 *            Throwable the exception cause.
	 */
	public ParserServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor method with message and cause.
	 * 
	 * @param message
	 *            String the exception message.
	 * @param cause
	 *            Throwable the exception cause.
	 */
	public ParserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor method with message and cause. Moreover, whether or not
	 * suppression and stack trace should be used.
	 * 
	 * @param message
	 *            String the exception message.
	 * @param cause
	 *            Throwable the exception cause.
	 * @param enableSuppression
	 *            boolean whether or not suppression is enabled or disabled
	 * @param writableStackTrace
	 *            boolean whether or not the stack trace should be writable
	 */
	public ParserServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
