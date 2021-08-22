package com.stackhack.timesheet.api;

import com.stackhack.timesheet.dtos.CostHeadDto;
import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.services.CostHeadService;
import com.stackhack.timesheet.utils.PaginatedResponse;
import com.stackhack.timesheet.utils.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/costhead")
public class CostHeadController {

    @Autowired
    private CostHeadService costHeadService;

    @PostMapping
    public CostHeadDto createCostHead(@RequestBody CostHeadDto costHeadDto) {
        CostHead costHead = costHeadService.createCostHead(costHeadDto);
        return new CostHeadDto(costHead);
    }


    @GetMapping("/{costHeadId}")
    public CostHeadDto getCostHead(@PathVariable(name = "costHeadId") UUID costHeadId) {
        CostHead costHead = costHeadService.getCostHead(costHeadId);
        return new CostHeadDto(costHead);
    }

    @PatchMapping("/{costHeadId}")
    public CostHeadDto updateCostHead(@PathVariable(name = "costHeadId") UUID costHeadId, @RequestBody CostHeadDto costHeadDto) {
        CostHead costHead = costHeadService.updateCostHead(costHeadId, costHeadDto);
        return new CostHeadDto(costHead);
    }

    @GetMapping
    public PaginatedResponse<CostHeadDto> getCostHeadPage(PaginationRequest pageRequest) {
        return PaginatedResponse.toPage(costHeadService.listCostHead(pageRequest).map(CostHeadDto::new));

    }

}
