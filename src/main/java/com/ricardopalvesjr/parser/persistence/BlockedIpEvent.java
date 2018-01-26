/**
 * 
 */
package com.ricardopalvesjr.parser.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Blocked IP Log event class.
 * 
 * @author ricardopalvesjr
 *
 */
@Entity
@Table(name = "IP_BLOCKED_EVENTS", uniqueConstraints = { @UniqueConstraint(columnNames = "EVENT_DATE") })
public class BlockedIpEvent implements Serializable {

	private static final long serialVersionUID = -6437159694745031230L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVENT_ID")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EVENT_DATE", unique = true, nullable = false, columnDefinition = "DATETIME(3)")
	private Date eventDate;

	@NotNull
	@Column(name = "IP_ADDRESS")
	private String ip_address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", nullable = false)
	private BlockedIpEntry log_entry;

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
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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
	 * @return the log_entry
	 */
	public BlockedIpEntry getLog_entry() {
		return log_entry;
	}

	/**
	 * @param log_entry
	 *            the log_entry to set
	 */
	public void setLog_entry(BlockedIpEntry log_entry) {
		this.log_entry = log_entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Event id=" + id + " :Event date=" + eventDate + "]";
	}

}
