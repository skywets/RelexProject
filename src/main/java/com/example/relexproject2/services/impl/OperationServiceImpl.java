package com.example.relexproject2.services.impl;

import com.example.relexproject2.exceptions.OperationException;
import com.example.relexproject2.services.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    @Override
    public int calcMax(List<Integer> elements) {
        checkEmptyList(elements, "max value");
        return Collections.max(elements);
    }

    @Override
    public int calcMin(List<Integer> elements) {
        checkEmptyList(elements, "min value");
        return Collections.min(elements);
    }

    @Override
    public double calcMedian(List<Integer> elements) {
        checkEmptyList(elements, "median value");

        Collections.sort(elements);
        int size = elements.size();
        if (size % 2 == 0)
            return ((double) elements.get(size / 2) + (double) elements.get(size / 2 - 1)) / 2;
        else
            return (double) elements.get(size / 2);
    }

    @Override
    public double calcAvg(List<Integer> elements) {
        checkEmptyList(elements, "average value");

        return elements.stream()
                .mapToDouble(d -> d)
                .average()
                .orElseThrow(() -> new OperationException("Finding average value failed"));
    }

    @Override
    public List<List<Integer>> calcIncSeq(List<Integer> elements) {
        checkEmptyList(elements, "longest increase sequence");
        return calcSeq(elements, true);
    }

    @Override
    public List<List<Integer>> calcDecSeq(List<Integer> elements) {
        checkEmptyList(elements, "longest decrease sequence");
        return calcSeq(elements, false);
    }

    /**
     * Find one or more longest sequences in list of integers
     * @param elements list of integers
     * @param isInc true - find longest increase sequence, false - find longest decrease sequence
     * @return all found sequences
     */
    private List<List<Integer>> calcSeq(List<Integer> elements, boolean isInc) {
        List<List<Integer>> result = new ArrayList<>();

        // если в списке 1 элемент, возвращаем весь список
        if (elements.size() == 1) {
            result.add(elements);
            return result;
        }

        List<Integer> indexes = new ArrayList<>(); // индексы начал наибольших последовательностей
        indexes.add(0); // начальная длина 1, значит 0 элемент подходит
        int maxLength = 1; // длина наибольшей найденной последовательности
        int curLength = 1; // длина текущей найденной последовательности

        for (int i = 1; i < elements.size(); i++) {
            // проверяем продолжимость текущей последолвательности
            boolean isSeq;
            if (isInc)
                isSeq = elements.get(i) > elements.get(i - 1);
            else
                isSeq = elements.get(i) < elements.get(i - 1);

            if (isSeq) {
                // увеличиваем длину текущей последовательности
                curLength++;
                // если текущая длина равна максимальной, сохраянем индекс начала текущей последовательности
                if (curLength == maxLength)
                    indexes.add(i - maxLength + 1);
                // если текущая длина превысила максимальную, очищаем список индексов, сохраняем индекс начала текущей
                // последовательности и обновляем максимальную длину
                if (curLength > maxLength) {
                    maxLength = curLength;
                    indexes.clear();
                    indexes.add(i - maxLength + 1);
                }
            } else {
                // если последовательность прервалась, текущая длина становится равной 1
                curLength = 1;
            }
        }

        // сохраняем элементы последовательностей исходя из индексов
        for (Integer index : indexes) {
            List<Integer> curRes = new ArrayList<>();
            for (int j = 0; j < maxLength; j++) {
                curRes.add(elements.get(index + j));
            }
            result.add(curRes);
        }

        return result;
    }

    private void checkEmptyList(List<Integer> list, String operationName) {
        if (CollectionUtils.isEmpty(list))
            throw new OperationException("Finding " + operationName + " failed - list is empty");
    }
}
