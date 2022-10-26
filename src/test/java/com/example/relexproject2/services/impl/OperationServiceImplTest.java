package com.example.relexproject2.services.impl;

import com.example.relexproject2.exceptions.OperationException;
import com.example.relexproject2.services.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class OperationServiceImplTest {
    private final OperationService service = new OperationServiceImpl();

    private final List<Integer> LIST_DIFFERENT = List.of(1, 2, 3, 4, 5, 0, -1, -2, -4, - 3, -5);
    private final List<Integer> LIST_SIMILAR = List.of(1, 1, 1, 1, 1, 1);
    private final List<Integer> LIST_MEDIAN_EVEN = Arrays.asList(1, 2, 3 ,4); // для сортировки список должен быть изменяемый
    private final List<Integer> LIST_MEDIAN_ODD = Arrays.asList(1, 2, 3, 4, 5);
    private final List<Integer> LIST_INC_SEQ = List.of(7, 8, 9, 4, 5, 6, 1, 2, 3);
    private final List<Integer> LIST_DEC_SEQ = List.of(-7, -8, -9, -4, -5, -6, -1, -2, -3);
    private final List<Integer> LIST_ONE = List.of(1);
    private final List<Integer> LIST_EMPTY = Collections.emptyList();

    @Test
    void calcMax_whenDifferentElements_thenReturnMax() {
        Assertions.assertEquals(5, service.calcMax(LIST_DIFFERENT));
    }

    @Test
    void calcMax_whenSimilarElements_thenReturnElement() {
        Assertions.assertEquals(1, service.calcMax(LIST_SIMILAR));
    }

    @Test
    void calcMax_thenOneElement_thenReturnElement() {
        Assertions.assertEquals(1, service.calcMax(LIST_ONE));
    }

    @Test
    void calcMax_thenEmptyList_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcMax(LIST_EMPTY));
    }

    @Test
    void calcMax_thenListIsNull_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcMax(null));
    }

    @Test
    void calcMin_whenDifferentElements_thenReturnMin() {
        Assertions.assertEquals(-5, service.calcMin(LIST_DIFFERENT));
    }

    @Test
    void calcMin_whenSimilarElements_thenReturnElement() {
        Assertions.assertEquals(1, service.calcMin(LIST_SIMILAR));
    }

    @Test
    void calcMin_thenOneElement_thenReturnElement() {
        Assertions.assertEquals(1, service.calcMin(LIST_ONE));
    }

    @Test
    void calcMin_thenEmptyList_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcMin(LIST_EMPTY));
    }

    @Test
    void calcMin_thenListIsNull_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcMin(null));
    }

    @Test
    void calcMedian_whenSizeIsOdd_thenReturnMiddle() {
        Assertions.assertEquals(3, service.calcMedian(LIST_MEDIAN_ODD));
    }

    @Test
    void calcMedian_whenSizeIsEven_thenReturnSum() {
        Assertions.assertEquals(2.5, service.calcMedian(LIST_MEDIAN_EVEN));
    }

    @Test
    void calcMedian_thenEmptyList_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcMedian(LIST_EMPTY));
    }

    @Test
    void calcMedian_thenListIsNull_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcMedian(null));
    }

    @Test
    void calcAvg_whenDifferentElements_thenReturnAvg() {
        Assertions.assertEquals(0, service.calcAvg(LIST_DIFFERENT));
    }

    @Test
    void calcAvg_whenSimilarElements_thenReturnElement() {
        Assertions.assertEquals(1, service.calcAvg(LIST_SIMILAR));
    }

    @Test
    void calcAvg_thenOneElement_thenReturnElement() {
        Assertions.assertEquals(1, service.calcAvg(LIST_ONE));
    }

    @Test
    void calcAvg_thenEmptyList_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcAvg(LIST_EMPTY));
    }

    @Test
    void calcAvg_thenListIsNull_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcAvg(null));
    }

    @Test
    void calcIncSeq_whenDifferentElements_thenReturnLongestIncSeq() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1, 2, 3, 4, 5));
        Assertions.assertEquals(result, service.calcIncSeq(LIST_DIFFERENT));
    }

    @Test
    void calcIncSeq_whenSeveralSequences_thenReturnSequences() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(7, 8, 9));
        result.add(List.of(4, 5, 6));
        result.add(List.of(1, 2, 3));
        Assertions.assertEquals(result, service.calcIncSeq(LIST_INC_SEQ));
    }

    @Test
    void calcIncSeq_whenSimilarElements_thenReturnElement() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));
        Assertions.assertEquals(result, service.calcIncSeq(LIST_SIMILAR));
    }

    @Test
    void calcIncSeq_thenOneElement_thenReturnElement() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));
        Assertions.assertEquals(result, service.calcIncSeq(LIST_ONE));
    }

    @Test
    void calcIncSeq_thenEmptyList_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcIncSeq(LIST_EMPTY));
    }

    @Test
    void calcIncSeq_thenListIsNull_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcIncSeq(null));
    }

    @Test
    void calcDecSeq_whenDifferentElements_thenReturnLongestDecSeq() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(5, 0, -1, -2, -4));
        Assertions.assertEquals(result, service.calcDecSeq(LIST_DIFFERENT));
    }

    @Test
    void calcDecSeq_whenSeveralSequences_thenReturnSequences() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(-7, -8, -9));
        result.add(List.of(-4, -5, -6));
        result.add(List.of(-1, -2, -3));
        Assertions.assertEquals(result, service.calcDecSeq(LIST_DEC_SEQ));
    }

    @Test
    void calcDecSeq_whenSimilarElements_thenReturnElement() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));
        Assertions.assertEquals(result, service.calcDecSeq(LIST_SIMILAR));
    }

    @Test
    void calcDecSeq_thenOneElement_thenReturnElement() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));
        Assertions.assertEquals(result, service.calcDecSeq(LIST_ONE));
    }

    @Test
    void calcDecSeq_thenEmptyList_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcDecSeq(LIST_EMPTY));
    }

    @Test
    void calcDecSeq_thenListIsNull_thenThrowOperationException() {
        Assertions.assertThrows(OperationException.class, () -> service.calcDecSeq(null));
    }
}