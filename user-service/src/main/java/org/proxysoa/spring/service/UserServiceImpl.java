package org.proxysoa.spring.service;

import org.proxysoa.spring.controller.ProjectController;
import org.proxysoa.spring.dto.ProjectDTO;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.dto.UserDTO;
import org.proxysoa.spring.exception.SOAControllerInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stanislav.lapitsky created 4/14/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    private static List<UserDTO> dummyUsers = getDummyUsers();
    @Autowired
    private ControllerFactory controllerFactory;

    private static List<UserDTO> getDummyUsers() {
        List<UserDTO> users = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            final long id = (long) i;
            UserDTO user = new UserDTO(u -> {
                u.setId(id);
                u.setEmail("user1@someemail.com");
                u.setName("User " + id + " name");
            });

            users.add(user);
        }

        return users;
    }

    @Override
    public SimplePage<UserDTO> getUsers(SimplePageRequest pageRequest) {
        int startIndex = pageRequest.getOffset();
        int endIndex = pageRequest.getOffset() + pageRequest.getPageSize();
        if (startIndex >= dummyUsers.size()) {
            return new SimplePage<>(new ArrayList<>());
        }
        if (endIndex >= dummyUsers.size()) {
            endIndex = dummyUsers.size();
        }

        List<UserDTO> projectUsers = dummyUsers.subList(startIndex, endIndex);
        for (UserDTO user : projectUsers) {
            user.setProjects(getUserProjects(user));
        }

        return new SimplePage<>(projectUsers);
    }

    private List<ProjectDTO> getUserProjects(UserDTO u) {
        ProjectController projectController = controllerFactory.getController(ProjectController.class);

        try {
            return projectController.getProjectsByUser(u.getId());
        } catch (ResourceAccessException | SOAControllerInvocationException e) {
            return null;
        }
    }
}
