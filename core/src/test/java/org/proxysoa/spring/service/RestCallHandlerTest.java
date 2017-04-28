package org.proxysoa.spring.service;

import org.junit.Assert;
import org.junit.Test;
import org.proxysoa.spring.controller.TestController;

/**
 * @author stanislav.lapitsky created 4/18/2017.
 */
public class RestCallHandlerTest {
    private RestCallHandler restCallHandler = new RestCallHandler(TestController.class, "", null);

    @Test
    public void testClassRequestMapping() {
        String res = restCallHandler.getClassRequestMapping(TestController.class);
        Assert.assertEquals("", res);
    }

}
