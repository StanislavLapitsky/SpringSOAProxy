package org.proxysoa.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
