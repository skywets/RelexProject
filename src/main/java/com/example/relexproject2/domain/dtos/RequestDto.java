package com.example.relexproject2.domain.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Class for request data transfer with path to data file
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestDto {
    @NotBlank(message = "Path to file can not be blank")
    private String filePath;
}
