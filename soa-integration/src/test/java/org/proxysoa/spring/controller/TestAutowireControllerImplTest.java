package org.proxysoa.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proxysoa.spring.ServicesIntegrationMainApp;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author stanislav.lapitsky created 4/14/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestWebApplication.class})
public class TestAutowireControllerImplTest {
    @LocalServerPort
    private int port;

    @Value("${server.contextPath:}")
    private String contextPath;
    private String base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = "http://localhost:" + port;
    }

    @Test
    public void testProjectControllerAutowired() throws Exception {
        ResponseEntity<String> response = template.exchange(
                base + contextPath + "/checkAutowired",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                });

        assertTrue(response.getBody().length()>0);
    }

}

