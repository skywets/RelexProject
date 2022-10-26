package com.example.relexproject2.domain.dtos;

import com.example.relexproject2.domain.OperationType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class for response data transfer with operation and answer
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDto {
    @NotNull(message = "Operation can not be null")
    private OperationType operation;

    @NotBlank(message = "Answer can not be blank")
    private String answer;
}
