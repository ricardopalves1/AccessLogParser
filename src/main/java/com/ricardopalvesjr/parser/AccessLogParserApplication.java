package com.ricardopalvesjr.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;

import com.ricardopalvesjr.parser.domain.ParamsDO;
import com.ricardopalvesjr.parser.factory.ParamsFactory;
import com.ricardopalvesjr.parser.service.ParserServiceInterface;

/**
 * Main application class.
 * 
 * @author ricardopalvesjr
 *
 */
@ComponentScan("com.ricardopalvesjr.parser")
@SpringBootApplication
public class AccessLogParserApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogParserApplication.class);

	@Autowired
	private ParamsFactory paramsFactory;

	@Autowired
	private ParserServiceInterface parserService;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder()
				.sources(AccessLogParserApplication.class).run(args);

		MessageSource messageSource = context.getBean(MessageSource.class);
		LOGGER.info(messageSource.getMessage("debug.process.stop", null, null));
		SpringApplication.exit(context, () -> 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		paramsFactory.setArgs(args);
		ParamsDO params = null;
		try {
			params = paramsFactory.getObject();
			LOGGER.debug(params.toString());
			parserService.parseLog(params);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
