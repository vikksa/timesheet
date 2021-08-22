package com.stackhack.timesheet.repo;

import com.stackhack.timesheet.models.TimeSheetUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<TimeSheetUser, UUID> {
    Optional<TimeSheetUser> findByUsernameIgnoreCase(String username);
}
