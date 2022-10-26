package com.example.relexproject2.services;

import java.util.List;

public interface OperationService {
    /**
     * Calculate max element in list
     * @param elements list of integers
     * @return max element in list
     */
    int calcMax(List<Integer> elements);

    /**
     * Calculate min element in list
     * @param elements list of integers
     * @return min element in list
     */
    int calcMin(List<Integer> elements);

    /**
     * Calculate median element in list
     * @param elements list of integers
     * @return median element in list
     */
    double calcMedian(List<Integer> elements);

    /**
     * Calculate average value of elements in list
     * @param elements - list of integers
     * @return average value
     */
    double calcAvg(List<Integer> elements);

    /**
     * Calculate one or more longest increase sequences of elements in list
     * @param elements - list of integers
     * @return all elements in longest sequences
     */
    List<List<Integer>> calcIncSeq(List<Integer> elements);

    /**
     * Calculate one or more longest decrease sequences of elements in list
     * @param elements - list of integers
     * @return all elements in longest sequences
     */
    List<List<Integer>> calcDecSeq(List<Integer> elements);
}
