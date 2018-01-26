package com.ricardopalvesjr.parser.service;

import com.ricardopalvesjr.parser.domain.ParamsDO;
import com.ricardopalvesjr.parser.exception.ParserServiceException;

/**
 * Parser service interface.
 * 
 * @author ricardopalvesjr
 *
 */
public interface ParserServiceInterface {

	/**
	 * Parses a log file entries, print them to console and also load them to the
	 * database.
	 * 
	 * @param params
	 *            ParamsDO Parameters object received.
	 * @throws ParserServiceException
	 */
	public void parseLog(ParamsDO params) throws ParserServiceException;

}
