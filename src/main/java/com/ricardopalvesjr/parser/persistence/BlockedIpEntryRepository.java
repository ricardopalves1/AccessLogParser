package com.ricardopalvesjr.parser.persistence;

/**
 * Repository interface for IP blocked entries.
 * 
 * @author ricardopalvesjr
 *
 */
public interface BlockedIpEntryRepository
		extends org.springframework.data.repository.CrudRepository<BlockedIpEntry, Long> {
}