package com.ricardopalvesjr.parser.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Blocked IP Log entry entity class.
 * 
 * @author ricardopalvesjr
 *
 */
@Entity
@Table(name = "IPs_BLOCKED", uniqueConstraints = { @UniqueConstraint(columnNames = "IP_ADDRESS") })
public class BlockedIpEntry implements Serializable {

	private static final long serialVersionUID = 4977972499611098806L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Column(name = "IP_ADDRESS")
	private String ip_address;

	@NotNull
	@Basic
	private Integer requests;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", nullable = false, columnDefinition = "DATETIME(3)")
	private Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", nullable = false, columnDefinition = "DATETIME(3)")
	private Date endDate;

	@NotNull
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "log_entry")
	private Set<BlockedIpEvent> ip_events = new HashSet<BlockedIpEvent>(0);

	@NotNull
	@Basic
	private String cause;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ip_address
	 */
	public String getIp_address() {
		return ip_address;
	}

	/**
	 * @param ip_address
	 *            the ip_address to set
	 */
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	/**
	 * @return the requests
	 */
	public Integer getRequests() {
		return requests;
	}

	/**
	 * @param requests
	 *            the requests to set
	 */
	public void setRequests(Integer requests) {
		this.requests = requests;
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

	/**
	 * @return the ip_events
	 */
	public Set<BlockedIpEvent> getIp_events() {
		return ip_events;
	}

	/**
	 * @param ip_events
	 *            the ip_events to set
	 */
	public void setIp_events(Set<BlockedIpEvent> ip_events) {
		this.ip_events = ip_events;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Requests=" + requests + " :Start date=" + startDate + " :End date=" + endDate + " :Cause=" + cause
				+ "]";
	}
}
