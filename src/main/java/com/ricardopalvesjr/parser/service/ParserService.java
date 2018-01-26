package com.ricardopalvesjr.parser.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ricardopalvesjr.parser.domain.ParamsDO;
import com.ricardopalvesjr.parser.enums.DurationEnum;
import com.ricardopalvesjr.parser.exception.ParserServiceException;
import com.ricardopalvesjr.parser.helper.LogEntryHelper;
import com.ricardopalvesjr.parser.persistence.BlockedIpEntry;
import com.ricardopalvesjr.parser.persistence.BlockedIpEntryRepository;
import com.ricardopalvesjr.parser.persistence.BlockedIpEventRepository;

/**
 * Parser service class.
 * 
 * @author ricardopalvesjr
 *
 */
@Service
public class ParserService implements ParserServiceInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParserService.class);

	@Autowired
	BlockedIpEntryRepository entry_repo;

	@Autowired
	BlockedIpEventRepository event_repo;

	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ricardopalvesjr.parser.service.ParserServiceInterface#parseLog(com.
	 * ricardopalvesjr.parser.domain.ParamsDO)
	 */
	@Override
	public void parseLog(ParamsDO params) throws ParserServiceException {
		try {
			Map<String, BlockedIpEntry> ips = LogEntryHelper.getLogLinesMap(params);
			ips.forEach((k, v) -> {
				int count = v.getIp_events().size();
				if (count >= params.getThreshold()) {
					v.setRequests(count);
					String cause = null;
					if (params.getThreshold() > DurationEnum.HOURLY.retrieveLimit()) {
						cause = messageSource.getMessage("info.exceeded.daily", new Object[] {}, null);
					} else {
						cause = messageSource.getMessage("info.exceeded.hourly", new Object[] {}, null);
					}
					v.setCause(cause);
					entry_repo.save(v);
					v.getIp_events().forEach(x -> event_repo.save(x));

					LOGGER.info(messageSource.getMessage("info.ipaddress.found", new Object[] { k, v }, null));
				}
			});

		} catch (DataIntegrityViolationException e) {
			LOGGER.error(messageSource.getMessage("error.file.processed.already",
					new Object[] { params.getAccesslog() }, null));

		} catch (Exception e) {
			throw new ParserServiceException(e);
		}
	}

}