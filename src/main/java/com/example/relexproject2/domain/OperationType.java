package com.example.relexproject2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationType {
    MAX("max_value"),
    MIN("min_value"),
    MED("median_value"),
    AVG("avg_value"),
    SEQ_INC("increase_sequence"),
    SEQ_DEC("decrease_sequence");

    private final String type;
}
