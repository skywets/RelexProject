package com.example.relexproject2.controllers;

import com.example.relexproject2.domain.OperationType;
import com.example.relexproject2.domain.dtos.RequestDto;
import com.example.relexproject2.domain.dtos.RequestWithOperationDto;
import com.example.relexproject2.domain.dtos.ResponseDto;
import com.example.relexproject2.services.ManageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation successful",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ResponseDto.class))
        }),
        @ApiResponse(responseCode = "400", description = "Incorrect DTO", content = @Content),
        @ApiResponse(responseCode = "404", description = "File not found", content = @Content),
        @ApiResponse(responseCode = "409", description = "Operation failed", content = @Content)
})

public class OperationController {
    private final ManageService service;
    private final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Operation(summary = "Calculate operation from json, file path from json")
    @GetMapping("/all")
    public ResponseDto calculateFromJson(@RequestBody RequestWithOperationDto dto) {
        logger.info("Calculate from RequestWithOperationDto = " + dto.toString());
        return service.calculate(dto);
    }

    @Operation(summary = "Calculate max value, file path from json")
    @GetMapping("/get_max_value")
    public ResponseDto calculateMax(@RequestBody RequestDto dto) {
        logger.info("Calculate max value, dto = " + dto.toString());
        return service.calculate(dto, OperationType.MAX);
    }

    @Operation(summary = "Calculate min value, file path from json")
    @GetMapping("/get_min_value")
    public ResponseDto calculateMin(@RequestBody RequestDto dto) {
        logger.info("Calculate min value, dto = " + dto.toString());
        return service.calculate(dto, OperationType.MIN);
    }

    @Operation(summary = "Calculate median value, file path from json")
    @GetMapping("/get_median_value")
    public ResponseDto calculateMedianValue(@RequestBody RequestDto dto) {
        logger.info("Calculate median value, dto = " + dto.toString());
        return service.calculate(dto, OperationType.MED);
    }

    @Operation(summary = "Calculate average value, file path from json")
    @GetMapping("/get_avg_value")
    public ResponseDto calculateAvgValue(@RequestBody RequestDto dto) {
        logger.info("Calculate average value, dto = " + dto.toString());
        return service.calculate(dto, OperationType.AVG);
    }

    @Operation(summary = "Calculate longest increase sequence(s), file path from json")
    @GetMapping("/get_increase_sequence")
    public ResponseDto calculateIncSequence(@RequestBody RequestDto dto) {
        logger.info("Calculate longest increase sequence, dto = " + dto.toString());
        return service.calculate(dto, OperationType.SEQ_INC);
    }

    @Operation(summary = "Calculate longest decrease sequence(s), file path from json")
    @GetMapping("/get_decrease_sequence")
    public ResponseDto calculateDecSequence(@RequestBody RequestDto dto) {
        logger.info("Calculate longest decrease sequence, dto = " + dto.toString());
        return service.calculate(dto, OperationType.SEQ_DEC);
    }
}
