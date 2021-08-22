package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.ProjectPermission;
import com.stackhack.timesheet.models.values.AllMemberPermission;
import com.stackhack.timesheet.models.values.BillingInfoVisibility;
import com.stackhack.timesheet.models.values.MemberTimeVisibility;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProjectPermissionDto extends AuditedDto {

    private UUID id;
    private BillingInfoVisibility billingInfoVisibility;
    private List<AllMemberPermission> allMemberPermissions;
    private BillingInfoVisibility projectStatusVisibility;
    private BillingInfoVisibility timeUpdation;
    private List<AllMemberPermission> allMemberVisitPermissions;
    private MemberTimeVisibility memberTimeVisibility;
    private Boolean memberAccessTeamDashBoard;

    public ProjectPermissionDto() {
    }

    public ProjectPermissionDto(ProjectPermission projectPermission) {
        super(projectPermission);
        this.id = projectPermission.getId();
        this.billingInfoVisibility = projectPermission.getBillingInfoVisibility();
        this.allMemberPermissions = projectPermission.getAllMemberPermissions();
        this.projectStatusVisibility = projectPermission.getProjectStatusVisibility();
        this.timeUpdation = projectPermission.getTimeUpdation();
        this.allMemberVisitPermissions = projectPermission.getAllMemberVisitPermissions();
        this.memberTimeVisibility = projectPermission.getMemberTimeVisibility();
        this.memberAccessTeamDashBoard = projectPermission.getMemberAccessTeamDashBoard();
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjectPermissionDto that = (ProjectPermissionDto) o;
        return Objects.equals(id, that.id) && billingInfoVisibility == that.billingInfoVisibility && Objects.equals(allMemberPermissions, that.allMemberPermissions) && projectStatusVisibility == that.projectStatusVisibility && timeUpdation == that.timeUpdation && Objects.equals(allMemberVisitPermissions, that.allMemberVisitPermissions) && memberTimeVisibility == that.memberTimeVisibility && Objects.equals(memberAccessTeamDashBoard, that.memberAccessTeamDashBoard) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, billingInfoVisibility, allMemberPermissions, projectStatusVisibility, timeUpdation, allMemberVisitPermissions, memberTimeVisibility, memberAccessTeamDashBoard);
    }
}
