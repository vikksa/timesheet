package com.stackhack.timesheet.api;

import com.stackhack.timesheet.dtos.TeamDto;
import com.stackhack.timesheet.dtos.TimeSheetUserDto;
import com.stackhack.timesheet.models.Team;
import com.stackhack.timesheet.services.TeamService;
import com.stackhack.timesheet.utils.PaginatedResponse;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public TeamDto createTeam(@RequestBody @Validated TeamDto teamDto) {
        Team team = teamService.createTeam(teamDto);
        return new TeamDto(team);
    }


    @GetMapping("/{teamId}")
    public TeamDto getTeam(@PathVariable(name = "teamId") UUID teamId) {
        Team team = teamService.getTeam(teamId);
        return new TeamDto(team);
    }

    @PatchMapping("/{teamId}")
    public TeamDto updateTeam(@PathVariable(name = "teamId") UUID teamId, @RequestBody TeamDto teamDto) {
        Team team = teamService.updateTeam(teamId, teamDto);
        return new TeamDto(team);
    }

    @GetMapping
    public PaginatedResponse<TeamDto> getTeamPage(PaginationRequest pageRequest) {
        return PaginatedResponse.toPage(teamService.listTeam(pageRequest).map(TeamDto::new));
    }

    @GetMapping("/{teamId}/members")
    public List<TimeSheetUserDto> getTeamMembers(@PathVariable(name = "teamId") UUID teamId) {
        return teamService.getTeamMember(teamId).stream().map(TimeSheetUserDto::new).collect(Collectors.toList());
    }

    @PatchMapping("/{teamId}/members")
    public TeamDto addTeamMembers(@PathVariable(name = "teamId") UUID teamId, @RequestParam("memberIds") Set<UUID> memberIds) {
        Team team = teamService.addTeamMember(teamId, memberIds);
        return new TeamDto(team);
    }

    @DeleteMapping("/{teamId}/members")
    public TeamDto removeTeamMembers(@PathVariable(name = "teamId") UUID teamId, @RequestParam("memberIds") Set<UUID> memberIds) {
        Team team = teamService.removeTeamMember(teamId, memberIds);
        return new TeamDto(team);
    }
}
