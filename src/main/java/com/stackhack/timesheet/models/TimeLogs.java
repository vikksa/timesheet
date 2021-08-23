package com.stackhack.timesheet.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class TimeLogs extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "VARBINARY(16)")
  private UUID id;

  private String description;
  private Date startTime;
  private Date endTime;
  private String issueNumber;
  @OneToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;
  @OneToOne
  @JoinColumn(name = "cost_head_id", nullable = false)
  private CostHead costHead;
  @JoinColumn(name = "timesheet_user_id", nullable = false)
  @OneToOne
  private TimeSheetUser timeSheetUser;
  private String programName;

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

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public CostHead getCostHead() {
    return costHead;
  }

  public void setCostHead(CostHead costHead) {
    this.costHead = costHead;
  }

  public String getProgramName() {
    return programName;
  }

  public void setProgramName(String programName) {
    this.programName = programName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    TimeLogs timeLogs = (TimeLogs) o;
    return Objects.equals(id, timeLogs.id) && Objects.equals(description, timeLogs.description) && Objects.equals(startTime, timeLogs.startTime) && Objects.equals(endTime, timeLogs.endTime) && Objects.equals(issueNumber, timeLogs.issueNumber) && Objects.equals(project, timeLogs.project) && Objects.equals(costHead, timeLogs.costHead) && Objects.equals(programName, timeLogs.programName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, description, startTime, endTime, issueNumber, project, costHead, programName);
  }

  public TimeSheetUser getTimeSheetUser() {
    return timeSheetUser;
  }

  public void setTimeSheetUser(TimeSheetUser timeSheetUser) {
    this.timeSheetUser = timeSheetUser;
  }
}
