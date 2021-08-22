package com.stackhack.timesheet.dtos;

public class Time {

  private String description;
  private Integer hours;
  private String issueNumber;
  private String projectName;
  private String costHead;
  private String programName;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getHours() {
    return hours;
  }

  public void setHours(Integer hours) {
    this.hours = hours;
  }

  public String getIssueNumber() {
    return issueNumber;
  }

  public void setIssueNumber(String issueNumber) {
    this.issueNumber = issueNumber;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getCostHead() {
    return costHead;
  }

  public void setCostHead(String costHead) {
    this.costHead = costHead;
  }

  public String getProgramName() {
    return programName;
  }

  public void setProgramName(String programName) {
    this.programName = programName;
  }
}
