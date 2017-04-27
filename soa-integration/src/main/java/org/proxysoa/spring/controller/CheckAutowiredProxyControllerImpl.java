package org.proxysoa.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * The simple controller is necessary to check how Proxy controllers autowiring works.
 * It is supposed to return class name of autowired another controller.
 * We autowire ProjectController and expect real instance or Proxy.
 * @author stanislav.lapitsky created 4/21/2017.
 */
@RestController
public class CheckAutowiredProxyControllerImpl implements CheckAutowiredProxyController {
    @Autowired
    private ProjectController projectController;

    @Override
    public String checkAutowired() {
        return projectController.toString();
    }
}
