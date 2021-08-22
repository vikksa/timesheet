package com.stackhack.timesheet.models;

import com.stackhack.timesheet.models.values.AllMemberPermission;
import com.stackhack.timesheet.models.values.Days;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Entity
public class ProjectSettings extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;

    private Boolean billable;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Days.class)
    private List<Days> days;
    private Boolean isTimerOn;
    private Boolean timeManualEntry;
    private byte[] logo;
    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public List<Days> getDays() {
        return days;
    }

    public void setDays(List<Days> days) {
        this.days = days;
    }

    public Boolean getTimerOn() {
        return isTimerOn;
    }

    public void setTimerOn(Boolean timerOn) {
        isTimerOn = timerOn;
    }

    public Boolean getTimeManualEntry() {
        return timeManualEntry;
    }

    public void setTimeManualEntry(Boolean timeManualEntry) {
        this.timeManualEntry = timeManualEntry;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
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
        ProjectSettings that = (ProjectSettings) o;
        return Objects.equals(id, that.id) && Objects.equals(billable, that.billable) && Objects.equals(days, that.days) && Objects.equals(isTimerOn, that.isTimerOn) && Objects.equals(timeManualEntry, that.timeManualEntry) && Arrays.equals(logo, that.logo) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), id, billable, days, isTimerOn, timeManualEntry, project);
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }
}
