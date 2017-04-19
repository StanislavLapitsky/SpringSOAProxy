package org.proxysoa.spring.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proxysoa.spring.config.UserTestConfig;
import org.proxysoa.spring.controller.ProjectController;
import org.proxysoa.spring.controller.UserController;
import org.proxysoa.spring.dto.ProjectDTO;
import org.proxysoa.spring.dto.SimplePageRequest;
import org.proxysoa.spring.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.lang.reflect.Proxy;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author stanislav.lapitsky created 4/14/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerFactoryIntegrationTest {
    @LocalServerPort
    private int port;

    @Value("${server.contextPath}")
    private String contextPath;
    private String base;

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private ControllerFactory controllerFactory;

    @Before
    public void setUp() throws Exception {
        this.base = "http://localhost:" + port;
    }

    @Test
    public void controllerFactoryTestLocalController() throws Exception {
        UserController uc = controllerFactory.getController(UserController.class);
        Assert.assertNotNull(uc);
        Assert.assertFalse(uc instanceof Proxy);
        List<UserDTO> resUsers = uc.getUsers(new SimplePageRequest()).getContent();
        Assert.assertNotNull(resUsers);
        Assert.assertEquals(10, resUsers.size());
    }

    @Test
    @Ignore
    public void controllerFactoryTestRemoteController() throws Exception {
        ProjectController pc = controllerFactory.getController(ProjectController.class);
        Assert.assertNotNull(pc);
        Assert.assertTrue(pc instanceof Proxy);
        List<ProjectDTO> resProjects;

        pc.createProject(new ProjectDTO(p -> {
            p.setId(22L);
            p.setName("A new project for 22L");
            p.setOwner(new UserDTO(u -> {
                u.setId(1L);
                u.setName("Name");
            }));
        }));
        Assert.assertTrue(true);

        resProjects = pc.getProjectsByUser(1L);
        Assert.assertNotNull(resProjects);
        Assert.assertEquals(2, resProjects.size());

        resProjects = pc.getProjects(null).getContent();
        Assert.assertNotNull(resProjects);
        Assert.assertEquals(10, resProjects.size());

    }

}

