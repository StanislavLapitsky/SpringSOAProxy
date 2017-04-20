package org.proxysoa.spring.config;

import org.proxysoa.spring.controller.ParametersConverterTestController;
import org.proxysoa.spring.controller.ProjectController;
import org.proxysoa.spring.controller.UserController;
import org.proxysoa.spring.service.ControllerURLResolver;
import org.proxysoa.spring.service.MapControllerUrlResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Simplest URL resolver for remote calls for each controller.
 * Could be replaced with .properties based approach
 *
 * @author stanislav.lapitsky created 4/18/2017.
 */
@Configuration
@PropertySource("classpath:soa-services-urls.properties")
public class ProjectServiceConfig {

}
