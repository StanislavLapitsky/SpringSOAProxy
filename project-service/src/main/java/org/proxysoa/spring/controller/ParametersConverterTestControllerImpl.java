package org.proxysoa.spring.controller;

import org.proxysoa.spring.dto.PojoDTO;
import org.springframework.web.bind.annotation.RestController;

/**
 * A dummy implementation of ParametersConverterTestController
 * Used for tests passing/resolving REST call parameters
 * @author stanislav.lapitsky created 4/18/2017.
 */
@RestController
public class ParametersConverterTestControllerImpl implements ParametersConverterTestController {
    @Override
    public String processPrimitivesWithAPIParams(Long id, String name) {
        return name + id;
    }

    @Override
    public String processPrimitivesNoAPIParams(Long id, String name) {
        return name + id;
    }

    @Override
    public String processPOJOWithAPIParams(PojoDTO dto) {
        return dto != null ? dto.getName() + dto.getId() : "null dto";
    }

    @Override
    public String processPOJONoAPIParams(PojoDTO dto) {
        return dto != null ? dto.getName() + dto.getId() : "null dto";
    }

    @Override
    public String processPrimitivesWithAPIParamsPost(Long id, String name) {
        return name + id;
    }

    @Override
    public String processPrimitivesNoAPIParamsPost(Long id, String name) {
        return name + id;
    }

    @Override
    public String processPOJOWithAPIParamsPost(PojoDTO dto) {
        return dto != null ? dto.getName() + dto.getId() : "null dto";
    }

    @Override
    public String processPOJONoAPIParamsPost(PojoDTO dto) {
        return dto != null ? dto.getName() + dto.getId() : "null dto";
    }
}
