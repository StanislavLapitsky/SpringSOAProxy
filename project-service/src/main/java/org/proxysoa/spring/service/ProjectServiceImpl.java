package org.proxysoa.spring.service;

import org.proxysoa.spring.dto.ProjectDTO;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stanislav.lapitsky created 4/14/2017.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private static List<ProjectDTO> dummyProjects = getDummyProjects();

    private static List<ProjectDTO> getDummyProjects() {
        List<ProjectDTO> projects = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            final long id = (long) i;
            projects.add(new ProjectDTO(p -> {
                p.setId(id);
                p.setOwner(new UserDTO(u -> {
                    u.setId(id);
                    u.setEmail("user1@someemail.com");
                    u.setName("User 1 name");
                }));
                p.setName("Project " + id + " name");
            }));
        }

        return projects;
    }

    @Override
    public SimplePage<ProjectDTO> getProjects(SimplePageRequest pageRequest) {
        int startIndex = pageRequest.getOffset();
        int endIndex = pageRequest.getOffset() + pageRequest.getPageSize();
        if (startIndex >= dummyProjects.size()) {
            return new SimplePage<>(new ArrayList<>());
        }
        if (endIndex >= dummyProjects.size()) {
            endIndex = dummyProjects.size();
        }
        return new SimplePage<>(dummyProjects.subList(startIndex, endIndex));
    }

    @Override
    public List<ProjectDTO> getProjectsByUser(Long userId) {
        List<ProjectDTO> res = dummyProjects.stream()
                .filter(p -> p.getOwner() != null && p.getOwner().getId() != null && p.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
        return res;
    }

    @Override
    public void createProject(ProjectDTO project) {
        dummyProjects.add(project);
    }
}
