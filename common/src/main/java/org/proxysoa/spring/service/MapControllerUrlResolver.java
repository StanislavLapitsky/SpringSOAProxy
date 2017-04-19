package org.proxysoa.spring.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents simplest URL resolver - Map based.
 *
 * @author stanislav.lapitsky created 4/18/2017.
 */
public class MapControllerUrlResolver implements ControllerURLResolver {
    private Map<String, String> urlsMap = new HashMap<>();

    /**
     * Default constructor
     */
    public MapControllerUrlResolver() {
    }

    /**
     * Builds a new instance with predefined map
     *
     * @param urlsMap map for the Class->URLs
     */
    public MapControllerUrlResolver(Map<Class<?>, String> urlsMap) {
        for (Map.Entry<Class<?>, String> entry : urlsMap.entrySet()) {
            this.urlsMap.put(entry.getKey().getCanonicalName(), entry.getValue());
        }
    }

    @Override
    public String getServiceURL(Class<?> controllerClass) {
        return urlsMap.get(controllerClass.getCanonicalName());
    }
}
