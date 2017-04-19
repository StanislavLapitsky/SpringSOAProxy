package org.proxysoa.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.proxysoa.spring.dto.ProjectDTO;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.web.ApiConst;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller is a contract for ProjectService (implemented in the Project Service app).
 * It is used to be called locally or remotely. For remote call a Proxy is created for the Controller.
 * Swagger annotations are used to manage remote calls (via proxy).
 * @author stanislav.lapitsky created 4/13/2017.
 */
@Api(value = ApiConst.MAPPING_PROJECTS, description = "Project API")
public interface ProjectController {

    /**
     * The method go get chunk of projects (page). We pass page request which offset, pageSize and sort
     * and get selected projects
     * @param pageRequest page request data (has offset, pageSize and sort)
     * @return page representation (sublist of existing projects)
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PROJECTS, method = RequestMethod.GET)
    @ApiOperation(value = "Get page of Projects", notes = "Returns page of project items")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "Offset", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "Page size", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "Sort", required = false, dataType = "String", paramType = "query")
    })
    SimplePage<ProjectDTO> getProjects(@RequestBody SimplePageRequest pageRequest);

    /**
     * Gets all existing projects
     * @return list of projects
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PROJECTS + "/all", method = RequestMethod.GET)
    @ApiOperation(value = "Get All Projects List", notes = "Returns all project items")
    @ResponseBody
    List<ProjectDTO> getAllProjects();

    /**
     * Gets all projects of specified user
     * @param userId user id
     * @return user's project list
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PROJECTS + "/userProjects", method = RequestMethod.GET)
    @ApiOperation(value = "Get All Projects List", notes = "Returns all project items")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "User ID", required = true, dataType = "long", paramType = "query")
    })
    @ResponseBody
    List<ProjectDTO> getProjectsByUser(@RequestParam("userId") Long userId);

    /**
     * Creates a new project
     * @param project new project
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PROJECTS + "/create", method = RequestMethod.POST)
    @ApiOperation(value = "CreateProject", notes = "Creates a new project")
    @ResponseBody
    void createProject(@RequestBody ProjectDTO project);
}
