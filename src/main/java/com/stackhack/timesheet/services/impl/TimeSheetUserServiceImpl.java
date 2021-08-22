package com.stackhack.timesheet.services.impl;

import com.stackhack.timesheet.dtos.TimeSheetUserDto;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.repo.UserRepository;
import com.stackhack.timesheet.services.TimeSheetUserService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TimeSheetUserServiceImpl implements TimeSheetUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public TimeSheetUser createUser(TimeSheetUserDto timeSheetUserDto) {
        TimeSheetUser timeSheetUser = new TimeSheetUser();
        timeSheetUser.setUsername(timeSheetUserDto.getUsername());
        timeSheetUser.setPassword(timeSheetUserDto.getPassword());
        timeSheetUser.setEmail(timeSheetUserDto.getEmail());
        timeSheetUser.setFirstName(timeSheetUserDto.getFirstName());
        timeSheetUser.setLastName(timeSheetUserDto.getLastName());
        timeSheetUser.setUserType(timeSheetUserDto.getUserType());
        return userRepository.save(timeSheetUser);
    }

    @Override
    public TimeSheetUser getUser(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new TimeSheetException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND"));
    }

    @Override
    public Page<TimeSheetUser> listUser(PaginationRequest paginationRequest) {
        return userRepository.findAll(paginationRequest.toPageable(sortBy -> "updatedAt"));
    }

    @Override
    public TimeSheetUser updateUser(UUID id, TimeSheetUserDto timeSheetUserDto) {
        TimeSheetUser timeSheetUser = getUser(id);
        timeSheetUser.setUsername(timeSheetUserDto.getUsername());
        timeSheetUser.setPassword(timeSheetUserDto.getPassword());
        timeSheetUser.setEmail(timeSheetUserDto.getEmail());
        timeSheetUser.setFirstName(timeSheetUserDto.getFirstName());
        timeSheetUser.setLastName(timeSheetUserDto.getLastName());
        timeSheetUser.setUserType(timeSheetUserDto.getUserType());
        return userRepository.save(timeSheetUser);
    }
}
