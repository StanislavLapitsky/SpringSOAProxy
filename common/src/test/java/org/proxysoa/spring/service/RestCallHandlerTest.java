package org.proxysoa.spring.service;

import org.junit.Assert;
import org.junit.Test;
import org.proxysoa.spring.controller.ProjectController;

/**
 * @author stanislav.lapitsky created 4/18/2017.
 */
public class RestCallHandlerTest {
    private RestCallHandler restCallHandler = new RestCallHandler(ProjectController.class, "");

    @Test
    public void testClassRequestMapping() {
        String res = restCallHandler.getClassRequestMapping(ProjectController.class);
        Assert.assertEquals("", res);
    }

}
