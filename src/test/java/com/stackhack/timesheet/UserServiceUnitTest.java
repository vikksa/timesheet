package com.stackhack.timesheet;

import com.stackhack.timesheet.dtos.TimeSheetUserDto;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.services.TimeSheetUserService;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceUnitTest {


    @Autowired
    private TimeSheetUserService timeSheetUserService;

    @Test
    @DisplayName("Create User")
    void createUser() {
        TimeSheetUserDto timeSheetUserDto = new TimeSheetUserDto();
        timeSheetUserDto.setEmail("test@sarvika.com");
        timeSheetUserDto.setUsername("test@sarvika.com");
        timeSheetUserDto.setPassword("test@sarvika.com!!");
        timeSheetUserDto.setFirstName("test");
        TimeSheetUser user = timeSheetUserService.createUser(timeSheetUserDto);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getUsername());
        Assertions.assertNotNull(user.getEmail());

    }

    @Test
    @DisplayName("Get User")
    void getUser() {
        TimeSheetUserDto timeSheetUserDto = new TimeSheetUserDto();
        timeSheetUserDto.setEmail("test@sarvika1.com");
        timeSheetUserDto.setUsername("test@sarvik1a.com");
        timeSheetUserDto.setPassword("test@sarvika.com!!");
        timeSheetUserDto.setFirstName("test");
        TimeSheetUser user = timeSheetUserService.createUser(timeSheetUserDto);

        TimeSheetUser user1 = timeSheetUserService.getUser(user.getId());
        Assertions.assertNotNull(user1);
        Assertions.assertNotNull(user1.getUsername());
        Assertions.assertNotNull(user1.getEmail());

    }


    @Test
    @DisplayName("List User")
    void listUser() {
        timeSheetUserService.listUser(new PaginationRequest());
    }

}
