package org.proxysoa.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proxysoa.spring.dto.SimplePage;
import org.proxysoa.spring.dto.UserDTO;
import org.proxysoa.spring.service.DeserializeParameterizedTypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author stanislav.lapitsky created 4/14/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerImplTest {
    @LocalServerPort
    private int port;

    @Value("${server.contextPath}")
    private String contextPath;
    private String base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = "http://localhost:" + port;
    }

    @Test
    public void getUsersTest() throws Exception {
        ResponseEntity<SimplePage<UserDTO>> response = template.exchange(
                base + contextPath + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<SimplePage<UserDTO>>() {});

        assertThat(response.getBody().getContent().size(), equalTo(10));
    }

}
