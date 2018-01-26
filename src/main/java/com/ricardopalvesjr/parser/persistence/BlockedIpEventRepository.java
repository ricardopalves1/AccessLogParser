/**
 * 
 */
package com.ricardopalvesjr.parser.persistence;

import org.springframework.data.repository.CrudRepository;

/**
 * /** Repository interface for IP blocked events.
 * 
 * @author ricardopalvesjr
 *
 */
public interface BlockedIpEventRepository extends CrudRepository<BlockedIpEvent, Long> {
}
