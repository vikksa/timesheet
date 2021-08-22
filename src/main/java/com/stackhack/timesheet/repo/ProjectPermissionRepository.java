package com.stackhack.timesheet.repo;

import com.stackhack.timesheet.models.ProjectPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectPermissionRepository extends JpaRepository<ProjectPermission, UUID> {
    Optional<ProjectPermission> findByProjectId(UUID projectId);

}
