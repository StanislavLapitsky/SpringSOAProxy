package org.proxysoa.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The resolver is based on .properties available for application.
 * The canonical class name of Controller interface is used as a key
 * The value must be remote URL
 *
 * @author stanislav.lapitsky created 4/20/2017.
 */
@Component
public class PropertiesControllerURLResolver implements ControllerURLResolver {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Override
    public String getServiceURL(Class<?> controllerClass) {
        Object value = environment.getProperty(controllerClass.getCanonicalName());
        return value != null ? "" + value : null;
    }
}
