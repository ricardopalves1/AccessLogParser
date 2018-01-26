/**
 * 
 */
package com.ricardopalvesjr.parser.service;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ricardopalvesjr.parser.domain.ParamsDO;
import com.ricardopalvesjr.parser.factory.ParamsFactory;

/**
 * Test case for <ParserService>.
 * 
 * @author ricardopalvesjr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceTest {

	@Autowired
	private ParamsFactory paramsFactory;

	@Autowired
	private ParserServiceInterface parserService;

	private final String[] HOURLY_ARGS = { "--accesslog", "/Users/ricardopalvesjr/Downloads/Java_MySQL_Test/access.log",
			"--startDate", "2017-01-01.15:00:00", "--duration", "hourly", "--threshold", "250" };

	private final String[] DAILY_ARGS = { "--accesslog", "/Users/ricardopalvesjr/Downloads/Java_MySQL_Test/access.log",
			"--startDate", "2017-01-01.00:00:00", "--duration", "daily", "--threshold", "500" };

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// parserService.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
		// parserService.deleteAll();
	}

	/**
	 * Test method for
	 * {@link com.ricardopalvesjr.parser.service.ParserService#parseLog(com.ricardopalvesjr.parser.domain.ParamsDO)}.
	 */
	@Test
	public final void testParseLog_HOURLY() {
		paramsFactory.setArgs(HOURLY_ARGS);
		ParamsDO params = null;
		try {
			params = paramsFactory.getObject();
			parserService.parseLog(params);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.ricardopalvesjr.parser.service.ParserService#parseLog(com.ricardopalvesjr.parser.domain.ParamsDO)}.
	 */
	@Test
	public final void testParseLog_DAILY() {
		paramsFactory.setArgs(DAILY_ARGS);
		ParamsDO params = null;
		try {
			params = paramsFactory.getObject();
			parserService.parseLog(params);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
