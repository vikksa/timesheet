package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.models.TimeLogs;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


public class TimeLogsDto extends AuditedDto {

    private UUID id;
    private String description;
    private Date startTime;
    private Date endTime;
    private String issueNumber;
    private ProjectDto project;
    private UUID projectId;
    private UUID costHeadId;
    private CostHeadDto costHead;
    private String programName;

    public TimeLogsDto() {
    }

    public TimeLogsDto(TimeLogs timeLogs) {
        super(timeLogs);
        this.id = timeLogs.getId();
        this.description = timeLogs.getDescription();
        this.startTime = timeLogs.getStartTime();
        this.endTime = timeLogs.getEndTime();
        this.issueNumber = timeLogs.getIssueNumber();
        this.project = new ProjectDto(timeLogs.getProject());
        this.costHead = new CostHeadDto(timeLogs.getCostHead());
        this.programName = timeLogs.getProgramName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeLogsDto that = (TimeLogsDto) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(issueNumber, that.issueNumber) && Objects.equals(project, that.project) && Objects.equals(costHead, that.costHead) && Objects.equals(programName, that.programName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, description, startTime, endTime, issueNumber, project, costHead, programName);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public CostHeadDto getCostHead() {
        return costHead;
    }

    public void setCostHead(CostHeadDto costHead) {
        this.costHead = costHead;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public UUID getCostHeadId() {
        return costHeadId;
    }

    public void setCostHeadId(UUID costHeadId) {
        this.costHeadId = costHeadId;
    }
}
