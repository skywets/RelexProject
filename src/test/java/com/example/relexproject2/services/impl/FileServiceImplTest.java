package com.example.relexproject2.services.impl;

import com.example.relexproject2.services.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class FileServiceImplTest {
    private final FileService service = new FileServiceImpl();

    private final List<Integer> LIST = List.of(1, 2, 3, 4, 5);

    @Test
    void readFromFile_whenPathAndDataIsCorrect_thenReturnList() throws IOException {
        File file = new File("src/test/resources/fileServiceResources/correct.txt");
        String filePath = file.getAbsolutePath();

        Assertions.assertEquals(LIST, service.readFromFile(filePath));
    }

    @Test
    void readFromFile_whenPathIsIncorrect_thenThrowsIOException() {
        String filePath = "src/test/resources/fileServiceResources/nonExistent.txt";

        Assertions.assertThrows(IOException.class, () -> service.readFromFile(filePath));
    }

    @Test
    void readFromFile_whenDataIsIncorrect_thenThrowsIOException() {
        File file = new File("src/test/resources/fileServiceResources/incorrect.txt");
        String filePath = file.getAbsolutePath();

        Assertions.assertThrows(NumberFormatException.class, () -> service.readFromFile(filePath));
    }
}