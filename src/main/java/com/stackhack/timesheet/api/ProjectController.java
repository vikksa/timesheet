package com.stackhack.timesheet.api;

import com.stackhack.timesheet.dtos.ProjectDto;
import com.stackhack.timesheet.dtos.ProjectPermissionDto;
import com.stackhack.timesheet.dtos.ProjectSettingsDto;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.models.ProjectPermission;
import com.stackhack.timesheet.models.ProjectSettings;
import com.stackhack.timesheet.services.ProjectService;
import com.stackhack.timesheet.utils.PaginatedResponse;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ProjectDto createProject(@RequestBody @Validated ProjectDto projectDto) {
        Project project = projectService.createProject(projectDto);
        return new ProjectDto(project);
    }


    @GetMapping("/{projectId}")
    public ProjectDto getProject(@PathVariable(name = "projectId") UUID projectId) {
        Project project = projectService.getProject(projectId);
        return new ProjectDto(project);
    }

    @PatchMapping("/{projectId}")
    public ProjectDto updateProject(@PathVariable(name = "projectId") UUID projectId, @RequestBody ProjectDto projectDto) {
        Project project = projectService.updateProject(projectId, projectDto);
        return new ProjectDto(project);
    }

    @GetMapping
    public PaginatedResponse<ProjectDto> getProjectPage(PaginationRequest pageRequest) {
        return PaginatedResponse.toPage(projectService.listProject(pageRequest).map(ProjectDto::new));

    }

    @GetMapping("/{projectId}/settings")
    public ProjectSettingsDto getProjectSetting(@PathVariable(name = "projectId") UUID projectId) {
        ProjectSettings projectSettings = projectService.getProjectSettings(projectId);
        return new ProjectSettingsDto(projectSettings);
    }


    @PatchMapping("/{projectId}/settings")
    public ProjectSettingsDto updateProjectSettings(@PathVariable(name = "projectId") UUID projectId, @RequestBody ProjectSettingsDto ProjectSettingsDto) {
        ProjectSettings projectSettings = projectService.updateProjectSettings(projectId, ProjectSettingsDto);
        return new ProjectSettingsDto(projectSettings);
    }

    @GetMapping("/{projectId}/permission")
    public ProjectPermissionDto getProjectPermission(@PathVariable(name = "projectId") UUID projectId) {
        ProjectPermission projectPermission = projectService.getProjectPermission(projectId);
        return new ProjectPermissionDto(projectPermission);
    }


    @PatchMapping("/{projectId}/permission")
    public ProjectPermissionDto updateProjectPermission(@PathVariable(name = "projectId") UUID projectId, @RequestBody ProjectPermissionDto projectPermissionDto) {
        ProjectPermission projectPermission = projectService.updateProjectPermission(projectId, projectPermissionDto);
        return new ProjectPermissionDto(projectPermission);
    }

}
