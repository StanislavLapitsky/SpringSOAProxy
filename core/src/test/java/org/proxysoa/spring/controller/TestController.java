package org.proxysoa.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author stanislav.lapitsky created 4/20/2017.
 */
public interface TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    String test();
}
