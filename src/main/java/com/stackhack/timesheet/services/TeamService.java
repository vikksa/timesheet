package com.stackhack.timesheet.services;

import com.stackhack.timesheet.dtos.TeamDto;
import com.stackhack.timesheet.models.Team;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.Set;
import java.util.UUID;

public interface TeamService {

    Team createTeam(TeamDto teamDto);

    Team getTeam(UUID teamId);

    Page<Team> listTeam(PaginationRequest paginationRequest);

    Team updateTeam(UUID id, TeamDto teamDto);

    Team addTeamMember(UUID id, Set<UUID> memberIds);

    Team removeTeamMember(UUID id, Set<UUID> memberIds);

    Set<TimeSheetUser> getTeamMember(UUID id);

}
