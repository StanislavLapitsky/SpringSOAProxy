package org.proxysoa.spring.dto;

import java.util.function.Consumer;

/**
 * Represents project information
 *
 * @author stanislav.lapitsky created 4/13/2017.
 */
public class ProjectDTO {
    //project id
    private Long id;
    //project name
    private String name;
    ///project owner
    private UserDTO owner;

    /**
     * Default constructor
     */
    public ProjectDTO() {
    }

    /**
     * Constructor with consumer to simplify creation
     *
     * @param builder consumer
     */
    public ProjectDTO(Consumer<ProjectDTO> builder) {
        builder.accept(this);
    }

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

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", owner=").append(owner);
        sb.append('}');
        return sb.toString();
    }
}
