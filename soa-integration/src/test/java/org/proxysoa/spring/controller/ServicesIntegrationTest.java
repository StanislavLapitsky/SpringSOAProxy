package org.proxysoa.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.proxysoa.spring.ProjectWebApplication;
import org.proxysoa.spring.UserWebApplication;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.UserDTO;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * We run 2 independent web services (for users and for projects) and call users service
 * which internally calls projects service
 * @author stanislav.lapitsky created 4/14/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ServicesIntegrationTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void runTest() throws Exception {
        SpringApplicationBuilder uws = new SpringApplicationBuilder(UserWebApplication.class)
                .properties("server.port=8081",
                        "server.contextPath=/UserService",
                        "SOA.ControllerFactory.enforceProxyCreation=true");
        uws.run();
        SpringApplicationBuilder pws = new SpringApplicationBuilder(ProjectWebApplication.class)
                .properties("server.port=8082",
                        "server.contextPath=/ProjectService",
                        "SOA.ControllerFactory.enforceProxyCreation=true");
        pws.run();

        String url = "http://localhost:8081/UserService/users";
        ResponseEntity<SimplePage<UserDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<SimplePage<UserDTO>>() {
                });
        assertNotNull(response);
        List<UserDTO> users = response.getBody().getContent();
        assertEquals(10, users.size());
        assertNotNull(users.get(0).getProjects());
        assertEquals(1,users.get(0).getProjects().size());

    }
}
