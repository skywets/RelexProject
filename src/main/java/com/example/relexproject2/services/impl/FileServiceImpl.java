package com.example.relexproject2.services.impl;

import com.example.relexproject2.services.FileService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<Integer> readFromFile(String path) throws IOException, NumberFormatException {
        List<Integer> result;

        Path filePath = Paths.get(path);

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            result = reader.lines()
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
        }

        return result;
    }
}
