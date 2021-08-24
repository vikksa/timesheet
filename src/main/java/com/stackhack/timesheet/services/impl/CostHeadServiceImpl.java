package com.stackhack.timesheet.services.impl;

import com.stackhack.timesheet.dtos.CostHeadDto;
import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.repo.CostHeadRepository;
import com.stackhack.timesheet.services.CostHeadService;
import com.stackhack.timesheet.utils.PaginationRequest;
import com.stackhack.timesheet.utils.TimeSheetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class CostHeadServiceImpl implements CostHeadService {

    @Autowired
    private CostHeadRepository costHeadRepository;

    @Override
    public CostHead createCostHead(CostHeadDto costHeadDto) {
        if (costHeadRepository.findByNameIgnoreCase(costHeadDto.getName()).isPresent()) {
            throw new TimeSheetException(HttpStatus.CONFLICT, "COST_HEAD_NAME_ALREADY_EXISTS");
        }
        CostHead costHead = new CostHead();
        costHead.setName(costHeadDto.getName());
        return costHeadRepository.save(costHead);
    }

    @Override
    public CostHead getCostHead(UUID costHeadId) {
        return costHeadRepository.findById(costHeadId).orElseThrow(() -> new TimeSheetException(HttpStatus.NOT_FOUND, "COST_HEAD_NOT_FOUND"));
    }

    @Override
    public Page<CostHead> listCostHead(PaginationRequest paginationRequest) {
        return costHeadRepository.findAll(paginationRequest.toPageable(sortBy -> "updatedBy"));
    }

    @Override
    public CostHead updateCostHead(UUID id, CostHeadDto costHeadDto) {
        CostHead costHead = getCostHead(id);
        if (!ObjectUtils.isEmpty(costHeadDto.getName())) {
            if (costHeadRepository.findByIdIsNotAndNameIgnoreCase(id, costHeadDto.getName()).isPresent()) {
                throw new TimeSheetException(HttpStatus.CONFLICT, "COST_HEAD_NAME_ALREADY_EXISTS");
            }
            costHead.setName(costHeadDto.getName());
        }
        if (costHeadDto.getArchived() != null) {
            costHead.setArchived(costHeadDto.getArchived());
        }
        return costHeadRepository.save(costHead);
    }

}
