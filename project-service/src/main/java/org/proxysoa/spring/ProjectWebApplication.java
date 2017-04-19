package org.proxysoa.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Projects web application
 * @author stanislav.lapitsky created 4/14/2017.
 */

@ComponentScan(basePackages = "org.proxysoa.spring")
@SpringBootApplication
public class ProjectWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectWebApplication.class, args);
    }
}
