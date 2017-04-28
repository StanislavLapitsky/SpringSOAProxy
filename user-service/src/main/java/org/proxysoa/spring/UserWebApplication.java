package org.proxysoa.spring;

import org.proxysoa.spring.annotation.ProxyableScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Users web application
 *
 * @author stanislav.lapitsky created 4/14/2017.
 */
@ComponentScan(basePackages = "org.proxysoa.spring")
@SpringBootApplication
@ProxyableScan("org.proxysoa")
public class UserWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserWebApplication.class, args);
    }
}
