package org.proxysoa.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The simple controller is necessary to check how Proxy controllers autowiring works.
 * It is supposed to return class name of autowired another controller
 * @author stanislav.lapitsky created 4/21/2017.
 */
public interface CheckAutowiredProxyController {
    @RequestMapping(value = "/checkAutowired", method = RequestMethod.GET)
    @ResponseBody
    String checkAutowired();
}
