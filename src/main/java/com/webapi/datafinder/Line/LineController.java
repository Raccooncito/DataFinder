package com.webapi.datafinder.Line;

import com.webapi.datafinder.Line.DTO.LineGeneralResponseDto;
import com.webapi.datafinder.Line.DTO.LineRequestDto;
import com.webapi.datafinder.user.DTO.UserGeneralResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/productionLine")
public class LineController {

    private final LineService lineService;

    public LineController(LineService productionLineService) {
        this.lineService = productionLineService;
    }

    @GetMapping
    public List<LineGeneralResponseDto> getAllProductionLines() {
        return lineService.getAllLines();
    }

    @GetMapping(path = "{lineCode}")
    public List<UserGeneralResponseDto> getAllWorkersFromLine(@PathVariable("lineCode") String lineCode) {
        return lineService.getAllWorkersOfLine(lineCode);
    }

    @PostMapping
    public void createLine(@RequestBody LineRequestDto productionLineRequestDto) {
        lineService.createNewLine(productionLineRequestDto);
    }

    @DeleteMapping(path = "{lineCode}")
    public void deleteLine(@PathVariable("lineCode") String lineCode) {
        lineService.deleteLine(lineCode);
    }

    @DeleteMapping(path = "{lineCode}/{employeeCode}")
    public void removeWorkerFromLine(@PathVariable("lineCode") String lineCode,
                                     @PathVariable("employeeCode") String employeeCode) {
        lineService.removeWorkerFromLine(lineCode, employeeCode);
    }

    @PutMapping(path = "{lineCode}")
    public void updateLine(
            @PathVariable("lineCode") String lineCode,
            @RequestBody LineRequestDto productionLineRequestDto
    ) {
        lineService.updateLine(lineCode, productionLineRequestDto);
    }

    @PutMapping(path = "{lineCode}/{employeeCode}")
    public void addWorkerToLine(@PathVariable("lineCode") String lineCode,
                                @PathVariable("employeeCode") String employeeCode) {
        lineService.addNewWorkerToLine(lineCode, employeeCode);
    }
}
