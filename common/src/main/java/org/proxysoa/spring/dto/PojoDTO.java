package org.proxysoa.spring.dto;

/**
 * Just a simple test class to check passing values to controller on REST calls
 * Has just name and id to check passing differnet parameter types.
 *
 * @author stanislav.lapitsky created 4/18/2017.
 */
public class PojoDTO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
