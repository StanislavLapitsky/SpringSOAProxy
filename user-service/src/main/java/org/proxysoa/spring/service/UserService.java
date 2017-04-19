package org.proxysoa.spring.service;

import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.dto.UserDTO;

/**
 * Service Layer to work with users
 *
 * @author stanislav.lapitsky created 4/14/2017.
 */
public interface UserService {

    /**
     * Gets users for specified page
     *
     * @param pageRequest page info (offset, pageSize, sort)
     * @return list of users for the page + total
     */
    SimplePage<UserDTO> getUsers(SimplePageRequest pageRequest);
}
