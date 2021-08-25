package com.stackhack.timesheet;

import com.stackhack.timesheet.dtos.TeamDto;
import com.stackhack.timesheet.models.Team;
import com.stackhack.timesheet.services.TeamService;
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
class TeamServiceUnitTest {

    @Autowired
    private TeamService teamService;

    @Test
    @DisplayName("Create Team")
    void createTeam() {
        TeamDto teamDto = new TeamDto();
        teamDto.setName("Test Team");
        Team team = teamService.createTeam(teamDto);
        Assertions.assertNotNull(team);
        Assertions.assertNotNull(team.getName());
        Assertions.assertNotNull(team.getArchived());
        Assertions.assertEquals(teamDto.getName(), team.getName());
        Assertions.assertFalse(team.getArchived());
    }

    @Test
    @DisplayName("Update Team")
    void updateTeam() {
        TeamDto teamDto = new TeamDto();
        teamDto.setName("Test Team 1");
        Team team = teamService.createTeam(teamDto);

        TeamDto updateDto = new TeamDto();
        updateDto.setArchived(true);
        updateDto.setName("New Name to Team");
        Team updatedTeam = teamService.updateTeam(team.getId(), updateDto);
        Assertions.assertNotNull(updatedTeam);
        Assertions.assertNotNull(updatedTeam.getName());
        Assertions.assertNotNull(updatedTeam.getArchived());
        Assertions.assertEquals(updateDto.getName(), updatedTeam.getName());
        Assertions.assertTrue(updatedTeam.getArchived());
    }

    @Test
    @DisplayName("Get Team")
    void getTeam() {
        TeamDto teamDto = new TeamDto();
        teamDto.setName("Test Team Get");
        Team createdTeam = teamService.createTeam(teamDto);

        Team team = teamService.getTeam(createdTeam.getId());
        Assertions.assertNotNull(team);
        Assertions.assertNotNull(team.getName());
        Assertions.assertNotNull(team.getArchived());
        Assertions.assertEquals(teamDto.getName(), team.getName());
        Assertions.assertFalse(team.getArchived());
    }

    @Test
    @DisplayName("List Team")
    void listTeam() {
        TeamDto teamDto = new TeamDto();
        teamDto.setName("Test Team List");
        Team createdTeam = teamService.createTeam(teamDto);

        TeamDto teamDto1 = new TeamDto();
        teamDto1.setName("Test Team List 1");
        Team createdTeam1 = teamService.createTeam(teamDto1);


        Page<Team> teams = teamService.listTeam(new PaginationRequest());
        Assertions.assertNotNull(teams);
    }

    @Test
    @DisplayName("Not Found Team")
    void getNotDefinedTeam() {
        UUID teamId = UUID.randomUUID();
        Assertions.assertThrows(TimeSheetException.class, () -> teamService.getTeam(teamId));
    }

}
