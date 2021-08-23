package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.Team;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TeamDto extends AuditedDto {


    private UUID id;
    private String name;
    private Boolean archived;
    private List<TimeSheetUserDto> members;
    private List<UUID> memberIds;

    public TeamDto() {
    }

    public TeamDto(Team team) {
        super(team);
        this.id = team.getId();
        this.name = team.getName();
        this.archived = team.getArchived();
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
        TeamDto project = (TeamDto) o;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(archived, project.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, archived);
    }

    public List<TimeSheetUserDto> getMembers() {
        return members;
    }

    public void setMembers(List<TimeSheetUserDto> members) {
        this.members = members;
    }

    public List<UUID> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<UUID> memberIds) {
        this.memberIds = memberIds;
    }
}
