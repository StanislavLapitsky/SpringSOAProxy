package org.proxysoa.spring.controller;

import org.proxysoa.spring.ProjectWebApplication;
import org.proxysoa.spring.UserWebApplication;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.UserDTO;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The class is used for testing.
 * We run 2 independent web services (for users and for projects) and call users service
 * which internally calls projects service
 *
 * @author stanislav.lapitsky created 4/14/2017.
 */
public class ServicesIntegrationMainApp {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
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
        List<UserDTO> users = response.getBody().getContent();
        System.out.println(users);
    }
}
