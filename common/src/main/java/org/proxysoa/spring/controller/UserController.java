package org.proxysoa.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.dto.UserDTO;
import org.proxysoa.spring.web.ApiConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The controller is a contract for UserService (implemented in the User Service app).
 * It is used to be called locally or remotely. For remote call a Proxy is created for the Controller.
 * Swagger annotations are used to manage remote calls (via proxy).
 * @author stanislav.lapitsky created 4/13/2017.
 */
@Api(value = ApiConst.MAPPING_USERS, description = "Users API")
public interface UserController {

    /**
     * The method go get chunk of users (page). We pass page request which offset, pageSize and sort
     * and get selected users
     * @param pageRequest page request data (has offset, pageSize and sort)
     * @return page representation (sublist of existing users)
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_USERS, method = RequestMethod.GET)
    @ApiOperation(value = "Get paged Users List", notes = "Returns one page user items")
    @ResponseBody
    SimplePage<UserDTO> getUsers(SimplePageRequest pageRequest);

}
