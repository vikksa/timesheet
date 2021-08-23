package com.stackhack.timesheet.services.impl;

import com.stackhack.timesheet.dtos.TimeLogsDto;
import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.models.TimeLogs;
import com.stackhack.timesheet.repo.TimeLogsRepository;
import com.stackhack.timesheet.services.CostHeadService;
import com.stackhack.timesheet.services.ProjectService;
import com.stackhack.timesheet.services.TimeLogsService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TimeLogsServiceImpl implements TimeLogsService {

    @Autowired
    private TimeLogsRepository timeLogsRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private CostHeadService costHeadService;

    @Override
    public TimeLogs addTime(TimeLogsDto timeLogsDto) {
        Project project = projectService.getProject(timeLogsDto.getProjectId());
        CostHead costHead = costHeadService.getCostHead(timeLogsDto.getCostHeadId());
        TimeLogs timeLogs = new TimeLogs();
        timeLogs.setStartTime(timeLogsDto.getStartTime());
        timeLogs.setEndTime(timeLogsDto.getEndTime());
        timeLogs.setDescription(timeLogsDto.getDescription());
        timeLogs.setIssueNumber(timeLogsDto.getIssueNumber());
        timeLogs.setProgramName(timeLogsDto.getProgramName());
        timeLogs.setProject(project);
        timeLogs.setCostHead(costHead);
        return timeLogsRepository.save(timeLogs);
    }

    @Override
    public TimeLogs getTimeLog(UUID id) {
        return timeLogsRepository.findById(id).orElseThrow(() -> new TimeSheetException(HttpStatus.NOT_FOUND, "TIME_LOG_NOT_FOUND"));
    }

    @Override
    public TimeLogs updateTimeLog(UUID id, TimeLogsDto timeLogsDto) {
        TimeLogs timeLog = getTimeLog(id);
        timeLog.setEndTime(timeLogsDto.getEndTime());
        timeLog.setStartTime(timeLogsDto.getStartTime());
        timeLog.setDescription(timeLogsDto.getDescription());
        return timeLogsRepository.save(timeLog);
    }

    @Override
    public Page<TimeLogs> getTimeLogsPage(PaginationRequest paginationRequest) {
        return timeLogsRepository.findAll(paginationRequest.toPageable(sortBy -> "updatedBy"));
    }
}
