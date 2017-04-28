package org.proxysoa.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.proxysoa.spring.annotation.Proxyable;
import org.proxysoa.spring.dto.PojoDTO;
import org.proxysoa.spring.web.ApiConst;
import org.springframework.web.bind.annotation.*;

/**
 * The controller is created to handle multiple test cases - convert to JSON and get the info bak when
 * RestTemplate is called.
 * The controller is a contract for ParametersConverterTestControllerImpl (implemented in the Project Service app)
 *
 * @author stanislav.lapitsky created 4/18/2017.
 */
@Api(value = ApiConst.MAPPING_PARAMETERS_CONVERTER, description = "Project API")
@Proxyable
public interface ParametersConverterTestController {

    /**
     * We declare ApiImplicitParams and the same method parameters.
     * During calling the method all the args are converted to appropriate api parameters.
     *
     * @param id   long parameter
     * @param name name parameter
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/primitives", method = RequestMethod.GET)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "long", paramType = "query"),
    })
    String processPrimitivesWithAPIParams(@RequestParam("id") Long id, @RequestParam("name") String name);

    /**
     * We does not declare ApiImplicitParams but have some method parameters.
     * During calling the method we cannot convert properly the data. There is no way to organize
     * HOST:PORT/CONTEXT?param1=value1&param2=value2...
     * we just don't know how the get proper parameter names/
     * When the method is called remotely null is sent for each parameter.
     *
     * @param id   long parameter
     * @param name name parameter
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/primitivesNoParams", method = RequestMethod.GET)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    String processPrimitivesNoAPIParams(@RequestParam("id") Long id, @RequestParam("name") String name);

    /**
     * We declare ApiImplicitParams but the parameters are converted to POJO fields. We use the POJO fields' names
     * to pass the values
     *
     * @param dto POJO with field names corresponding the used request parameters
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/pojo", method = RequestMethod.GET)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "long", paramType = "query"),
    })
    String processPOJOWithAPIParams(@RequestBody PojoDTO dto);

    /**
     * We does not declare ApiImplicitParams but the parameters are POJO's fields. We use the POJO fields' names
     * to pass the values
     *
     * @param dto POJO with field names corresponding the used request parameters
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/pojoNoParams", method = RequestMethod.GET)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    String processPOJONoAPIParams(@RequestBody PojoDTO dto);

    /**
     * We declare ApiImplicitParams and the same method parameters.
     * During calling the method all the args are converted to appropriate api parameters.
     *
     * @param id   long parameter
     * @param name name parameter
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/post/primitives", method = RequestMethod.POST)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "long", paramType = "query"),
    })
    String processPrimitivesWithAPIParamsPost(@RequestParam("id") Long id, @RequestParam("name") String name);

    /**
     * We does not declare ApiImplicitParams but have some method parameters.
     * During calling the method we cannot convert properly the data. There is no way to organize
     * HOST:PORT/CONTEXT + BODY {param1=value1&param2=value2...}
     * we just don't know how the get proper parameter names/
     * When the method is called remotely null is sent for each parameter.
     *
     * @param id   long parameter
     * @param name name parameter
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/post/primitivesNoParams", method = RequestMethod.POST)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    String processPrimitivesNoAPIParamsPost(@RequestParam("id") Long id, @RequestParam("name") String name);

    /**
     * We declare ApiImplicitParams but the parameters are converted to POJO fields. We use the POJO fields' names
     * to pass the values
     *
     * @param dto POJO with field names corresponding the used request parameters
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/post/pojo", method = RequestMethod.POST)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "long", paramType = "query"),
    })
    String processPOJOWithAPIParamsPost(@RequestBody PojoDTO dto);

    /**
     * We does not declare ApiImplicitParams but the parameters are POJO's fields. We use the POJO fields' names
     * to pass the values
     *
     * @param dto POJO with field names corresponding the used request parameters
     * @return just sum of name + id
     */
    @RequestMapping(value = "/" + ApiConst.MAPPING_PARAMETERS_CONVERTER + "/post/pojoNoParams", method = RequestMethod.POST)
    @ApiOperation(value = "Primitives test", notes = "Primitives test")
    @ResponseBody
    String processPOJONoAPIParamsPost(@RequestBody PojoDTO dto);

}
