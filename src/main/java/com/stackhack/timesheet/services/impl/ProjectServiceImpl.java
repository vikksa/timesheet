package com.stackhack.timesheet.services.impl;

import com.stackhack.timesheet.dtos.ProjectDto;
import com.stackhack.timesheet.dtos.ProjectPermissionDto;
import com.stackhack.timesheet.dtos.ProjectSettingsDto;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.models.ProjectPermission;
import com.stackhack.timesheet.models.ProjectSettings;
import com.stackhack.timesheet.repo.ProjectPermissionRepository;
import com.stackhack.timesheet.repo.ProjectRepository;
import com.stackhack.timesheet.repo.ProjectSettingsRepository;
import com.stackhack.timesheet.services.ProjectService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectSettingsRepository projectSettingsRepository;
    @Autowired
    private ProjectPermissionRepository projectPermissionRepository;

    @Override
    public Project createProject(ProjectDto projectDto) {
        if (projectRepository.findByNameIgnoreCase(projectDto.getName()).isPresent()) {
            throw new TimeSheetException(HttpStatus.CONFLICT, "PROJECT_NAME_ALREADY_EXISTS");
        }
        Project project = new Project();
        project.setName(projectDto.getName());
        return projectRepository.save(project);
    }

    @Override
    public Project getProject(UUID projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new TimeSheetException(HttpStatus.NOT_FOUND, "PROJECT_NOT_FOUND"));
    }

    @Override
    public Page<Project> listProject(PaginationRequest paginationRequest) {
        return projectRepository.findAll(paginationRequest.toPageable(sortBy -> "updatedBy"));
    }

    @Override
    public Project updateProject(UUID id, ProjectDto projectDto) {
        Project project = getProject(id);
        if (!ObjectUtils.isEmpty(projectDto.getName())) {
            if (projectRepository.findByIdIsNotAndNameIgnoreCase(id, projectDto.getName()).isPresent()) {
                throw new TimeSheetException(HttpStatus.CONFLICT, "PROJECT_NAME_ALREADY_EXISTS");
            }
            project.setName(projectDto.getName());
        }
        if (projectDto.getArchived() != null) {
            project.setArchived(projectDto.getArchived());
        }
        return projectRepository.save(project);
    }

    @Override
    public ProjectSettings getProjectSettings(UUID projectId) {
        Project project = getProject(projectId);
        return projectSettingsRepository.findByProjectId(projectId).orElseGet(() -> {
            ProjectSettings projectSettings = new ProjectSettings();
            projectSettings.setProject(project);
            return projectSettingsRepository.save(projectSettings);
        });
    }

    @Override
    public ProjectSettings updateProjectSettings(UUID projectId, ProjectSettingsDto projectSettingsDto) {
        ProjectSettings projectSettings = getProjectSettings(projectId);
        if (projectSettingsDto.getBillable() != null) {
            projectSettings.setBillable(projectSettingsDto.getBillable());
        }
        if (projectSettingsDto.getDays() != null && !projectSettings.getDays().isEmpty()) {
            projectSettings.setDays(projectSettingsDto.getDays());
        }
        if (projectSettingsDto.getTimerOn() != null) {
            projectSettings.setTimerOn(projectSettingsDto.getTimerOn());
        }
        if (projectSettingsDto.getTimeManualEntry() != null) {
            projectSettings.setTimeManualEntry(projectSettingsDto.getTimeManualEntry());
        }
        projectSettings.setLogo(projectSettingsDto.getLogo());
        return projectSettingsRepository.save(projectSettings);
    }

    @Override
    public ProjectPermission getProjectPermission(UUID projectId) {
        Project project = getProject(projectId);
        return projectPermissionRepository.findByProjectId(projectId).orElseGet(() -> {
            ProjectPermission projectPermission = new ProjectPermission();
            projectPermission.setProject(project);
            return projectPermissionRepository.save(projectPermission);
        });
    }

    @Override
    public ProjectPermission updateProjectPermission(UUID projectId, ProjectPermissionDto projectPermissionDto) {
        ProjectPermission projectPermission = getProjectPermission(projectId);
        projectPermission.setAllMemberPermissions(projectPermissionDto.getAllMemberPermissions());
        projectPermission.setAllMemberVisitPermissions(projectPermissionDto.getAllMemberVisitPermissions());
        projectPermission.setProjectStatusVisibility(projectPermissionDto.getProjectStatusVisibility());
        projectPermission.setBillingInfoVisibility(projectPermissionDto.getBillingInfoVisibility());
        projectPermission.setTimeUpdation(projectPermissionDto.getTimeUpdation());
        projectPermission.setMemberAccessTeamDashBoard(projectPermissionDto.getMemberAccessTeamDashBoard());
        projectPermission.setMemberTimeVisibility(projectPermissionDto.getMemberTimeVisibility());
        return projectPermissionRepository.save(projectPermission);
    }
}
