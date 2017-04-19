package org.proxysoa.spring.config;

import org.proxysoa.spring.controller.ProjectController;
import org.proxysoa.spring.controller.UserController;
import org.proxysoa.spring.service.ControllerURLResolver;
import org.proxysoa.spring.service.MapControllerUrlResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Simplest URL resolver for remote calls for each controller.
 * Could be replaced with .properties based approach
 * @author stanislav.lapitsky created 4/18/2017.
 */
@Configuration
public class UserServiceConfig {

    @Bean
    public ControllerURLResolver controllerURLResolver() {
        Map<Class<?>, String> urlsMap = new HashMap<>();
        urlsMap.put(UserController.class, "http://localhost:8081/UserService/");
        urlsMap.put(ProjectController.class, "http://localhost:8082/ProjectService/");
        return new MapControllerUrlResolver(urlsMap);
    }
}
