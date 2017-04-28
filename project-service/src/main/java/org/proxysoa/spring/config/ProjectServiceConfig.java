package org.proxysoa.spring.config;

import org.proxysoa.spring.service.CommonHttpHeadersResolver;
import org.proxysoa.spring.service.HttpHeadersResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Simplest URL resolver for remote calls for each controller.
 * Could be replaced with .properties based approach
 *
 * @author stanislav.lapitsky created 4/18/2017.
 */
@Configuration
@PropertySource("classpath:soa-services-urls.properties")
public class ProjectServiceConfig {
    @Bean
    public HttpHeadersResolver httpHeadersResolver() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Accept","application/json");
        CommonHttpHeadersResolver resolver = new CommonHttpHeadersResolver(map);

        return resolver;
    }
}
