package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.models.Project;

import java.util.Objects;
import java.util.UUID;

public class CostHeadDto extends AuditedDto {


    private UUID id;
    private String name;
    private Boolean archived;

    public CostHeadDto() {
    }

    public CostHeadDto(CostHead costHead) {
        super(costHead);
        this.id = costHead.getId();
        this.name = costHead.getName();
        this.archived = costHead.getArchived();
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
        CostHeadDto project = (CostHeadDto) o;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(archived, project.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, archived);
    }
}
