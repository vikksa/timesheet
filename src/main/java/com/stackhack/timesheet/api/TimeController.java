package com.stackhack.timesheet.api;

import com.stackhack.timesheet.dtos.TimeLogsDto;
import com.stackhack.timesheet.models.TimeLogs;
import com.stackhack.timesheet.services.TimeLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeLogsService timeLogsService;

    @PostMapping
    public TimeLogsDto addTimeLog(TimeLogsDto timeLogsDto) {
        TimeLogs timeLogs = timeLogsService.addTime(timeLogsDto);
        return new TimeLogsDto(timeLogs);
    }

    @GetMapping("/{logId}")
    public TimeLogsDto getTimeLog(@PathVariable UUID logId) {
        TimeLogs timeLogs = timeLogsService.getTimeLog(logId);
        return new TimeLogsDto(timeLogs);
    }
}
