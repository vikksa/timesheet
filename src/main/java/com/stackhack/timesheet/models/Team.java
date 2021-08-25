package com.stackhack.timesheet.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Team extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;
    private Boolean archived = false;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "team_member",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")}
    )
    private Set<TimeSheetUser> teamMembers = new HashSet<>();

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

    public Set<TimeSheetUser> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<TimeSheetUser> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(archived, team.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, archived);
    }
}
