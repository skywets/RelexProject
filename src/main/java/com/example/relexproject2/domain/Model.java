package com.example.relexproject2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Model {
    private String filePath;
    private OperationType operation;
    private String answer;
}
