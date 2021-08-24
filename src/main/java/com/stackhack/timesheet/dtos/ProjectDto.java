package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.Project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

public class ProjectDto extends AuditedDto {

    private UUID id;
    @NotBlank
    @Size(max = 50)
    private String name;
    private Boolean archived;

    public ProjectDto() {
    }

    public ProjectDto(Project project) {
        super(project);
        this.id = project.getId();
        this.name = project.getName();
        this.archived = project.getArchived();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjectDto project = (ProjectDto) o;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(archived, project.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, archived);
    }
}
