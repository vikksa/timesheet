package com.stackhack.timesheet.repo;

import com.stackhack.timesheet.models.TimeLogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimeLogsRepository extends JpaRepository<TimeLogs, UUID> {

}
