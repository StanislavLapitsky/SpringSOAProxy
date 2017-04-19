package org.proxysoa.spring.service;

/**
 * Provides a way to get URL for remote call of a controller
 * @author stanislav.lapitsky created 4/18/2017.
 */
public interface ControllerURLResolver {

    /**
     * Gets remote service URL for the controller
     * @param controllerClass controller
     * @return remote URL
     */
    String getServiceURL(Class<?> controllerClass);
}
