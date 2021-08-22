package com.stackhack.timesheet.api;

import com.stackhack.timesheet.dtos.Time;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {
  @GetMapping
  public Time getTime(){
    Time time = new Time();
    time.setDescription("This is a description");
    time.setCostHead("This is cost head");
    time.setHours(123456);
    time.setIssueNumber("This is a issue number");
    time.setProgramName("This is a program Name");
    time.setProjectName("This is a project name");

return time;
  }
}
