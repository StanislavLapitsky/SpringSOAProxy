package org.proxysoa.spring.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * A web application just to be used in tests
 *
 * @author stanislav.lapitsky created 4/14/2017.
 */

@ComponentScan(basePackages = "org.proxysoa.spring")
@SpringBootApplication
public class TestWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestWebApplication.class, args);
    }
}
