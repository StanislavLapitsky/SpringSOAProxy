package org.proxysoa.spring.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.proxysoa.spring.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @author stanislav.lapitsky created 4/18/2017.
 */
public class ControllerFactoryTest {
    private ControllerFactory controllerFactory = new ControllerFactory();

    @Before
    public void before() {
        ApplicationContext mockContext = Mockito.mock(ApplicationContext.class);
        ControllerURLResolver mockUrlResolver = Mockito.mock(ControllerURLResolver.class);
        Mockito.when(mockContext.getBeansOfType(UserController.class)).thenReturn(new HashMap<>());
        Mockito.when(mockUrlResolver.getServiceURL(UserController.class)).thenReturn("");
        try {
            ReflectionTestUtils.setField(controllerFactory, "applicationContext", mockContext, ApplicationContext.class);
            ReflectionTestUtils.setField(controllerFactory, "controllerURLResolver", mockUrlResolver, ControllerURLResolver.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getProxiedController() {
        UserController uc = controllerFactory.getController(UserController.class);
        Assert.assertNotNull(uc);
        Assert.assertTrue(uc instanceof Proxy);

        UserController uc2 = controllerFactory.getController(UserController.class);
        Assert.assertNotNull(uc2);
        Assert.assertTrue(uc == uc2);
    }
}
