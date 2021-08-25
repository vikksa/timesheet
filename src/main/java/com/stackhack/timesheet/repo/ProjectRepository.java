package com.stackhack.timesheet.repo;

import com.stackhack.timesheet.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    Optional<Project> findByNameIgnoreCase(String name);

    Optional<Project> findByIdIsNotAndNameIgnoreCase(UUID id, String name);
}
