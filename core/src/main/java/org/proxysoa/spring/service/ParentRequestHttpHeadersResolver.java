package org.proxysoa.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * The resolver tries to get request headers from existing call. If there is no headers it
 * fails back to usual map.
 *
 * @author stanislav.lapitsky created 4/28/2017.
 */
public class ParentRequestHttpHeadersResolver extends CommonHttpHeadersResolver {
    private static final Logger LOG = LoggerFactory.getLogger(ParentRequestHttpHeadersResolver.class);

    /**
     * Default constructor
     *
     * @param commonMap headers common map
     */
    public ParentRequestHttpHeadersResolver(MultiValueMap<String, String> commonMap) {
        super(commonMap);
    }

    @Override
    public MultiValueMap<String, String> getHeaders(Class<?> controllerClass) {
        MultiValueMap<String, String> requestMap = getCurrentHttpRequest();
        if (requestMap != null) {
            return requestMap;
        }
        return super.getHeaders(controllerClass);
    }

    @SuppressWarnings("unchecked")
    private static MultiValueMap<String, String> getCurrentHttpRequest() {
        MultiValueMap<String, String> res = new LinkedMultiValueMap<>();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            List<String> names = Collections.list(request.getHeaderNames());
            for (String headerName: names) {
                res.add(headerName, request.getHeader(headerName));
            }
            return res;
        }
        LOG.debug("Not called in the context of an HTTP request");
        return null;
    }
}
