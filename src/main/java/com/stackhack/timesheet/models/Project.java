package com.stackhack.timesheet.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Project extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;
    private Boolean archived = false;
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ProjectSettings projectSettings;
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ProjectPermission projectPermission;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<TimeLogs> timeLogs = Collections.emptyList();
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
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(archived, project.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, archived);
    }

    public ProjectSettings getProjectSettings() {
        return projectSettings;
    }

    public void setProjectSettings(ProjectSettings projectSettings) {
        this.projectSettings = projectSettings;
    }

    public ProjectPermission getProjectPermission() {
        return projectPermission;
    }

    public void setProjectPermission(ProjectPermission projectPermission) {
        this.projectPermission = projectPermission;
    }

    public List<TimeLogs> getTimeLogs() {
        return timeLogs;
    }

    public void setTimeLogs(List<TimeLogs> timeLogs) {
        this.timeLogs = timeLogs;
    }
}
