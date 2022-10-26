package com.example.relexproject2.services;

import java.io.IOException;
import java.util.List;

public interface FileService {
    /**
     * Read data from file and parse it to list of integers
     * @param path path to file in local system
     * @return parsed data
     * @throws IOException file not found
     * @throws NumberFormatException data in file can not be parsed to integer
     */
    List<Integer> readFromFile(String path) throws IOException, NumberFormatException;
}
