/**
 * 
 */
package com.ricardopalvesjr.parser.enums;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Enum for durarion command line argument.
 * 
 * @author ricardopalvesjr
 *
 */
public enum DurationEnum {
	HOURLY("hourly"), DAILY("daily");

	private String duration;

	/**
	 * Constructor method.
	 * 
	 * @param duration
	 */
	DurationEnum(String duration) {
		this.duration = duration;
	}

	/**
	 * Retrieves the threshold limit.
	 * 
	 * @return it the value itself
	 */
	public int retrieveLimit() {
		switch (this) {
		case HOURLY:
			return 200;
		case DAILY:
			return 500;
		default:
			throw new AssertionError(this);
		}
	}

	/**
	 * Calculates the end date.
	 * 
	 * @param startDate
	 *            Date the start date.
	 * @return Date the end date.
	 */
	public Date calculateEndDate(Date startDate) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = startDate.toInstant().atZone(defaultZoneId).toLocalDateTime();

		switch (this) {
		case HOURLY:
			localDateTime = localDateTime.plusHours(1);
			break;
		case DAILY:
			localDateTime = localDateTime.plusDays(1);
			break;
		}

		return Date.from(localDateTime.atZone(defaultZoneId).toInstant());
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

}
