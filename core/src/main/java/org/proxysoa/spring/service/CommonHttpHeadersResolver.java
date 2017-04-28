package org.proxysoa.spring.service;

import org.springframework.util.MultiValueMap;

/**
 * A simple implemnetation which returns static headers map the same for all controllers
 *
 * @author stanislav.lapitsky created 4/28/2017.
 */
public class CommonHttpHeadersResolver implements HttpHeadersResolver {

    private MultiValueMap<String, String> commonMap;

    /**
     * Default constructor
     *
     * @param commonMap headers common map
     */
    public CommonHttpHeadersResolver(MultiValueMap<String, String> commonMap) {
        this.commonMap = commonMap;
    }

    @Override
    public MultiValueMap<String, String> getHeaders(Class<?> controllerClass) {
        return commonMap;
    }
}
