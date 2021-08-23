package com.stackhack.timesheet.services;

import com.stackhack.timesheet.dtos.TimeLogsDto;
import com.stackhack.timesheet.models.TimeLogs;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TimeLogsService {
    TimeLogs addTime(TimeLogsDto timeLogsDto);
    TimeLogs getTimeLog(UUID id);
    TimeLogs updateTimeLog(UUID id, TimeLogsDto timeLogsDto);
    Page<TimeLogs> getTimeLogsPage(PaginationRequest paginationRequest);
}
