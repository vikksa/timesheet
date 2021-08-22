package com.stackhack.timesheet.services;

import com.stackhack.timesheet.dtos.CostHeadDto;
import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CostHeadService {

    CostHead createCostHead(CostHeadDto costHeadDto);

    CostHead getCostHead(UUID id);

    Page<CostHead> listCostHead(PaginationRequest paginationRequest);

    CostHead updateCostHead(UUID id, CostHeadDto costHeadDto);
}
