package com.stackhack.timesheet.repo;

import com.stackhack.timesheet.models.CostHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CostHeadRepository extends JpaRepository<CostHead, UUID> {
    Optional<CostHead> findByNameIgnoreCase(String name);

    Optional<CostHead> findByIdIsNotAndNameIgnoreCase(UUID id, String name);
}
