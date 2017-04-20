package org.proxysoa.spring.service;

import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Type;

/**
 * The purpose of this class is to enable capturing and passing a generic
 * {@link Type}. Used to parse properly RestTemplate calls and allows to deserialize JSOn to proper classes.
 *
 * @author stanislav.lapitsky created 4/17/2017.
 */
public class DeserializeParameterizedTypeReference extends ParameterizedTypeReference<Object> {
    private final Type type;

    /**
     * Default constructor
     *
     * @param type type of reference
     */
    public DeserializeParameterizedTypeReference(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

}
