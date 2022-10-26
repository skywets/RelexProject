package com.example.relexproject2.domain.dtos;

import com.example.relexproject2.domain.OperationType;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Class request for data transfer with path to data file and operation
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestWithOperationDto extends RequestDto {
    // @AllArgsConstructor не может вызывать super(), поэтому необходимо прописать его вручную
    public RequestWithOperationDto(String filePath, OperationType operation) {
        super(filePath);
        this.operation = operation;
    }

    @NotNull(message = "Operation can not be null")
    private OperationType operation;
}
