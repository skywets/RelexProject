package com.example.relexproject2.domain;

import com.example.relexproject2.domain.dtos.RequestDto;
import com.example.relexproject2.domain.dtos.RequestWithOperationDto;
import com.example.relexproject2.domain.dtos.ResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    Model requestDtoToModel(RequestDto dto, OperationType operation);
    Model requestWithOperationDtoToModel(RequestWithOperationDto dto);
    ResponseDto modelToResponseDto(Model model);
}
