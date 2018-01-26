/**
 * 
 */
package com.ricardopalvesjr.parser.factory;

import java.text.SimpleDateFormat;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.ricardopalvesjr.parser.domain.ParamsDO;
import com.ricardopalvesjr.parser.enums.DurationEnum;

/**
 * Factory class for Parser parameter object.
 * 
 * @author ricardopalvesjr
 *
 */
@Component
public class ParamsFactory implements FactoryBean<ParamsDO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParamsFactory.class);

	@Autowired
	private MessageSource messageSource;

	private final String ACCESS_LOG_PARAM = "accesslog";
	private final String START_DATE_PARAM = "startDate";
	private final String DURATION_PARAM = "duration";
	private final String THRESHOLD_PARAM = "threshold";

	private String[] args;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public ParamsDO getObject() throws Exception {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
		ParamsDO params = new ParamsDO();
		CommandLine cmd = parseCmdOptions();

		params.setAccesslog(cmd.getOptionValue(ACCESS_LOG_PARAM));
		String sdate = cmd.getOptionValue(START_DATE_PARAM);
		params.setStartDate(sdformat.parse(sdate));
		params.setDuration(cmd.getOptionValue(DURATION_PARAM).toUpperCase());
		validate(params);

		DurationEnum durationEnum = DurationEnum.valueOf(params.getDuration());
		Integer thresholdReceived = Integer.valueOf(cmd.getOptionValue(THRESHOLD_PARAM));
		Integer limit = durationEnum.retrieveLimit();
		if (thresholdReceived > limit) {
			thresholdReceived = limit;
		}
		params.setThreshold(limit);
		params.setEndDate(durationEnum.calculateEndDate(params.getStartDate()));

		LOGGER.debug(params.toString());

		return params;
	}

	/**
	 * Parse command line options inputed.
	 * 
	 * @return CommandLine the command line arguments parsed.
	 * 
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	private CommandLine parseCmdOptions() throws IllegalArgumentException, ParseException {
		Options options = new Options();
		Option accesslog = new Option("a", ACCESS_LOG_PARAM, true, "access log file path");
		accesslog.setRequired(true);
		options.addOption(accesslog);

		Option startDate = new Option("s", START_DATE_PARAM, true, "starting log date");
		startDate.setRequired(true);
		options.addOption(startDate);

		Option duration = new Option("d", DURATION_PARAM, true, "duration of events");
		duration.setRequired(true);
		options.addOption(duration);

		Option threshold = new Option("t", THRESHOLD_PARAM, true, "threshold of events");
		threshold.setRequired(true);
		options.addOption(threshold);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);

		} catch (ParseException e) {
			formatter.printHelp("AccessLogParser", options);
			throw e;
		}

		return cmd;
	}

	/**
	 * Validate parameter object before deliver.
	 * 
	 * @param params
	 *            ParamsDO Object to validate.
	 */
	private void validate(ParamsDO params) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		try {
			validator.validate(params);

		} catch (ValidationException e) {
			LOGGER.error(
					messageSource.getMessage("error.duration.invalid", new Object[] { params.getDuration() }, null));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return ParamsDO.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(String[] args) {
		this.args = args;
	}

}
