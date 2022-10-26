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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManageServiceImplTest {
    private ManageService service;
    private Validator validator;

    @Mock
    private ModelMapper mapperMock;
    @Mock
    private FileService fileServiceMock;
    @Mock
    private OperationService operationServiceMock;

    @BeforeEach
    void init() {
        service = new ManageServiceImpl(mapperMock, fileServiceMock, operationServiceMock);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    private final String FILE_PATH = "C://10m.txt";
    private final OperationType OPERATION = OperationType.MAX;
    private final String ANSWER = "10";
    private final List<Integer> LIST = List.of(10, 10, 10);

    @Test
    void calculate_requestWithOperation_whenAllIsCorrect_thenReturnAnswer() throws IOException {
        RequestWithOperationDto requestDto = new RequestWithOperationDto(FILE_PATH, OPERATION);
        Model model = new Model(FILE_PATH, OPERATION, ANSWER);
        ResponseDto responseDto = new ResponseDto(OPERATION, ANSWER);

        doReturn(model).when(mapperMock).requestWithOperationDtoToModel(requestDto);
        doReturn(responseDto).when(mapperMock).modelToResponseDto(model);
        doReturn(LIST).when(fileServiceMock).readFromFile(FILE_PATH);
        doReturn(10).when(operationServiceMock).calcMax(LIST);

        Assertions.assertTrue(validator.validate(requestDto).isEmpty());
        Assertions.assertEquals(responseDto, service.calculate(requestDto));
    }

    @Test
    void calculate_requestWithOperation_whenRequestIsIncorrect_thenValidationExceptionCreated() {
        RequestWithOperationDto requestDto = new RequestWithOperationDto("", null);

        Assertions.assertFalse(validator.validate(requestDto).isEmpty());
    }

    @Test
    void calculate_requestWithOperation_whenFileLoadFailed_thenThrowsException() throws IOException {
        RequestWithOperationDto requestDto = new RequestWithOperationDto(FILE_PATH, OPERATION);
        Model model = new Model(FILE_PATH, OPERATION, ANSWER);

        doReturn(model).when(mapperMock).requestWithOperationDtoToModel(requestDto);
        doThrow(IOException.class).when(fileServiceMock).readFromFile(FILE_PATH);

        Assertions.assertTrue(validator.validate(requestDto).isEmpty());
        Assertions.assertThrows(FileLoadException.class, () -> service.calculate(requestDto));
    }

    @Test
    void calculate_whenAllIsCorrect_thenReturnAnswer() throws IOException {
        RequestDto requestDto = new RequestDto(FILE_PATH);
        Model model = new Model(FILE_PATH, OPERATION, ANSWER);
        ResponseDto responseDto = new ResponseDto(OPERATION, ANSWER);

        doReturn(model).when(mapperMock).requestDtoToModel(requestDto, OPERATION);
        doReturn(responseDto).when(mapperMock).modelToResponseDto(model);
        doReturn(LIST).when(fileServiceMock).readFromFile(FILE_PATH);
        doReturn(10).when(operationServiceMock).calcMax(LIST);

        Assertions.assertTrue(validator.validate(requestDto).isEmpty());
        Assertions.assertEquals(responseDto, service.calculate(requestDto, OPERATION));
    }

    @Test
    void calculate_whenRequestIsIncorrect_thenValidationExceptionCreated() {
        RequestDto requestDto = new RequestDto("");

        Assertions.assertFalse(validator.validate(requestDto).isEmpty());
    }

    @Test
    void calculate_whenFileLoadFailed_thenThrowsException() throws IOException {
        RequestDto requestDto = new RequestDto(FILE_PATH);
        Model model = new Model(FILE_PATH, OPERATION, ANSWER);

        doReturn(model).when(mapperMock).requestDtoToModel(requestDto, OPERATION);
        doThrow(IOException.class).when(fileServiceMock).readFromFile(FILE_PATH);

        Assertions.assertTrue(validator.validate(requestDto).isEmpty());
        Assertions.assertThrows(FileLoadException.class, () -> service.calculate(requestDto, OPERATION));
    }
}