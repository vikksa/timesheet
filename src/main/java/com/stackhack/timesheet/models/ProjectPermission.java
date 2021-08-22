package com.stackhack.timesheet.models;

import com.stackhack.timesheet.models.values.AllMemberPermission;
import com.stackhack.timesheet.models.values.BillingInfoVisibility;
import com.stackhack.timesheet.models.values.MemberTimeVisibility;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ProjectPermission extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private BillingInfoVisibility billingInfoVisibility;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = AllMemberPermission.class)
    private List<AllMemberPermission> allMemberPermissions;
    @Enumerated(EnumType.STRING)
    private BillingInfoVisibility projectStatusVisibility;
    @Enumerated(EnumType.STRING)
    private BillingInfoVisibility timeUpdation;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = AllMemberPermission.class)
    private List<AllMemberPermission> allMemberVisitPermissions;
    @Enumerated(EnumType.STRING)
    private MemberTimeVisibility memberTimeVisibility;
    private Boolean memberAccessTeamDashBoard;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BillingInfoVisibility getBillingInfoVisibility() {
        return billingInfoVisibility;
    }

    public void setBillingInfoVisibility(BillingInfoVisibility billingInfoVisibility) {
        this.billingInfoVisibility = billingInfoVisibility;
    }

    public List<AllMemberPermission> getAllMemberPermissions() {
        return allMemberPermissions;
    }

    public void setAllMemberPermissions(List<AllMemberPermission> allMemberPermissions) {
        this.allMemberPermissions = allMemberPermissions;
    }

    public BillingInfoVisibility getProjectStatusVisibility() {
        return projectStatusVisibility;
    }

    public void setProjectStatusVisibility(BillingInfoVisibility projectStatusVisibility) {
        this.projectStatusVisibility = projectStatusVisibility;
    }

    public BillingInfoVisibility getTimeUpdation() {
        return timeUpdation;
    }

    public void setTimeUpdation(BillingInfoVisibility timeUpdation) {
        this.timeUpdation = timeUpdation;
    }

    public List<AllMemberPermission> getAllMemberVisitPermissions() {
        return allMemberVisitPermissions;
    }

    public void setAllMemberVisitPermissions(List<AllMemberPermission> allMemberVisitPermissions) {
        this.allMemberVisitPermissions = allMemberVisitPermissions;
    }

    public MemberTimeVisibility getMemberTimeVisibility() {
        return memberTimeVisibility;
    }

    public void setMemberTimeVisibility(MemberTimeVisibility memberTimeVisibility) {
        this.memberTimeVisibility = memberTimeVisibility;
    }

    public Boolean getMemberAccessTeamDashBoard() {
        return memberAccessTeamDashBoard;
    }

    public void setMemberAccessTeamDashBoard(Boolean memberAccessTeamDashBoard) {
        this.memberAccessTeamDashBoard = memberAccessTeamDashBoard;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjectPermission that = (ProjectPermission) o;
        return Objects.equals(id, that.id) && billingInfoVisibility == that.billingInfoVisibility && Objects.equals(allMemberPermissions, that.allMemberPermissions) && projectStatusVisibility == that.projectStatusVisibility && timeUpdation == that.timeUpdation && Objects.equals(allMemberVisitPermissions, that.allMemberVisitPermissions) && memberTimeVisibility == that.memberTimeVisibility && Objects.equals(memberAccessTeamDashBoard, that.memberAccessTeamDashBoard) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, billingInfoVisibility, allMemberPermissions, projectStatusVisibility, timeUpdation, allMemberVisitPermissions, memberTimeVisibility, memberAccessTeamDashBoard, project);
    }
}
