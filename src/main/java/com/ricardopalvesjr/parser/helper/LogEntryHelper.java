package com.ricardopalvesjr.parser.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ricardopalvesjr.parser.domain.ParamsDO;
import com.ricardopalvesjr.parser.persistence.BlockedIpEntry;
import com.ricardopalvesjr.parser.persistence.BlockedIpEvent;

/**
 * Log entry helper class.
 * 
 * @author ricardopalvesjr
 *
 */
public class LogEntryHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogEntryHelper.class);

	/**
	 * Private constructor method.
	 */
	private LogEntryHelper() {
	}

	/**
	 * Reads a log file line and retrieves the respective <Map> of entities
	 * populated.
	 * 
	 * @param params
	 *            ParamsDO Parameters object received.
	 * @return Map<String, BlockedIpEntry> the Map with IP address and their
	 *         respective IP blocked details.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Map<String, BlockedIpEntry> getLogLinesMap(ParamsDO params) throws IOException, ParseException {
		Map<String, BlockedIpEntry> ips = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Stream<String> stream = null;
		try {
			stream = Files.lines(Paths.get(params.getAccesslog()));
			Iterator<String> it = stream.iterator();
			Date entryDate = null;
			String entryIp = null;
			Date startDate = null;
			Date endDate = null;

			while (it.hasNext()) {
				String x = it.next();
				String[] entryValues = x.split(Pattern.quote("|"));
				try {
					entryDate = formatter.parse(entryValues[0]);
					entryIp = entryValues[1];
					startDate = params.getStartDate();
					endDate = params.getEndDate();

					if (entryDate.after(params.getStartDate())) {
						retrieveLogRequest(startDate, endDate, ips, entryDate, entryIp);
					}

					if (entryDate.after(params.getEndDate())) {
						// no need to go any further
						break;
					}

				} catch (ParseException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
			LOGGER.debug("Number of IP's entries matching the date period within the log file=" + ips.size());

		} finally {
			if (null != stream) {
				stream.close();
			}
		}

		return ips;
	}

	/**
	 * Retrieves the log file line just read, according to the request parameters
	 * received.
	 * 
	 * 
	 * @param startDate
	 *            The start date parameter received.
	 * @param endDate
	 *            The end date parameter received.
	 * @param ips
	 *            Map<String, BlockedIpEntry> the Map with IP address and their
	 *            respective IP blocked details.
	 * 
	 * @param entryDate
	 *            The log's line entry date read.
	 * 
	 * @param entryIp
	 *            The log's line ip address read.
	 */
	private static void retrieveLogRequest(Date startDate, Date endDate, Map<String, BlockedIpEntry> ips,
			Date entryDate, String entryIp) {
		BlockedIpEntry entity = null;
		BlockedIpEvent event = null;
		entity = ips.get(entryIp);

		if (null == entity) {
			entity = new BlockedIpEntry();
			entity.setIp_address(entryIp);
			entity.setStartDate(startDate);
			entity.setEndDate(endDate);
		}

		event = new BlockedIpEvent();
		event.setEventDate(entryDate);
		event.setIp_address(entryIp);
		event.setLog_entry(entity);

		entity.getIp_events().add(event);
		int count = entity.getIp_events().size();
		entity.setRequests(count);

		ips.put(entryIp, entity);
	}

}
