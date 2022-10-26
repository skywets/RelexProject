package com.example.relexproject2.services.impl;

import com.example.relexproject2.domain.Model;
import com.example.relexproject2.domain.ModelMapper;
import com.example.relexproject2.domain.OperationType;
import com.example.relexproject2.domain.dtos.RequestDto;
import com.example.relexproject2.domain.dtos.RequestWithOperationDto;
import com.example.relexproject2.domain.dtos.ResponseDto;
import com.example.relexproject2.exceptions.FileLoadException;
import com.example.relexproject2.services.FileService;
import com.example.relexproject2.services.ManageService;
import com.example.relexproject2.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {
    private final ModelMapper mapper;
    private final FileService fileService;
    private final OperationService operationService;

    @Override
    public ResponseDto calculate(RequestDto dto, OperationType operation) {
        Model model = mapper.requestDtoToModel(dto, operation);
        callServices(model);
        return mapper.modelToResponseDto(model);
    }

    @Override
    public ResponseDto calculate(RequestWithOperationDto dto) {
        Model model = mapper.requestWithOperationDtoToModel(dto);
        callServices(model);
        return mapper.modelToResponseDto(model);
    }

    private void callServices(Model model) {
        try {
            List<Integer> elements = fileService.readFromFile(model.getFilePath());

            switch (model.getOperation()) {
                case MAX:
                    model.setAnswer(Integer.toString(operationService.calcMax(elements)));
                    break;
                case MIN:
                    model.setAnswer(Integer.toString(operationService.calcMin(elements)));
                    break;
                case MED:
                    model.setAnswer(Double.toString(operationService.calcMedian(elements)));
                    break;
                case AVG:
                    model.setAnswer(Double.toString(operationService.calcAvg(elements)));
                    break;
                case SEQ_INC:
                    model.setAnswer(operationService.calcIncSeq(elements).toString());
                    break;
                case SEQ_DEC:
                    model.setAnswer(operationService.calcDecSeq(elements).toString());
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            throw new FileLoadException("File load failed, file path = " + model.getFilePath());
        }
    }
}
