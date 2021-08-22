package com.stackhack.timesheet.repo;

import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CostHeadRepository extends JpaRepository<CostHead, UUID> {

}
