package com.stackhack.timesheet.api;

import com.stackhack.timesheet.dtos.TimeSheetUserDto;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.services.TimeSheetUserService;
import com.stackhack.timesheet.utils.PaginatedResponse;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TimeSheetUserService timeSheetUserService;

    @PostMapping
    public TimeSheetUserDto createUser(@RequestBody TimeSheetUserDto userDto) {
        TimeSheetUser user = timeSheetUserService.createUser(userDto);
        return new TimeSheetUserDto(user);
    }


    @GetMapping("/{userId}")
    public TimeSheetUserDto getUser(@PathVariable(name = "userId") UUID userId) {
        TimeSheetUser user = timeSheetUserService.getUser(userId);
        return new TimeSheetUserDto(user);
    }

    @PatchMapping("/{userId}")
    public TimeSheetUserDto updateUser(@PathVariable(name = "userId") UUID userId, @RequestBody TimeSheetUserDto userDto) {
        TimeSheetUser user = timeSheetUserService.updateUser(userId, userDto);
        return new TimeSheetUserDto(user);
    }

    @GetMapping
    public PaginatedResponse<TimeSheetUserDto> getUserPage(PaginationRequest pageRequest) {
        return PaginatedResponse.toPage(timeSheetUserService.listUser(pageRequest).map(TimeSheetUserDto::new));
    }
}
