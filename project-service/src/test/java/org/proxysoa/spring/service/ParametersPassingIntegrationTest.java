package org.proxysoa.spring.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proxysoa.spring.controller.ParametersConverterTestController;
import org.proxysoa.spring.dto.PojoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author stanislav.lapitsky created 4/14/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParametersPassingIntegrationTest {
    @LocalServerPort
    private int port;

    @Value("${server.contextPath}")
    private String contextPath;
    private String base;

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private ControllerFactory controllerFactory;

    @Before
    public void setUp() throws Exception {
        this.base = "http://localhost:" + port;
        this.controllerFactory.setEnforceProxyCreation(true);
        ControllerURLResolver urlResolver = new ControllerURLResolver() {
            @Override
            public String getServiceURL(Class<?> controllerClass) {
                return "http://localhost:" + port + "/ProjectService";
            }
        };
        ReflectionTestUtils.setField(this.controllerFactory, "controllerURLResolver", urlResolver, ControllerURLResolver.class);
    }

    @After
    public void shutDown() throws Exception {
        this.controllerFactory.setEnforceProxyCreation(false);
    }

    @Test
    public void controllerFactoryTestLocalControllerGet() throws Exception {
        ParametersConverterTestController tc = controllerFactory.getController(ParametersConverterTestController.class);
        Assert.assertNotNull(tc);

        String name = "name";
        Long id = 1L;
        String expectedResult = name + id;
        String res;
        PojoDTO dto = new PojoDTO();
        dto.setId(id);
        dto.setName(name);

        res = tc.processPrimitivesWithAPIParams(id, name);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);

        res = tc.processPrimitivesNoAPIParams(id, name);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);

        res = tc.processPOJOWithAPIParams(dto);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);

        res = tc.processPOJONoAPIParams(dto);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);
    }

    @Test
    public void controllerFactoryTestLocalControllerPost() throws Exception {
        ParametersConverterTestController tc = controllerFactory.getController(ParametersConverterTestController.class);
        Assert.assertNotNull(tc);

        String name = "name";
        Long id = 1L;
        String expectedResult = name + id;
        String res;
        PojoDTO dto = new PojoDTO();
        dto.setId(id);
        dto.setName(name);

        res = tc.processPrimitivesWithAPIParamsPost(id, name);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);

        res = tc.processPrimitivesNoAPIParamsPost(id, name);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);

        res = tc.processPOJOWithAPIParamsPost(dto);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);

        res = tc.processPOJONoAPIParamsPost(dto);
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedResult, res);
    }

}

