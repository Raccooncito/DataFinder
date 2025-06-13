package com.webapi.datafinder.Line;

import com.webapi.datafinder.Line.DTO.LineGeneralResponseDto;
import com.webapi.datafinder.Line.DTO.LineRequestDto;
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

    @PostMapping
    public void createProductionLine(@RequestBody LineRequestDto productionLineRequestDto) {
        lineService.createNewLine(productionLineRequestDto);
    }

    @DeleteMapping(path = "{lineCode}")
    public void deleteProductionLine(@PathVariable("lineCode") String lineCode) {
        lineService.deleteLine(lineCode);
    }

    @PutMapping(path = "{lineCode}")
    public void updateProductionLine(
            @PathVariable("lineCode") String lineCode,
            @RequestBody LineRequestDto productionLineRequestDto
    ) {
        lineService.updateLine(lineCode, productionLineRequestDto);
    }
}
