package com.stackhack.timesheet.services;

import com.stackhack.timesheet.dtos.ProjectDto;
import com.stackhack.timesheet.dtos.ProjectPermissionDto;
import com.stackhack.timesheet.dtos.ProjectSettingsDto;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.models.ProjectPermission;
import com.stackhack.timesheet.models.ProjectSettings;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProjectService {

    Project createProject(ProjectDto projectDto);

    Project getProject(UUID projectId);

    Page<Project> listProject(PaginationRequest paginationRequest);

    Project updateProject(UUID id, ProjectDto projectDto);

    ProjectSettings getProjectSettings(UUID projectId);

    ProjectSettings updateProjectSettings(UUID projectId, ProjectSettingsDto projectSettingsDto);

    ProjectPermission getProjectPermission(UUID projectPermission);

    ProjectPermission updateProjectPermission(UUID projectId, ProjectPermissionDto projectPermissionDto);
}
