package org.proxysoa.spring.service;

import org.proxysoa.spring.dto.ProjectDTO;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;

import java.util.List;

/**
 * Service Layer to work with projects
 *
 * @author stanislav.lapitsky created 4/14/2017.
 */
public interface ProjectService {

    /**
     * Gets project for specified page
     *
     * @param pageRequest page info (offset, pageSize, sort)
     * @return list of projects for the page + total
     */
    SimplePage<ProjectDTO> getProjects(SimplePageRequest pageRequest);

    /**
     * gets list of projects for specified user
     *
     * @param userId user id
     * @return user's projects list
     */
    List<ProjectDTO> getProjectsByUser(Long userId);

    /**
     * Creates a new project instance
     *
     * @param project new project
     */
    void createProject(ProjectDTO project);
}
