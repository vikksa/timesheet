package com.stackhack.timesheet.services;

import com.stackhack.timesheet.dtos.TimeSheetUserDto;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TimeSheetUserService {

    TimeSheetUser createUser(TimeSheetUserDto timeSheetUserDto);

    TimeSheetUser getUser(UUID userId);

    Page<TimeSheetUser> listUser(PaginationRequest paginationRequest);

    TimeSheetUser updateUser(UUID id, TimeSheetUserDto userDto);
}
