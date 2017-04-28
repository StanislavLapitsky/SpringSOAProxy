package org.proxysoa.spring.service;

import org.springframework.util.MultiValueMap;

/**
 * Interface to be used to get necessary http headers for specified controller class.
 *
 * @author stanislav.lapitsky created 4/28/2017.
 */
public interface HttpHeadersResolver {
    /**
     *
     * @param controllerClass controller class (interface)
     * @return headers to be used on remote call of the controller
     */
    MultiValueMap<String, String> getHeaders(Class<?> controllerClass);
}
