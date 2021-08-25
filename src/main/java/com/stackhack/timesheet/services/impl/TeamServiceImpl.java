package com.stackhack.timesheet.services.impl;

import com.stackhack.timesheet.dtos.TeamDto;
import com.stackhack.timesheet.models.Team;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.repo.TeamRepository;
import com.stackhack.timesheet.services.TeamService;
import com.stackhack.timesheet.services.TimeSheetUserService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TimeSheetUserService timeSheetUserService;

    public TeamServiceImpl(TeamRepository teamRepository, TimeSheetUserService timeSheetUserService) {
        this.teamRepository = teamRepository;
        this.timeSheetUserService = timeSheetUserService;
    }


    @Override
    public Team createTeam(TeamDto teamDto) {
        if (teamRepository.findByNameIgnoreCase(teamDto.getName()).isPresent()) {
            throw new TimeSheetException(HttpStatus.CONFLICT, "TEAM_NAME_ALREADY_EXISTS");
        }
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
        if (!ObjectUtils.isEmpty(teamDto.getName())) {
            if (teamRepository.findByIdIsNotAndNameIgnoreCase(id, teamDto.getName()).isPresent()) {
                throw new TimeSheetException(HttpStatus.CONFLICT, "TEAM_NAME_ALREADY_EXISTS");
            }
            team.setName(teamDto.getName());
        }
        if (!CollectionUtils.isEmpty(teamDto.getMemberIds())) {
            Set<TimeSheetUser> teamMembers = team.getTeamMembers();
            List<TimeSheetUser> newTeamMembers = teamDto.getMemberIds().stream()
                    .map(timeSheetUserService::getUser).collect(Collectors.toList());
            teamMembers.addAll(newTeamMembers);
        }
        if (teamDto.getArchived() != null) {
            team.setArchived(teamDto.getArchived());
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
