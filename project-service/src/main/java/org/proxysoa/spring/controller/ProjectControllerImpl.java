package org.proxysoa.spring.controller;

import org.proxysoa.spring.dto.ProjectDTO;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implements common's ProjectController contract
 * @author stanislav.lapitsky created 4/13/2017.
 */
@RestController
public class ProjectControllerImpl implements ProjectController {
    public static final int MAX_PROJECT_COUNT = 1000000;

    @Autowired
    private ProjectService projectService;

    @Override
    public SimplePage<ProjectDTO> getProjects(SimplePageRequest pageRequest) {
        return projectService.getProjects(pageRequest);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        SimplePageRequest pageRequest = new SimplePageRequest(0, MAX_PROJECT_COUNT, null);
        return projectService.getProjects(pageRequest).getContent();
    }

    @Override
    public List<ProjectDTO> getProjectsByUser(Long userId) {
        return projectService.getProjectsByUser(userId);
    }

    @Override
    public void createProject(@RequestBody ProjectDTO project) {
        projectService.createProject(project);
    }
}
