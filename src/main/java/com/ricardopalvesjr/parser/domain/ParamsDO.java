package com.ricardopalvesjr.parser.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ricardopalvesjr.parser.validation.DurationConstraint;

/**
 * Parser Parameters POJO class.
 * 
 * @author ricardopalvesjr
 *
 */
public class ParamsDO implements Serializable {

	private static final long serialVersionUID = -3860036045145021386L;

	@NotNull
	private String accesslog;

	@NotNull
	private Date startDate;

	@DurationConstraint
	private String duration;

	@NotNull
	private Integer threshold;

	@NotNull
	private Date endDate;

	/**
	 * @return the accesslog
	 */
	public String getAccesslog() {
		return accesslog;
	}

	/**
	 * @param accesslog
	 *            the accesslog to set
	 */
	public void setAccesslog(String accesslog) {
		this.accesslog = accesslog;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold
	 *            the threshold to set
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParamsDO[accesslog=" + accesslog + " :startDate=" + startDate + " :duration=" + duration
				+ " :threshold=" + threshold + " :endDate=" + endDate + "]";
	}

}
