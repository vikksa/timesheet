package com.stackhack.timesheet.services.impl;

import com.stackhack.timesheet.dtos.TimeLogsDto;
import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.models.Project;
import com.stackhack.timesheet.models.TimeLogs;
import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.repo.TimeLogsRepository;
import com.stackhack.timesheet.services.CostHeadService;
import com.stackhack.timesheet.services.ProjectService;
import com.stackhack.timesheet.services.TimeLogsService;
import com.stackhack.timesheet.services.TimeSheetUserService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TimeLogsServiceImpl implements TimeLogsService {

    @Autowired
    private TimeLogsRepository timeLogsRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private CostHeadService costHeadService;
    @Autowired
    private TimeSheetUserService timeSheetUserService;
    @Autowired
    private EntityManager entityManager;

    @Override
    public TimeLogs addTime(TimeLogsDto timeLogsDto) {
        Project project = projectService.getProject(timeLogsDto.getProjectId());
        CostHead costHead = costHeadService.getCostHead(timeLogsDto.getCostHeadId());
        TimeSheetUser user = timeSheetUserService.getUser(timeLogsDto.getTimeSheetUserId());
        TimeLogs timeLogs = new TimeLogs();
        if (timeLogsDto.getStartTime() != null) {
            timeLogs.setStartTime(new Date(timeLogsDto.getStartTime()));
        }
        if (timeLogsDto.getEndTime() != null) {
            timeLogs.setEndTime(new Date(timeLogsDto.getEndTime()));
        }
        timeLogs.setDescription(timeLogsDto.getDescription());
        timeLogs.setIssueNumber(timeLogsDto.getIssueNumber());
        timeLogs.setProgramName(timeLogsDto.getProgramName());
        timeLogs.setProject(project);
        timeLogs.setCostHead(costHead);
        timeLogs.setTimeSheetUser(user);
        return timeLogsRepository.save(timeLogs);
    }

    @Override
    public TimeLogs getTimeLog(UUID id) {
        return timeLogsRepository.findById(id).orElseThrow(() -> new TimeSheetException(HttpStatus.NOT_FOUND, "TIME_LOG_NOT_FOUND"));
    }

    @Override
    public TimeLogs updateTimeLog(UUID id, TimeLogsDto timeLogsDto) {
        TimeLogs timeLog = getTimeLog(id);
        if (timeLogsDto.getStartTime() != null) {
            timeLog.setStartTime(new Date(timeLogsDto.getStartTime()));
        }
        if (timeLogsDto.getEndTime() != null) {
            timeLog.setEndTime(new Date(timeLogsDto.getEndTime()));
        }
        timeLog.setDescription(timeLogsDto.getDescription());
        return timeLogsRepository.save(timeLog);
    }

    @Override
    public Page<TimeLogs> getTimeLogsPage(Long from, Long to,UUID project,  UUID user, UUID costHead, PaginationRequest paginationRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TimeLogs> query = cb.createQuery(TimeLogs.class);
        Root<TimeLogs> timeLogs = query.from(TimeLogs.class);

        List<Predicate> predicates = new ArrayList<>();

        if (from != null) {
            predicates.add(cb.lessThanOrEqualTo(timeLogs.get("startTime"), new Date(from)));
        }
        if (to != null) {
            predicates.add(cb.lessThanOrEqualTo(timeLogs.get("endTime"), new Date(to)));
        }
        if (project != null) {
            predicates.add(cb.lessThanOrEqualTo(timeLogs.get("project").get("id"), project));
        }
        if (user != null) {
            predicates.add(cb.lessThanOrEqualTo(timeLogs.get("timeSheetUser").get("id"), user));
        }
        if (costHead != null) {
            predicates.add(cb.lessThanOrEqualTo(timeLogs.get("costHead").get("id"), costHead));
        }

        final Pageable pageable = paginationRequest.toPageable(sortBy -> "createdAt");
        query = query.where(predicates.toArray(new Predicate[0])).orderBy(QueryUtils.toOrders(pageable.getSort(), timeLogs, cb));

        TypedQuery<TimeLogs> q = entityManager.createQuery(query);
        int total = q.getResultList().size();

        final int startPosition = pageable.getPageNumber() * pageable.getPageSize();
        List<TimeLogs> result = q
                .setFirstResult(startPosition)
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(result, pageable, total);
    }
}
