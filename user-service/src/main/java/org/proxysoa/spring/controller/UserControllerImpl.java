package org.proxysoa.spring.controller;

import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.dto.UserDTO;
import org.proxysoa.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements common's UserController contract
 * @author stanislav.lapitsky created 4/13/2017.
 */
@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;

    @Override
    public SimplePage<UserDTO> getUsers(SimplePageRequest pageRequest) {
        return userService.getUsers(pageRequest);
    }
}
