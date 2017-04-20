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
 * @author stanislav.lapitsky created 4/20/2017.
 */
@Component
public class PropertiesControllerURLResolver implements ControllerURLResolver {
    @Autowired
    private Environment env;

    private Map<String, Object> allPropertiesMap;

    /**
     * Read all available properties and store them in a map
     */
    @PostConstruct
    private void init() {
        allPropertiesMap = getAllProperties((ConfigurableEnvironment) env);
    }

    /**
     * Gets all properties for environment iterating all available property sources
     * @param env environment
     * @return properties key/value map
     */
    private static Map<String, Object> getAllProperties(ConfigurableEnvironment env) {
        Map<String, Object> target = new HashMap<>();
        env.getPropertySources().forEach(ps -> addAll(target, getAllProperties(ps)));
        return target;
    }

    /**
     * Gets all properties for property sources
     * @param source property source
     * @return properties key/value map
     */
    private static Map<String, Object> getAllProperties(PropertySource<?> source) {
        Map<String, Object> result = new HashMap<>();

        if (source instanceof CompositePropertySource) {
            CompositePropertySource cps = (CompositePropertySource) source;
            cps.getPropertySources().forEach(ps -> addAll(result, getAllProperties(ps)));
            return result;
        }

        if (source instanceof EnumerablePropertySource<?>) {
            EnumerablePropertySource<?> ps = (EnumerablePropertySource<?>) source;
            Arrays.asList(ps.getPropertyNames()).forEach(key -> result.put(key, ps.getProperty(key)));
            return result;
        }

        return result;

    }

    private static void addAll(Map<String, Object> target, Map<String, Object> toAdd) {
        for (Map.Entry<String, Object> entry : toAdd.entrySet()) {
            if (target.containsKey(entry.getKey())) {
                continue;
            }

            target.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String getServiceURL(Class<?> controllerClass) {
        Object value = allPropertiesMap.get(controllerClass.getCanonicalName());
        return value!=null ? "" + value : null;
    }
}
