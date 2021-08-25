package com.stackhack.timesheet;

import com.stackhack.timesheet.dtos.CostHeadDto;
import com.stackhack.timesheet.models.CostHead;
import com.stackhack.timesheet.services.CostHeadService;
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
class CostHeadServiceUnitTest {

    @Autowired
    private CostHeadService costHeadService;

    @Test
    @DisplayName("Create Cost Head")
    void createCostHead() {
        CostHeadDto costHeadDto = new CostHeadDto();
        costHeadDto.setName("Test CostHead");
        CostHead costHead = costHeadService.createCostHead(costHeadDto);
        Assertions.assertNotNull(costHead);
        Assertions.assertNotNull(costHead.getName());
        Assertions.assertNotNull(costHead.getArchived());
        Assertions.assertEquals(costHeadDto.getName(), costHead.getName());
        Assertions.assertFalse(costHead.getArchived());
    }

    @Test
    @DisplayName("Update Cost Head")
    void updateCostHead() {
        CostHeadDto costHeadDto = new CostHeadDto();
        costHeadDto.setName("Test CostHead 1");
        CostHead costHead = costHeadService.createCostHead(costHeadDto);

        CostHeadDto updateDto = new CostHeadDto();
        updateDto.setArchived(true);
        updateDto.setName("New Name to CostHead");
        CostHead updatedCostHead = costHeadService.updateCostHead(costHead.getId(), updateDto);
        Assertions.assertNotNull(updatedCostHead);
        Assertions.assertNotNull(updatedCostHead.getName());
        Assertions.assertNotNull(updatedCostHead.getArchived());
        Assertions.assertEquals(updateDto.getName(), updatedCostHead.getName());
        Assertions.assertTrue(updatedCostHead.getArchived());
    }

    @Test
    @DisplayName("Get Cost Head")
    void getCostHead() {
        CostHeadDto costHeadDto = new CostHeadDto();
        costHeadDto.setName("Test CostHead Get");
        CostHead createdCostHead = costHeadService.createCostHead(costHeadDto);

        CostHead costHead = costHeadService.getCostHead(createdCostHead.getId());
        Assertions.assertNotNull(costHead);
        Assertions.assertNotNull(costHead.getName());
        Assertions.assertNotNull(costHead.getArchived());
        Assertions.assertEquals(costHeadDto.getName(), costHead.getName());
        Assertions.assertFalse(costHead.getArchived());
    }

    @Test
    @DisplayName("List Cost Head")
    void listCostHead() {
        CostHeadDto costHeadDto = new CostHeadDto();
        costHeadDto.setName("Test CostHead List");
        CostHead createdCostHead = costHeadService.createCostHead(costHeadDto);

        CostHeadDto costHeadDto1 = new CostHeadDto();
        costHeadDto1.setName("Test CostHead List 1");
        CostHead createdCostHead1 = costHeadService.createCostHead(costHeadDto1);


        Page<CostHead> costHeads = costHeadService.listCostHead(new PaginationRequest());
        Assertions.assertNotNull(costHeads);
    }

    @Test
    @DisplayName("Expect Not Found Exception Cost Head")
    void getNotDefinedCostHead() {
        UUID costHeadId = UUID.randomUUID();
        Assertions.assertThrows(TimeSheetException.class, () -> costHeadService.getCostHead(costHeadId));
    }

}
