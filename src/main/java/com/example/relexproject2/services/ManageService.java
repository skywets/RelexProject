package com.example.relexproject2.services;

import com.example.relexproject2.domain.OperationType;
import com.example.relexproject2.domain.dtos.RequestDto;
import com.example.relexproject2.domain.dtos.RequestWithOperationDto;
import com.example.relexproject2.domain.dtos.ResponseDto;

/**
 * Main service
 */
public interface ManageService {
    /**
     * Perform the required operation from dto and parameter
     * @param dto dto object with path to data file
     * @param operation required operation
     * @return dto with operation and answer
     */
    ResponseDto calculate(RequestDto dto, OperationType operation);

    /**
     * Perform the required operation from dto
     * @param dto dto object with path to data file and operation
     * @return dto with operation and answer
     */
    ResponseDto calculate(RequestWithOperationDto dto);
}
