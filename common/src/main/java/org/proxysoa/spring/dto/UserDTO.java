package org.proxysoa.spring.dto;

import java.util.List;
import java.util.function.Consumer;

/**
 * Represents user information
 *
 * @author stanislav.lapitsky created 4/13/2017.
 */
public class UserDTO {
    //user id
    private Long id;
    //user email
    private String email;
    //user name
    private String name;
    //list of projects the user owns
    private List<ProjectDTO> projects;

    /**
     * Default constructor
     */
    public UserDTO() {
    }

    /**
     * Constructor with consumer to simplify creation
     *
     * @param builder consumer
     */
    public UserDTO(Consumer<UserDTO> builder) {
        builder.accept(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
