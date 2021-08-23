package com.stackhack.timesheet.services.impl;

import com.stackhack.timesheet.dtos.TeamDto;
import com.stackhack.timesheet.models.Team;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.repo.TeamRepository;
import com.stackhack.timesheet.services.TeamService;
import com.stackhack.timesheet.services.TimeSheetUserService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TimeSheetUserService timeSheetUserService;


    @Override
    public Team createTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setName(teamDto.getName());
        return teamRepository.save(team);
    }

    @Override
    public Team getTeam(UUID teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TimeSheetException(HttpStatus.NOT_FOUND, "TEAM_NOT_FOUND"));
    }

    @Override
    public Page<Team> listTeam(PaginationRequest paginationRequest) {
        return teamRepository.findAll(paginationRequest.toPageable(sortBy -> "updatedBy"));
    }

    @Override
    public Team updateTeam(UUID id, TeamDto teamDto) {
        Team team = getTeam(id);
        team.setName(teamDto.getName());
        if (!CollectionUtils.isEmpty(teamDto.getMemberIds())) {
            Set<TimeSheetUser> teamMembers = team.getTeamMembers();
            List<TimeSheetUser> newTeamMembers = teamDto.getMemberIds().stream()
                    .map(timeSheetUserService::getUser).collect(Collectors.toList());
            teamMembers.addAll(newTeamMembers);
        }
        return teamRepository.save(team);
    }

    @Override
    public Team addTeamMember(UUID id, Set<UUID> memberIds) {
        Team team = getTeam(id);
        if (!CollectionUtils.isEmpty(memberIds)) {
            Set<TimeSheetUser> teamMembers = team.getTeamMembers();
            List<TimeSheetUser> newTeamMembers = memberIds.stream()
                    .map(timeSheetUserService::getUser).collect(Collectors.toList());
            teamMembers.addAll(newTeamMembers);
        }
        return teamRepository.save(team);
    }

    @Override
    public Team removeTeamMember(UUID id, Set<UUID> memberIds) {
        Team team = getTeam(id);
        if (!CollectionUtils.isEmpty(memberIds)) {
            Set<TimeSheetUser> teamMembers = team.getTeamMembers();
            List<TimeSheetUser> newTeamMembers = memberIds.stream()
                    .map(timeSheetUserService::getUser).collect(Collectors.toList());
            newTeamMembers.forEach(teamMembers::remove);
        }
        return teamRepository.save(team);
    }

    @Override
    public Set<TimeSheetUser> getTeamMember(UUID id) {
        return getTeam(id).getTeamMembers();
    }
}
