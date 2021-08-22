package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.ProjectSettings;
import com.stackhack.timesheet.models.values.Days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProjectSettingsDto extends AuditedDto {

    private UUID id;

    private Boolean billable;
    private List<Days> days;
    private Boolean isTimerOn;
    private Boolean timeManualEntry;
    private byte[] logo;

    public ProjectSettingsDto() {
    }

    public ProjectSettingsDto(ProjectSettings projectSettings) {
        super(projectSettings);
        this.id = projectSettings.getId();
        this.billable = projectSettings.getBillable();
        this.days = projectSettings.getDays();
        this.isTimerOn = projectSettings.getTimerOn();
        this.timeManualEntry = projectSettings.getTimeManualEntry();
        this.logo = projectSettings.getLogo();
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjectSettingsDto that = (ProjectSettingsDto) o;
        return Objects.equals(id, that.id) && Objects.equals(billable, that.billable) && Objects.equals(days, that.days) && Objects.equals(isTimerOn, that.isTimerOn) && Objects.equals(timeManualEntry, that.timeManualEntry) && Arrays.equals(logo, that.logo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), id, billable, days, isTimerOn, timeManualEntry);
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }
}
