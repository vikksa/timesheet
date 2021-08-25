package com.stackhack.timesheet;

import com.stackhack.timesheet.dtos.CostHeadDto;
import com.stackhack.timesheet.dtos.ProjectDto;
import com.stackhack.timesheet.dtos.TimeLogsDto;
import com.stackhack.timesheet.dtos.TimeSheetUserDto;
import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.models.TimeLogs;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.services.CostHeadService;
import com.stackhack.timesheet.services.ProjectService;
import com.stackhack.timesheet.services.TimeLogsService;
import com.stackhack.timesheet.services.TimeSheetUserService;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@SpringBootTest
@ActiveProfiles("test")
class TimeLogServiceUnitTest {

    @Autowired
    private CostHeadService costHeadService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TimeLogsService timeLogsService;
    @Autowired
    private TimeSheetUserService timeSheetUserService;

    @Test
    @DisplayName("Create Time Log")
    void createTimeLog() {
        CostHeadDto costHeadDto = new CostHeadDto();
        costHeadDto.setName("Test Time CostHead");
        CostHead costHead = costHeadService.createCostHead(costHeadDto);

        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test time Project");
        Project project = projectService.createProject(projectDto);

        TimeSheetUserDto timeSheetUserDto = new TimeSheetUserDto();
        timeSheetUserDto.setEmail("test1@sarvika.com");
        timeSheetUserDto.setUsername("test1@sarvika.com");
        timeSheetUserDto.setPassword("test1@sarvika.com!!");
        timeSheetUserDto.setFirstName("test");
        TimeSheetUser user = timeSheetUserService.createUser(timeSheetUserDto);


        TimeLogsDto timeLogsDto = new TimeLogsDto();
        timeLogsDto.setProjectId(project.getId());
        timeLogsDto.setCostHeadId(costHead.getId());
        timeLogsDto.setTimeSheetUserId(user.getId());
        timeLogsDto.setDescription("Task Desc");
        timeLogsDto.setIssueNumber("123");
        timeLogsDto.setStartTime(new Date().getTime());
        timeLogsDto.setEndTime(new Date().getTime());
        TimeLogs timeLogs = timeLogsService.addTime(timeLogsDto);

        Assertions.assertNotNull(timeLogs);
        Assertions.assertNotNull(timeLogs.getDescription());
        Assertions.assertNotNull(timeLogs.getIssueNumber());
    }

    @Test
    @DisplayName("Get Time Log")
    void getTimeLog() {
        CostHeadDto costHeadDto = new CostHeadDto();
        costHeadDto.setName("Test Time2 CostHead");
        CostHead costHead = costHeadService.createCostHead(costHeadDto);

        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test time2 Project");
        Project project = projectService.createProject(projectDto);

        TimeSheetUserDto timeSheetUserDto = new TimeSheetUserDto();
        timeSheetUserDto.setEmail("test21@sarvika.com");
        timeSheetUserDto.setUsername("test21@sarvika.com");
        timeSheetUserDto.setPassword("test1@sarvika.com!!");
        timeSheetUserDto.setFirstName("test");
        TimeSheetUser user = timeSheetUserService.createUser(timeSheetUserDto);


        TimeLogsDto timeLogsDto = new TimeLogsDto();
        timeLogsDto.setProjectId(project.getId());
        timeLogsDto.setCostHeadId(costHead.getId());
        timeLogsDto.setTimeSheetUserId(user.getId());
        timeLogsDto.setDescription("Task Desc");
        timeLogsDto.setIssueNumber("123");
        timeLogsDto.setStartTime(new Date().getTime());
        timeLogsDto.setEndTime(new Date().getTime());
        TimeLogs timeLogs1 = timeLogsService.addTime(timeLogsDto);

        TimeLogs timeLogs = timeLogsService.getTimeLog(timeLogs1.getId());

        Assertions.assertNotNull(timeLogs);
        Assertions.assertNotNull(timeLogs.getDescription());
        Assertions.assertNotNull(timeLogs.getIssueNumber());
    }

}
