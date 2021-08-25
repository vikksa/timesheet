package com.stackhack.timesheet;

import com.stackhack.timesheet.dtos.ProjectDto;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.services.ProjectService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class ProjectServiceUnitTest {

    @Autowired
    private ProjectService projectService;

    @Test
    @DisplayName("Create Project")
    void createProject() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project");
        Project project = projectService.createProject(projectDto);
        Assertions.assertNotNull(project);
        Assertions.assertNotNull(project.getName());
        Assertions.assertNotNull(project.getArchived());
        Assertions.assertEquals(projectDto.getName(), project.getName());
        Assertions.assertFalse(project.getArchived());
    }

    @Test
    @DisplayName("Update Project")
    void updateProject() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project 1");
        Project project = projectService.createProject(projectDto);

        ProjectDto updateDto = new ProjectDto();
        updateDto.setArchived(true);
        updateDto.setName("New Name to Project");
        Project updatedProject = projectService.updateProject(project.getId(), updateDto);
        Assertions.assertNotNull(updatedProject);
        Assertions.assertNotNull(updatedProject.getName());
        Assertions.assertNotNull(updatedProject.getArchived());
        Assertions.assertEquals(updateDto.getName(), updatedProject.getName());
        Assertions.assertTrue(updatedProject.getArchived());
    }

    @Test
    @DisplayName("Get Project")
    void getProject() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project Get");
        Project createdProject = projectService.createProject(projectDto);

        Project project = projectService.getProject(createdProject.getId());
        Assertions.assertNotNull(project);
        Assertions.assertNotNull(project.getName());
        Assertions.assertNotNull(project.getArchived());
        Assertions.assertEquals(projectDto.getName(), project.getName());
        Assertions.assertFalse(project.getArchived());
    }

    @Test
    @DisplayName("List Project")
    void listProject() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project List");
        Project createdProject = projectService.createProject(projectDto);

        ProjectDto projectDto1 = new ProjectDto();
        projectDto1.setName("Test Project List 1");
        Project createdProject1 = projectService.createProject(projectDto1);


        Page<Project> projects = projectService.listProject(new PaginationRequest());
        Assertions.assertNotNull(projects);
    }

    @Test
    @DisplayName("Not Found Project")
    void getNotDefinedProject() {
        UUID projectId = UUID.randomUUID();
        Assertions.assertThrows(TimeSheetException.class, () -> projectService.getProject(projectId));
    }

}
