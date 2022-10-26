package com.example.relexproject2.controllers;

import com.example.relexproject2.domain.OperationType;
import com.example.relexproject2.domain.dtos.RequestDto;
import com.example.relexproject2.domain.dtos.RequestWithOperationDto;
import com.example.relexproject2.domain.dtos.ResponseDto;
import com.example.relexproject2.exceptions.FileLoadException;
import com.example.relexproject2.exceptions.OperationException;
import com.example.relexproject2.services.ManageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolationException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class OperationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ManageService serviceMock;

    private final String FILE_PATH = "C://10m.txt";
    private final OperationType OPERATION = OperationType.MAX;
    private final String ANSWER = "10";

    @Test
    void calculateFromJson_whenParametersIsCorrect_thenReturnAnswer() throws Exception {
        RequestWithOperationDto request = new RequestWithOperationDto(FILE_PATH, OPERATION);
        ResponseDto response = new ResponseDto(OPERATION, ANSWER);

        doReturn(response).when(serviceMock).calculate(any(RequestWithOperationDto.class));

        mockMvc.perform(get("/operations/all")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @Test
    void calculateFromJson_whenServiceThrowsFileLoadException_thenStatusIsNotFound() throws Exception {
        RequestWithOperationDto request = new RequestWithOperationDto(FILE_PATH, OPERATION);

        doThrow(new FileLoadException("test")).when(serviceMock).calculate(any(RequestWithOperationDto.class));

        mockMvc.perform(get("/operations/all")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void calculateFromJson_whenServiceThrowsOperationException_thenStatusIsConflict() throws Exception {
        RequestWithOperationDto request = new RequestWithOperationDto(FILE_PATH, OPERATION);

        doThrow(new OperationException("test")).when(serviceMock).calculate(any(RequestWithOperationDto.class));

        mockMvc.perform(get("/operations/all")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void calculateFromJson_whenServiceThrowsConstraintViolationException_thenStatusIsBadRequest() throws Exception {
        RequestWithOperationDto request = new RequestWithOperationDto();

        doThrow(ConstraintViolationException.class).when(serviceMock).calculate(any(RequestWithOperationDto.class));

        mockMvc.perform(get("/operations/all")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void calculateMax() throws Exception {
        RequestDto request = new RequestDto(FILE_PATH);
        ResponseDto response = new ResponseDto(OPERATION, ANSWER);

        doReturn(response).when(serviceMock).calculate(any(RequestDto.class), eq(OperationType.MAX));

        mockMvc.perform(get("/operations/get_max_value")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @Test
    void calculateMin() throws Exception {
        RequestDto request = new RequestDto(FILE_PATH);
        ResponseDto response = new ResponseDto(OPERATION, ANSWER);

        doReturn(response).when(serviceMock).calculate(any(RequestDto.class), eq(OperationType.MIN));

        mockMvc.perform(get("/operations/get_min_value")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @Test
    void calculateMedianValue() throws Exception {
        RequestDto request = new RequestDto(FILE_PATH);
        ResponseDto response = new ResponseDto(OPERATION, ANSWER);

        doReturn(response).when(serviceMock).calculate(any(RequestDto.class), eq(OperationType.MED));

        mockMvc.perform(get("/operations/get_median_value")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @Test
    void calculateAvgValue() throws Exception {
        RequestDto request = new RequestDto(FILE_PATH);
        ResponseDto response = new ResponseDto(OPERATION, ANSWER);

        doReturn(response).when(serviceMock).calculate(any(RequestDto.class), eq(OperationType.AVG));

        mockMvc.perform(get("/operations/get_avg_value")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @Test
    void calculateIncSequence() throws Exception {
        RequestDto request = new RequestDto(FILE_PATH);
        ResponseDto response = new ResponseDto(OPERATION, ANSWER);

        doReturn(response).when(serviceMock).calculate(any(RequestDto.class), eq(OperationType.SEQ_INC));

        mockMvc.perform(get("/operations/get_increase_sequence")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @Test
    void calculateDecSequence() throws Exception {
        RequestDto request = new RequestDto(FILE_PATH);
        ResponseDto response = new ResponseDto(OPERATION, ANSWER);

        doReturn(response).when(serviceMock).calculate(any(RequestDto.class), eq(OperationType.SEQ_DEC));

        mockMvc.perform(get("/operations/get_decrease_sequence")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }
}