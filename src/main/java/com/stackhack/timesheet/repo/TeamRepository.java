package com.stackhack.timesheet.repo;

import com.stackhack.timesheet.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {

    Optional<Team> findByNameIgnoreCase(String name);

    Optional<Team> findByIdIsNotAndNameIgnoreCase(UUID id, String name);

}
