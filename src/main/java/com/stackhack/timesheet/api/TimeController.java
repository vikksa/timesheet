package com.stackhack.timesheet.api;

import com.stackhack.timesheet.dtos.TimeLogsDto;
import com.stackhack.timesheet.models.TimeLogs;
import com.stackhack.timesheet.services.TimeLogsService;
import com.stackhack.timesheet.utils.PaginatedResponse;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeLogsService timeLogsService;

    @PostMapping
    public TimeLogsDto addTimeLog(@RequestBody TimeLogsDto timeLogsDto) {
        TimeLogs timeLogs = timeLogsService.addTime(timeLogsDto);
        return new TimeLogsDto(timeLogs);
    }

    @GetMapping("/{logId}")
    public TimeLogsDto getTimeLog(@PathVariable UUID logId) {
        TimeLogs timeLogs = timeLogsService.getTimeLog(logId);
        return new TimeLogsDto(timeLogs);
    }

    @GetMapping
    public PaginatedResponse<TimeLogsDto> getTimeLogPage(@RequestParam(value = "from", required = false) Long from,
                                                         @RequestParam(value = "to", required = false) Long to,
                                                         @RequestParam(value = "project", required = false) UUID project,
                                                         @RequestParam(value = "user", required = false) UUID user,
                                                         @RequestParam(value = "costHead", required = false) UUID costHead,
                                                         PaginationRequest paginationRequest) {
        return PaginatedResponse.toPage(timeLogsService.getTimeLogsPage(from, to
                , project, user, costHead, paginationRequest).map(TimeLogsDto::new));
    }

    @PatchMapping("/{logId}")
    public TimeLogsDto updateTimeLog(@PathVariable UUID logId, @RequestBody TimeLogsDto timeLogsDto) {
        TimeLogs timeLogs = timeLogsService.updateTimeLog(logId, timeLogsDto);
        return new TimeLogsDto(timeLogs);
    }
}
